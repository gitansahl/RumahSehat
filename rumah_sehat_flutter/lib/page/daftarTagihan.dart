import 'package:flutter/material.dart';

class DaftarTagihanPage extends StatefulWidget {
  const DaftarTagihanPage({Key? key}) : super(key: key);

  @override
  State<DaftarTagihanPage> createState() => _DaftarTagihanPageState();
}

class _DaftarTagihanPageState extends State<DaftarTagihanPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Daftar Tagihan'),
      ),
      body: Center(
        child: Text('Daftar Tagihan Page'),
      ),
    );
  }
}
