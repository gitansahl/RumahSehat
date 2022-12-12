import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
// import 'package:rumah_sehat_flutter/page/daftar_appointment.dart';
import 'resepModel.dart';
import 'package:rumah_sehat_flutter/main.dart';

class DetailResep extends StatelessWidget {
  late ResepModel detailResep;

  DetailResep(this.kodeApt);

  final String kodeApt;

  // Referensi: https://blog.logrocket.com/implementing-repository-pattern-flutter/
  Future getDetailResep() async {
    var response = await http.get(
        Uri.parse("$SERVER_IP/api/v1/resep/$kodeApt",), // harusnya $idResep, ini masih hardcode
        // Uri.parse('http://localhost:8080/api/v1/resep/'+idResep), // harusnya $idResep, ini masih hardcode
        headers: <String, String>{
          'Content-Type' : 'application/json;charset=UTF-8',
          'Authorization' : 'Bearer $jwt',
          // "Access-Control-Allow-Origin": "*",
          // "Access-Control-Allow-Method": "POST, GET, PUT, DELETE"
        });
    var jsonData = jsonDecode(response.body);
    print(jsonData);

    if (response.statusCode == 200) {
      String idResep = jsonData["idResep"].toString();
      detailResep = ResepModel(
          idResep,
          jsonData["dokter"],
          jsonData["pasien"],
          jsonData["status"],
          jsonData["apoteker"],
          jsonData["listObat"]);
      print(detailResep);
      return detailResep;
    } else {
      return false;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Detail Resep'),
          centerTitle: true,
        ),
        resizeToAvoidBottomInset: false,
        body: SingleChildScrollView(
          child: FutureBuilder(
            future: getDetailResep(),
            builder: (context, snapshot) {
              if (snapshot.data == false) {
                return SafeArea(
                    child: Padding(
                      padding:
                      const EdgeInsets.symmetric(vertical: 20, horizontal: 36),
                      child: SizedBox(
                        width: double.infinity,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: <Widget>[
                            const Text(
                              'Error 404',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                fontSize: 24,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            const SizedBox(
                              height: 20,
                            ),
                            const Text(
                              'Fetch API gagal.',
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                fontSize: 16,
                              ),
                            ),
                            Container(
                              padding: const EdgeInsets.only(top: 12),
                              child: ElevatedButton(
                                onPressed: () {
                                  Navigator.pop(context);
                                },
                                child: const Text('Home'),
                              ),
                            )
                          ],
                        ),
                      ),
                    ));
              } else if (snapshot.data == null) {
                return SafeArea(
                  child: Padding(
                    padding: const EdgeInsets.symmetric(
                        vertical: 20, horizontal: 36),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: const <Widget>[
                        Text(
                          'Detail Resep',
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            fontSize: 24,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        SizedBox(height: 20),
                        LinearProgressIndicator(),
                        SizedBox(height: 20),
                        // Text(
                        //   'Membaca detail resep Anda.',
                        //   textAlign: TextAlign.center,
                        //   style: TextStyle(
                        //     fontSize: 16,
                        //   ),
                        // )
                      ],
                    ),
                  ),
                );
              } else {
                return SafeArea(
                  child: Padding(
                    padding: const EdgeInsets.symmetric(
                        vertical: 24, horizontal: 16),
                    child: SizedBox(
                      width: double.infinity,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                          const Text(
                            'Detail Resep',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              fontSize: 24,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          const SizedBox(
                            height: 24,
                          ),
                          SizedBox(
                            width: double.infinity,
                            child: Card(
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(15.0),
                                side: const BorderSide(
                                  color: Colors.black12,
                                ),
                              ),
                              elevation: 20,
                              shadowColor: Colors.black26,
                              child: Padding(
                                padding: const EdgeInsets.all(16),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: <Widget>[
                                    Text(
                                      'Id Resep: ${detailResep.idResep}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(
                                      height: 8,
                                    ),
                                    Text(
                                      'Nama Dokter: ${detailResep.dokter}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(
                                      height: 8,
                                    ),
                                    Text(
                                      'Nama Pasien: ${detailResep.pasien}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(
                                      height: 8,
                                    ),
                                    Text(
                                      'Status Resep: ${detailResep.status}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(
                                      height: 8,
                                    ),
                                    Text(
                                      'Nama Apoteker: ${detailResep.apoteker}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(
                                      height: 8,
                                    ),
                                    DataTable(
                                        columns: [
                                          DataColumn(
                                              label: Text("Nama Obat",
                                                  style: TextStyle(
                                                      fontWeight:
                                                      FontWeight.bold))),
                                          DataColumn(
                                              label: Text("Jumlah Obat",
                                                  style: TextStyle(
                                                      fontWeight:
                                                      FontWeight.bold))),
                                        ],
                                        rows: detailResep.listObat
                                            .map((listObat) => DataRow(cells: [
                                          DataCell(Text(
                                              "${listObat['obat']}")),
                                          DataCell(Text(
                                              "${listObat['kuantitas']}")),
                                        ]))
                                            .toList())
                                  ],
                                ),
                              ),
                            ),
                          ),
                          Row(
                            children: <Widget>[
                              Expanded(
                                child: Container(
                                    padding: const EdgeInsets.only(top: 12),
                                    child: OutlinedButton(
                                      onPressed: () {
                                        Navigator.pop(context);
                                      },
                                      child: const Text('Back'),
                                    )),
                              ),
                              const SizedBox(
                                width: 12,
                              ),
                              // Expanded(
                              //   child: Container(
                              //       padding: const EdgeInsets.only(top: 12),
                              //       child: OutlinedButton(
                              //         onPressed: () {
                              //           Navigator.pop(context);
                              //           Navigator.push(context,
                              //               MaterialPageRoute(
                              //                   builder: (context) {
                              //                     return DetailResep();
                              //                   }));
                              //         },
                              //         child: const Text('Reload'),
                              //       )),
                              // ),
                            ],
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              }
            },
          ),
        ));
  }
}
