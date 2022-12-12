import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class DetailAppointmentPage extends StatelessWidget {
  Map<String, dynamic> appointment;
  DetailAppointmentPage(this.appointment, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    String status = "Belum dilaksanakan.";
    Color textColor = Colors.red;
    if (appointment['isDone']) {
      status = "Selesai.";
      textColor = Colors.green;
    }

    Widget resep = const Text (
      "Belum ada resep.",
      style: TextStyle(
          fontSize: 20
      ),
      textAlign: TextAlign.center,
    );

    if (appointment['resep'] != null) {
      resep = ElevatedButton(
          onPressed: () {
            // Tombol Detail Resep
            // Navigator.push(context,
            //     MaterialPageRoute(
            //       builder: (context) => DetailAppointmentPage(data),
            //     )
            // );
          },
          child: const Text("Detail Resep")
      );
    }

    DateTime dateTime = DateTime.parse(appointment['waktuAwal']);
    String tanggal = DateFormat.yMMMMd().format(dateTime);
    String jam = DateFormat.jm().format(dateTime);

    return Scaffold(
      appBar: AppBar(
        title: Text("Detail Appointment ${appointment['kodeAppointment']}"),
      ),
      body: ListView(
        padding: const EdgeInsets.all(8),
        children: <Widget> [
          Row(
            children: [
              const Expanded(
                child: Text(
                  "Kode Appointment:",
                  style: TextStyle(
                    fontSize: 20
                  ),
                ),
                flex: 1,
              ),
              Expanded(
                child: Text (
                  appointment['kodeAppointment'],
                  style: const TextStyle(
                    fontSize: 20
                  ),
                  textAlign: TextAlign.center,
                ),
                flex: 1,
              )
            ],
          ),
          Row(
            children: [
              const Expanded(
                child: Text(
                  "Waktu Awal:",
                  style: TextStyle(
                      fontSize: 20
                  ),
                ),
                flex: 1,
              ),
              Expanded(
                child: Text (
                  "$tanggal $jam",
                  style: const TextStyle(
                      fontSize: 20
                  ),
                  textAlign: TextAlign.center,
                ),
                flex: 1,
              )
            ],
          ),
          Row(
            children: [
              const Expanded(
                child: Text(
                  "Status:",
                  style: TextStyle(
                      fontSize: 20
                  ),
                ),
                flex: 1,
              ),
              Expanded(
                child: Text (
                  status,
                  style: TextStyle(
                      fontSize: 20,
                      color: textColor
                  ),
                  textAlign: TextAlign.center,
                ),
                flex: 1,
              )
            ],
          ),
          Row(
            children: [
              const Expanded(
                child: Text(
                  "Nama Dokter:",
                  style: TextStyle(
                      fontSize: 20
                  ),
                ),
                flex: 1,
              ),
              Expanded(
                child: Text (
                  appointment['dokter']['nama'],
                  style: const TextStyle(
                      fontSize: 20
                  ),
                  textAlign: TextAlign.center,
                ),
                flex: 1,
              )
            ],
          ),
          Row(
            children: [
              Expanded(
                child: resep,
                flex: 1,
              ),
            ],
          ),
        ],
      )
    );
  }

}