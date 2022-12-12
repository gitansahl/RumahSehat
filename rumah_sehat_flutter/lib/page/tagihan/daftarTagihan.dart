import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:intl/intl.dart';

import 'package:rumah_sehat_flutter/main.dart';
import 'package:rumah_sehat_flutter/page/profile/profile.dart';
import 'package:rumah_sehat_flutter/page/tagihan/bayarTagihan.dart';

class DaftarTagihanPage extends StatelessWidget {
  const DaftarTagihanPage({Key? key}) : super(key: key);
  Card generateListTile(
      context, String kodeTagihan, int jumlahTagihan, bool terbayar) {
    var status_warna = const TextStyle(color: Colors.green);
    var status_bayar = "Lunas";
    if (!terbayar) {
      status_warna = const TextStyle(color: Colors.red);
      status_bayar = "Belum dibayar";
    }

    return Card(
      child: ListTile(
        title: Text("Kode tagihan: " + kodeTagihan),
        subtitle: RichText(
          text: TextSpan(children: [
            TextSpan(
                text: "Jumlah tagihan: $jumlahTagihan\nStatus: ",
                style: const TextStyle(color: Color.fromARGB(255, 46, 46, 46))),
            TextSpan(text: status_bayar, style: status_warna)
          ]),
        ),
        trailing: IconButton(
          icon: const Icon(Icons.keyboard_arrow_right),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => DetailTagihanPage(
                  kode_tagihan: kodeTagihan,
                ),
              ),
            );
          },
        ),
      ),
    );
  }

  Future<Map<String, dynamic>> getListTagihan() async {
    var header = {
      "Content-Type": "application/json",
      "Authorization": "Bearer $jwt"
    };
    var respond = await http.get(Uri.parse("$SERVER_IP/api/tagihan/get"),
        headers: header);
    return jsonDecode(respond.body);
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: getListTagihan(),
        builder: (context, snapshot) {
          if (!snapshot.hasData) return CircularProgressIndicator();

          var data = snapshot.data as Map<String, dynamic>;
          List<dynamic> allTagihan = data['data'];

          return Scaffold(
              appBar: AppBar(
                title: const Text("Daftar Tagihan"),
              ),
              body: SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Center(
                      child: Column(
                    children: <Widget>[
                      ListView(
                        children: <Card>[
                          for (var i in allTagihan)
                            generateListTile(context, i['kodeTagihan'],
                                i['jumlahTagihan'], i['terbayar'])
                        ],
                        shrinkWrap: true,
                        physics: const NeverScrollableScrollPhysics(),
                      ),
                    ],
                  )),
                ),
              ));
        });
  }
}

Future<Map<String, dynamic>> getTagihan(String kodeTagihan) async {
  var header = {
    "Content-Type": "application/json",
    "Authorization": "Bearer $jwt"
  };
  var respond = await http
      .get(Uri.parse("$SERVER_IP/api/tagihan/" + kodeTagihan), headers: header);
  return jsonDecode(respond.body);
}

class DetailTagihanPage extends StatelessWidget {
  const DetailTagihanPage({
    Key? key,
    required this.kode_tagihan,
  }) : super(key: key);

  final String kode_tagihan;

  Widget buttonBayar(bool terbayar, context) {
    if (terbayar) {
      return TextButton(
          child: Text('Kembali'),
          onPressed: () {
            Navigator.pop(context);
          });
    }

    return TextButton(
          child: Text('Bayar'),
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => BayarTagihanPage(kode_tagihan: kode_tagihan,)),
            );
          }); 
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: getTagihan(kode_tagihan),
        builder: (context, snapshot) {
          if (!snapshot.hasData) {
            Text('tidak ada data');
          } else if (snapshot.hasData) {
            Map<String, dynamic> tagihanMap =
                snapshot.data as Map<String, dynamic>;
            Map<String, dynamic> detailTagihan = tagihanMap['data'];

            return Scaffold(
                appBar: AppBar(
                  title: Text("Detail Tagihan"),
                ),
                body: Column(
                  children: [
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('Kode tagihan'),
                                subtitle: Text(
                                    detailTagihan['kodeTagihan'].toString()),
                              ),
                            ]),
                      ),
                    ),
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('Kode Appointment'),
                                subtitle: Text(detailTagihan['appointment']
                                        ['kodeAppointment']
                                    .toString()),
                              ),
                            ]),
                      ),
                    ),
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('tanggal Dibuat'),
                                subtitle: Text(
                                    detailTagihan['tanggalDibuat'].toString()),
                              ),
                            ]),
                      ),
                    ),
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('tanggalPembayaran'),
                                subtitle: Text(
                                    detailTagihan['tanggalPembayaran']
                                        .toString()),
                              ),
                            ]),
                      ),
                    ),
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('jumlahTagihan'),
                                subtitle: Text(
                                    detailTagihan['jumlahTagihan'].toString()),
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  buttonBayar(detailTagihan['terbayar'], context)
                                ],
                              )
                            ]),
                      ),
                    ),
                  ],
                ));
          } else if (snapshot.hasError) {
            return Text('${snapshot.error}');
          }

          return CircularProgressIndicator();
        });
  }
}
