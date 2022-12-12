import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/app.dart';
import 'package:rumah_sehat_flutter/page/profile/profile.dart';

class ToUpSaldoPage extends StatelessWidget {
  final TextEditingController _saldoTambahanController =
      TextEditingController();

  ToUpSaldoPage({Key? key}) : super(key: key);

  void displayDialog(context, title, text) => showDialog(
        context: context,
        builder: (context) => AlertDialog(
          title: Text(title),
          content: Text(text),
          actions: [
            ElevatedButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const MyStatefulWidget()),
                );
              },
              child: const Text('Ok'),
            ),
          ],
        ),
      );

  Future<Map<String, dynamic>> topUp(
    int saldoTambahan,
  ) async {
    var requestBody = {
      "saldoTambahan": saldoTambahan,
    };

    var respond = await http.post(Uri.parse("$SERVER_IP/api/topUp"),
        body: jsonEncode(requestBody),
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer $jwt"
        });

    return jsonDecode(respond.body);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Top Up Saldo'),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
              child: TextField(
                controller: _saldoTambahanController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    hintText: 'Masukkan nominal Top-Up',
                    labelText: 'Nominal Top-Up'),
                keyboardType: TextInputType.number,
                inputFormatters: <TextInputFormatter>[
                  FilteringTextInputFormatter.digitsOnly
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
              child: ElevatedButton(
                  onPressed: () async {
                    try {
                      int.parse(_saldoTambahanController.text);
                    } catch (e) {
                      displayDialog(
                          context, "Error", "Mohon isi saldo dengan benar");
                    }
                    int saldoTambahan =
                        int.parse(_saldoTambahanController.text);

                    if (saldoTambahan == null) {
                      displayDialog(context, "Error", "null ex");
                    } else {
                      var res = await topUp(saldoTambahan);
                      if (res['status'] == 200) {
                        displayDialog(
                            context,
                            "Success",
                            "Top-Up sebesar " +
                                saldoTambahan.toString() +
                                " berhasil dilakukan");
                        Future.delayed(const Duration(seconds: 5));
                      } else {
                        displayDialog(context, "Error",
                            res['status'].toString() + "\n" + res['error']);
                      }
                    }
                  },
                  child: const Text("Confirm")),
            ),
          ],
        ));
  }
}
