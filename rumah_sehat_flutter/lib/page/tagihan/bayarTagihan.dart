import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/app.dart';
import 'package:rumah_sehat_flutter/page/profile/profile.dart';

class BayarTagihanPage extends StatelessWidget {
  const BayarTagihanPage({
    Key? key,
    required this.kode_tagihan,
  }) : super(key: key);

  final String kode_tagihan;

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
                  MaterialPageRoute(
                      builder: (context) => const MyStatefulWidget()),
                );
              },
              child: const Text('Ok'),
            ),
          ],
        ),
      );

  Future<Map<String, dynamic>> bayarTagihan(String kode_tagihan) async {
    var respond = await http.post(
        Uri.parse("$SERVER_IP/api/tagihan/bayar/" + kode_tagihan),
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
          title: const Text('Bayar Tagihan'),
        ),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Center(
              child: Card(
                child:
                    Column(mainAxisSize: MainAxisSize.min, children: const <Widget>[
                  ListTile(
                    title: Text('Pastikan saldo anda mencukupi!'),
                    subtitle: Text("Lanjutkan pembayaran?"),
                  ),
                ]),
              ),
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
              child: ElevatedButton(
                  onPressed: () async {
                    // var res = snapshot.data.toString();
                    var res = await bayarTagihan(kode_tagihan);
                    if (res['status'] == 200) {
                      if (res['data'] == "Saldo tidak cukup") {
                        displayDialog(context, "Gagal", res['data'].toString());
                        Future.delayed(const Duration(seconds: 5));
                      } else if (res['data'] == "Pembayaran Berhasil") {
                        displayDialog(
                            context, "Success", res['data'].toString());
                        Future.delayed(const Duration(seconds: 5));
                      }
                    } else {
                      displayDialog(context, "Error",
                          res.toString() + "\n" + res['error']);
                    }
                  },
                  child: const Text("Confirm")),
            ),
          ],
        ));
  }
}
