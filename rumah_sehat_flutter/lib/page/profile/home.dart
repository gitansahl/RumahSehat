import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/main.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  Future<String> get request async {
    var respond = await http.get(
        Uri.parse("$SERVER_IP/api/hello"),
        headers: {"Content-Type":"application/json",
          "Authorization" : "Bearer $jwt"}
    );
    if (respond.statusCode == 200) {
      return respond.body;
    }
    return "";
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: request,
        builder: (context, snapshot) {
          if (!snapshot.hasData) return CircularProgressIndicator();
          else {
            return Scaffold(
              body: Center (
                child: Text(snapshot.data.toString()),
              ),
            );
          }
        }
    );
  }
}
