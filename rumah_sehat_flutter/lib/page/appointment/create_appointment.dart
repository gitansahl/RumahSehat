import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;

class CreateAppointment extends StatefulWidget {



  @override
  State<CreateAppointment> createState() => _CreateAppointmentState();
}

class _CreateAppointmentState extends State<CreateAppointment> {
  DateTime dateTime = DateTime(
    DateTime.now().year,
    DateTime.now().month,
    DateTime.now().day,
    DateTime.now().hour,
    DateTime.now().minute
  );
  String? dokterId;

  @override
  Widget build(BuildContext context) {
    final hours = dateTime.hour.toString().padLeft(2, '0');
    final minutes = dateTime.minute.toString().padLeft(2, '0');

    Future<Map<String, dynamic>> getListDokter() async {
      var header = {
        "Content-Type":"application/json",
        "Authorization" : "Bearer $jwt"
      };
      var respond = await http.get(
          Uri.parse("$SERVER_IP/api/user/dokter/get"),
          headers: header
      );
      return jsonDecode(respond.body);
    }

    List<DropdownMenuItem<String>> generateListDropdownItem(Map<String, dynamic> data) {
      List<DropdownMenuItem<String>> res = [];
      List<dynamic> dataDokter = data['data'];

      for (var i in dataDokter) {
        DropdownMenuItem<String> newItem = DropdownMenuItem(
            child: Text("${i['nama']} - ${i['tarif']}"),
            value: i['username']);
        res.add(newItem);
      }

      return res;
    }

    void displayDialog(context, title, text) => showDialog(
      context: context,
      builder: (context) =>
          AlertDialog(
              title: Text(title),
              content: Text(text)
          ),
    );

    return FutureBuilder(
        future: getListDokter(),
        builder: (context, snapshot) {
          if (!snapshot.hasData) return const CircularProgressIndicator();

          List<DropdownMenuItem<String>> dropdownAppearance = generateListDropdownItem(snapshot.data as Map<String, dynamic>);
          return Scaffold(
            appBar: AppBar(
              title: const Text(
                  "Add Appointment"
              ),
            ),
            body: Container(
              padding: EdgeInsets.all(16.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  DropdownButtonFormField(
                      items: dropdownAppearance,
                      value: dokterId,
                      onChanged: (value) {
                          setState(() {
                            dokterId = value.toString();
                        });
                      }
                  ),
                  const Text(
                    "Tanggal dan Waktu",
                    style: TextStyle(fontSize: 32),
                  ),
                  const SizedBox(
                    height: 16,
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Expanded(
                          child: ElevatedButton(
                            child: Text(
                                "${dateTime.year}/${dateTime.month}/${dateTime.day}"
                            ),
                            onPressed: () async {
                              final date = await pickDate();
                              if (date == null) return;

                              final newDateTime = DateTime(
                                  date.year,
                                  date.month,
                                  date.day,
                                  dateTime.hour,
                                  dateTime.minute
                              );

                              setState(() => dateTime = newDateTime);
                            },
                          )
                      ),
                      const SizedBox(width: 12),
                      Expanded(
                          child: ElevatedButton(
                            child: Text('$hours:$minutes'),
                            onPressed: () async {
                              final time = await pickTime();
                              if (time == null) return;

                              final newDateTime = DateTime(
                                  dateTime.year,
                                  dateTime.month,
                                  dateTime.day,
                                  time.hour,
                                  time.minute
                              );

                              setState(() => dateTime = newDateTime);
                            },
                          ))
                    ],
                  ),
                  ElevatedButton(
                      onPressed: () async {
                        var body = {
                          "waktu_awal" : dateTime.toString().replaceAll(" ", "T"),
                          "id_dokter" : dokterId
                        };

                        var header = {
                          "Authorization" : "Bearer $jwt",
                          "Content-Type":"application/json"
                        };

                        var respond = await http.post(
                            Uri.parse("$SERVER_IP/api/appointment/add"),
                            headers: header,
                            body: jsonEncode(body));

                        var data = jsonDecode(respond.body);

                        if (data['status']!=200) {
                          displayDialog(context, "Error", data['data']);
                        }
                        displayDialog(context, "Success", data['data']);
                      },
                      child: const Text("Simpan"))
                ],
              ),
            ),
          );
        });
  }

  Future<DateTime?> pickDate() => showDatePicker(
      context: context,
      initialDate: dateTime,
      firstDate: dateTime,
      lastDate: dateTime.add(const Duration(days: 365)));

  Future<TimeOfDay?> pickTime() => showTimePicker(
      context: context,
      initialTime: TimeOfDay(hour: dateTime.hour, minute: dateTime.minute));
}