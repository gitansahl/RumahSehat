import 'dart:convert';
import 'dart:io';

import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:http/http.dart' as http;
import 'package:rumah_sehat_flutter/page/auth/login.dart';

class RegisterPage extends StatelessWidget {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _namaController = TextEditingController();
  final TextEditingController _umurController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _passwordConfirmController = TextEditingController();

  void displayDialog(context, title, text) => showDialog(
    context: context,
    builder: (context) =>
        AlertDialog(
            title: Text(title),
            content: Text(text)
        ),
  );

  Future<Map<String, dynamic>> registerAttempt(
      String username,
      String email,
      String nama,
      int umur,
      String password) async {

    var requestBody = {
      "email" : email,
      "nama" : nama,
      "password" : password,
      "username" : username,
      "umur" : umur
    };

    var respond = await http.post(
        "$SERVER_IP/api/user/registration",
        body: jsonEncode(requestBody),
        headers: {"Content-Type":"application/json"}
    );

    return jsonDecode(respond.body);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Register"),),

      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: <Widget> [
              TextField(
                controller: _usernameController,
                decoration: const InputDecoration(
                    labelText: 'Username'
                ),
              ),
              TextField(
                controller: _namaController,
                decoration: const InputDecoration(
                    labelText: 'Nama'
                ),
              ),
              TextField(
                controller: _emailController,
                decoration: const InputDecoration(
                    labelText: 'Email'
                ),
              ),
              TextField(
                controller: _umurController,
                decoration: const InputDecoration(
                    labelText: 'Umur'
                ),
              ),
              TextField(
                controller: _passwordController,
                obscureText: true,
                decoration: const InputDecoration(
                    labelText: 'Password'
                ),
              ),
              TextField(
                controller: _passwordConfirmController,
                obscureText: true,
                decoration: const InputDecoration(
                    labelText: 'Password Confirm'
                ),
              ),
              ElevatedButton(onPressed: () async {
                try {
                  int.parse(_umurController.text);
                } catch (e) {
                  displayDialog(context, "Error", "Umur harus berupa angka.");
                }
                int umur = int.parse(_umurController.text);
                var username = _usernameController.text;
                var password = _passwordController.text;
                var nama = _namaController.text;
                var email = _emailController.text;
                var passwordConfirm = _passwordConfirmController.text;
                if (password != passwordConfirm) {
                  displayDialog(context, "Error", "Password Confirm tidak cocok dengan password.");
                }
                var res = await registerAttempt(username, email, nama, umur, password);
                if (res['status'] == 200) {
                  displayDialog(context, "Success", "Registration Success");
                  Future.delayed(const Duration(seconds: 2));
                  Navigator.pushReplacement(context,
                      MaterialPageRoute(builder: (context) => LoginPage())
                  );
                } else {
                  displayDialog(context, "Error", res['data']);
                }
              }, child: const Text("Register")
              ),
              RichText(
                text: TextSpan(
                    children: [
                      const TextSpan(
                          text: "Already have an account? ",
                          style: TextStyle(
                              color: Colors.black
                          )
                      ),
                      TextSpan(
                          text: "Login",
                          style: const TextStyle(
                              color: Colors.blue
                          ),
                          recognizer: TapGestureRecognizer()..onTap = () {
                            Navigator.pushReplacement(context,
                                MaterialPageRoute(builder: (context) => LoginPage())
                            );
                          }
                      )
                    ]
                ),
              )
            ],
          ),
        ),
      )
    );
  }
}