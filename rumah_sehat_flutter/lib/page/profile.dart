import 'package:flutter/material.dart';
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
        body: Column(
          children: [
            Text('Username'),
            Text('Nama'),
            Text('Email'),
            Text('Saldo'),
            ElevatedButton(
              child: const Text('Top Up Saldo'),
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const ToUpSaldoPage()),
                );
              },
            ),
          ],
        ));
  }
}
