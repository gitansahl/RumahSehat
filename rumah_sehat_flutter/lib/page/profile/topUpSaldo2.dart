// import 'dart:convert';
// import 'dart:io';

// import 'package:flutter/gestures.dart';
// import 'package:flutter/material.dart';
// import 'package:flutter/services.dart';
// import 'package:rumah_sehat_flutter/main.dart';
// import 'package:http/http.dart' as http;
// import 'package:rumah_sehat_flutter/page/auth/login.dart';
// import 'package:rumah_sehat_flutter/page/profile/profile.dart';
// import 'package:rumah_sehat_flutter/page/profile/topUpSuccess.dart';

// class ToUpSaldoPage extends StatelessWidget {
//   final TextEditingController _saldoController = TextEditingController();

//   void displayDialog(context, title, text) => showDialog(
//         context: context,
//         builder: (context) =>
//             AlertDialog(title: Text(title), content: Text(text)),
//       );

//   Future<Map<String, dynamic>> topUp(String username, int saldo) async {
//     var requestBody = {
//       "username": username,
//       "saldo": saldo
//       };

//     var respond = await http.post(Uri.parse("$SERVER_IP/api/topUp"),
//         body: jsonEncode(requestBody),
//         headers: {
//           "Content-Type": "application/json",
//           "Authorization": "Bearer $jwt"
//         });

//     if (respond.statusCode == 200) {
//       return jsonDecode(respond.body);
//     }
//     return Map();
//   }

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(
//           title: Text("Top-Up Saldo"),
//         ),
//         body: SingleChildScrollView(
//           child: Padding(
//             padding: const EdgeInsets.all(8.0),
//             child: Column(
//               children: <Widget>[
//                 TextField(
//                   controller: _saldoController,
//                   decoration: InputDecoration(
//                     border: OutlineInputBorder(),
//                     hintText: 'Masukkan nominal Top-Up',
//                   ),
//                   keyboardType: TextInputType.number,
//                   inputFormatters: <TextInputFormatter>[
//                     FilteringTextInputFormatter.digitsOnly
//                   ],
//                 ),
//                 ElevatedButton(
//                     onPressed: () async {
//                       try {
//                         // int.parse(_saldoController.text);
//                         int saldo = int.parse(_saldoController.text);
//                         String username = 



//                       var res = await topUp(username, saldo);
//                       if (res['status'] == 200) {
//                         displayDialog(context, "Success", "Top-Up Success");
//                         Future.delayed(const Duration(seconds: 2));
//                         Navigator.pushReplacement(
//                             context,
//                             MaterialPageRoute(
//                                 builder: (context) => ProfilePage()));
//                       } else {
//                         displayDialog(context, "Error", res['data']);
//                       }
//                       } catch (e) {
//                         String saldoStr = _saldoController.text;
//                         displayDialog(
//                             context, "Error", "top-up saldo tidak boleh kosong");
//                       }
//                       // int saldo = int.parse(_saldoController.text);



//                       // var res = await topUp(saldo);
//                       // if (res['status'] == 200) {
//                       //   displayDialog(context, "Success", "Top-Up Success");
//                       //   Future.delayed(const Duration(seconds: 2));
//                       //   Navigator.pushReplacement(
//                       //       context,
//                       //       MaterialPageRoute(
//                       //           builder: (context) => TopUpSuccessPage()));
//                       // } else {
//                       //   displayDialog(context, "Error", res['data']);
//                       // }
//                     },
//                     child: const Text("Confirm")),
//               ],
//             ),
//           ),
//         ));
//   }
// }
