import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:rumah_sehat_flutter/page/auth/login.dart';
import 'topUpSaldo.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('Profile'),
        ),
        body: Column (children: [

          Center(
            child: Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget> [
                  const ListTile(
                    title: Text('Username'),
                    subtitle: Text('budibudi'),
                  ),
                ]),
            ),
          ),

          Center(
            child: Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget> [
                  const ListTile(
                    title: Text('Nama'),
                    subtitle: Text('Budi Budi'),
                  ),
                ]),
            ),
          ),

          Center(
            child: Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget> [
                  const ListTile(
                    title: Text('Email'),
                    subtitle: Text('budi@gmial.com'),
                  ),
                ]),
            ),
          ),

          Center(
            child: Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget> [
                  const ListTile(
                    title: Text('Saldo'),
                    subtitle: Text('Rp.500.000,00'),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      TextButton(
                        child: Text('Top-Up'),
                        onPressed: () {
                          Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => const ToUpSaldoPage()),
                          );
                        }
                      )
                    ],
                  )
                ]),
            ),
          ),
          Center(
            child: ElevatedButton(
              onPressed: () async {
                jwt = "";
                Navigator.pushAndRemoveUntil(context,
                    MaterialPageRoute(builder: (context) => LoginPage()),
                    ModalRoute.withName('/')
                );
              },
              child: Text("Log Out"),
            ),
          )

        ],)
    );
  }
}
