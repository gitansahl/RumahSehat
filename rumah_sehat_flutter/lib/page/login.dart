import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/app.dart';

class LoginPage extends StatelessWidget {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  Future<Map<String, dynamic>> loginAttempt(String username, String password) async {
    var requestBody = {
      "username": username,
      "password": password
    };
    var respond = await http.post(
      "$SERVER_IP/api/authenticate",
      body: jsonEncode(requestBody),
      headers: {"Content-Type":"application/json"}
    );
    if (respond.statusCode == 200) {
      return jsonDecode(respond.body);
    }
    return Map();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Log In"),),

      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget> [
            TextField(
              controller: _usernameController,
              decoration: InputDecoration(
                labelText: 'Username'
              ),
            ),
            TextField(
              controller: _passwordController,
              obscureText: true,
              decoration: InputDecoration(
                labelText: 'Password'
              ),
            ),
            ElevatedButton(onPressed: () async {
              var username = _usernameController.text;
              var password = _passwordController.text;
              var res = await loginAttempt(username, password);
              if (res['jwttoken'] != "") {
                jwt = res['jwttoken'];
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => MyStatefulWidget())
                );
              }
            }, child: Text("Log In")
            )
          ],
        ),
      ),
    );
  }


}