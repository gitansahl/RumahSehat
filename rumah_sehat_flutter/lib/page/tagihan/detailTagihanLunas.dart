import 'package:flutter/material.dart';

class DetailTagihanLunasPage extends StatelessWidget {
  const DetailTagihanLunasPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Detail Tagihan'),
      ),
      body: Column(
        children: [
          ListTile(
            title: Text('Code Tagihan'),
            subtitle: Text('1234',),
          ),

          ListTile(
            title: Text('Code Appointment'),
            subtitle: Text('1234',),
          ),
          ListTile(
            title: Text('Tanggal Dibuat'),
            subtitle: Text('5 Frebruari 2022',),
          ),

          ListTile(
            title: Text('Tanggal Bayar'),
            subtitle: Text('9 Frebruari 2022',),
          ),

          ListTile(
            title: Text('Jumlah Tagihan'),
            subtitle: Text('Rp.200.000,000',),
          ),

          ListTile(
            title: Text('Status'),
            subtitle: Text('LUNAS', style: TextStyle(color: Colors.green),),
          ),
          
        ],
      ),
    );
  }
}