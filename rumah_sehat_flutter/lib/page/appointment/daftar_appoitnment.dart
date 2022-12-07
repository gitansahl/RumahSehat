import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/appointment/create_appointment.dart';

class AppointmentPage extends StatelessWidget {
  const AppointmentPage({Key? key}) : super(key: key);
  Card generateListTile(String namaDokter, String waktuAwal, bool isDone) {
    DateTime dateTime = DateTime.parse(waktuAwal);
    String tanggal = DateFormat.yMMMMd().format(dateTime);
    String jam = DateFormat.jm().format(dateTime);

    var status = Icons.check_circle_outline;
    if(!isDone) {
      status = Icons.schedule;
    }
    return Card(
      child: ListTile(
        title: Text(
          namaDokter
        ),
        leading: Icon(
            status),
        subtitle: RichText(
          text: TextSpan(
              children: [
                TextSpan(
                    text: "$tanggal $jam ",
                    style: const TextStyle(
                        color: Colors.black
                    )
                )
              ]
          ),
        )
      ),
    );
  }

  ListView assembleListView(Map<String, dynamic> data) {
    List<dynamic> data0 = data['data'];
    return ListView(
      children: <Card> [
        for (var i in data0) generateListTile(i['dokter']['nama'], i['waktuAwal'], i['isDone'])
      ],
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
    );
  }
  
  Future<Map<String, dynamic>> getListAppointment() async {
    var header = {
      "Content-Type":"application/json",
      "Authorization" : "Bearer $jwt"
    };
    var respond = await http.get(
      "$SERVER_IP/api/appointment/get",
      headers: header
    );
    return jsonDecode(respond.body);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: getListAppointment(),
        builder: (context, snapshot) {
          if (!snapshot.hasData) return CircularProgressIndicator();
          return Scaffold(
            appBar: AppBar(
              title: const Text("Daftar Appointment"),
            ),
            body: SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Center(
                    child: Column(
                      children: <Widget>[
                        ElevatedButton(
                            onPressed: () {
                              Navigator.push(context,
                                  MaterialPageRoute(builder: (context) => CreateAppointment())
                              );
                            },
                            child: const Text("Buat Appointment")),
                        assembleListView(snapshot.data as Map<String, dynamic>),
                      ],
                    )
                ),
              ),
            )

          );
        });
  }
}