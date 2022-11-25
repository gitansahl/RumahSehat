import 'package:flutter/material.dart';

class ToUpSaldoPage extends StatelessWidget {
  const ToUpSaldoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Top Up Saldo'),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: const Text('Confirm'),
        ),
      ),
    );
  }
}