import 'dart:convert';
import 'dart:io';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/app.dart';
import 'package:rumah_sehat_flutter/page/auth/register.dart';

class LoginPage extends StatelessWidget {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  void displayDialog(context, title, text) => showDialog(
    context: context,
    builder: (context) =>
        AlertDialog(
            title: Text(title),
            content: Text(text)
        ),
  );

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
    return jsonDecode(respond.body);
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
              try {
                jwt = res['data']['jwttoken'];
                Navigator.pushReplacement(context,
                    MaterialPageRoute(builder: (context) => MyStatefulWidget())
                );
              } catch (e) {
                displayDialog(context, "Error", res['data']);
              }
            }, child: Text("Log In")
            ),
            RichText(
              text: TextSpan(
                children: [
                  TextSpan(
                    text: "Didn't have an account? ",
                    style: TextStyle(
                      color: Colors.black
                    )
                  ),
                  TextSpan(
                    text: "Register",
                      style: TextStyle(
                          color: Colors.blue
                      ),
                      recognizer: TapGestureRecognizer()..onTap = () {
                        Navigator.pushReplacement(context,
                            MaterialPageRoute(builder: (context) => RegisterPage())
                        );
                      }
                  )
                ]
              ),
            )
          ],
        ),
      ),
    );
  }
}