import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/appointment/create_appointment.dart';
import 'package:rumah_sehat_flutter/page/appointment/detail_appointment.dart';

class AppointmentPage extends StatelessWidget {
  const AppointmentPage({Key? key}) : super(key: key);

  Future<Map<String, dynamic>> getListAppointment() async {
    var header = {
      "Content-Type":"application/json",
      "Authorization" : "Bearer $jwt"
    };
    var respond = await http.get(
        Uri.parse("$SERVER_IP/api/appointment/get"),
        headers: header
    );
    return jsonDecode(respond.body);
  }

  @override
  Widget build(BuildContext context) {

    Card generateListTile(Map<String, dynamic> data) {
      DateTime dateTime = DateTime.parse(data['waktuAwal']);
      String tanggal = DateFormat.yMMMMd().format(dateTime);
      String jam = DateFormat.jm().format(dateTime);

      var status = Icons.check_circle_outline;
      if(!data['isDone']) {
        status = Icons.schedule;
      }
      return Card(
        child: ListTile(
          title: Text(
              data['dokter']['nama']
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
          ),
          onTap: () {
            Navigator.push(context,
                MaterialPageRoute(
                  builder: (context) => DetailAppointmentPage(data),
                )
            );
          },
        ),
      );
    }

    ListView assembleListView(Map<String, dynamic> data) {
      List<dynamic> data0 = data['data'];
      return ListView(
        children: <Card> [
          for (var i in data0) generateListTile(i)
        ],
        shrinkWrap: true,
        physics: const NeverScrollableScrollPhysics(),
      );
    }

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