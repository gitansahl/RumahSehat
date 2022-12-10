import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:rumah_sehat_flutter/page/app.dart';
import 'package:rumah_sehat_flutter/page/auth/login.dart';
import 'package:rumah_sehat_flutter/page/tagihan/daftarTagihan.dart';
import 'package:rumah_sehat_flutter/page/profile/home.dart';
import 'package:rumah_sehat_flutter/page/profile/profile.dart';


class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  static const String _title = 'Flutter Code Sample';

  Future<String> get jwtOrEmpty async {
    var token = await jwt;
    if (token == null) return "";
    return token;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rumah Sehat',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: FutureBuilder(
          future: jwtOrEmpty,
          builder: (context, snapshot) {
            if(!snapshot.hasData) return CircularProgressIndicator();
            if(snapshot.data != "") {
              var jwt = snapshot.data;

              if(jwt == "") {
                return LoginPage();
              } else {
                return MyStatefulWidget();
              }
            } else {
              return LoginPage();
            }
          }
      ),
    );
  }
}
