import 'package:flutter/material.dart';
import 'topUpSuccess.dart';


class ToUpSaldoPage extends StatelessWidget {
  const ToUpSaldoPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Top Up Saldo'),
      ),
      body: 
      Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        const Padding(
          padding: EdgeInsets.symmetric(horizontal: 8, vertical: 16),
          child: TextField(
            decoration: InputDecoration(
              border: OutlineInputBorder(),
              hintText: 'Masukkan nominal Top-Up',
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 16),
          child: ElevatedButton(onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const TopUpSuccessPage())
            );
          },
          child: const Text('Confirm'),
          )
        ),
      ],
    )
      // Center(
      //   child: ElevatedButton(
      //     onPressed: () {
      //       Navigator.push(
      //       context,
      //       MaterialPageRoute(builder: (context) => const TopUpSuccessPage()),
      //       );
      //     },
      //     child: const Text('Confirm'),
      //   ),
      // ),
    );
  }
}