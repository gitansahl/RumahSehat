import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:rumah_sehat_flutter/page/auth/login.dart';
import 'topUpSaldo.dart';
import 'package:http/http.dart' as http;

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

Future<Profile> get request async {
  final respond = await http.get(Uri.parse("$SERVER_IP/api/profile"), headers: {
    "Content-Type": "application/json",
    "Authorization": "Bearer $jwt"
  });

  if (respond.statusCode == 200) {
    return Profile.fromJson(jsonDecode(respond.body));
  } else {
    throw Exception('Failed to load profile');
  }
}

class Profile {
  final String username;
  final String nama;
  final String email;
  final int saldo;
  final int umur;

  const Profile({
    required this.username,
    required this.nama,
    required this.email,
    required this.saldo,
    required this.umur,
  });

  factory Profile.fromJson(Map<String, dynamic> json) {
    return Profile(
      username: json['username'] as String,
      nama: json['nama'] as String,
      email: json['email'] as String,
      saldo: json['saldo'] as int,
      umur: json['umur'] as int,
    );
  }
}

class _ProfilePageState extends State<ProfilePage> {
  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: request,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            Profile profile = snapshot.data as Profile;
            // String profileUsername = profile.username;

            return Scaffold(
                appBar: AppBar(
                  title: const Text('Profile'),
                ),
                body: Column(
                  children: [
                    Center(
                      child: Card(
                        child: Column(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ListTile(
                                title: Text('Username'),
                                subtitle: Text(profile.username),
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
                                title: Text('Nama'),
                                subtitle: Text(profile.nama),
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
                                title: Text('Umur'),
                                subtitle: Text(profile.umur.toString()),
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
                                title: Text('Email'),
                                subtitle: Text(profile.email),
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
                                title: Text('Saldo'),
                                subtitle: Text(profile.saldo.toString()),
                              ),
                              Row(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  TextButton(
                                      child: Text('Top-Up'),
                                      onPressed: () {
                                        Navigator.push(
                                          context,
                                          MaterialPageRoute(
                                              builder: (context) =>
                                                  ToUpSaldoPage()),
                                        );
                                      })
                                ],
                              )
                            ]),
                      ),
                    ),
                    Center(
                      child: ElevatedButton(
                        onPressed: () async {
                          jwt = "";
                          Navigator.pushAndRemoveUntil(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => LoginPage()),
                              ModalRoute.withName('/'));
                        },
                        child: Text("Log Out"),
                      ),
                    )
                  ],
                )
                );
          } else if (snapshot.hasError) {
            return Text('${snapshot.error}');
          }

          return CircularProgressIndicator();
        });
  }
}
