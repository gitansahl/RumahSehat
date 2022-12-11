import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/myApp.dart';
import 'konfirmasiPembayaran.dart';

class DetailTagihanPage extends StatelessWidget {
  const DetailTagihanPage({Key? key}) : super(key: key);

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
            subtitle: Text('4321',),
          ),

          ListTile(
            title: Text('Code Appointment'),
            subtitle: Text('4321',),
          ),
          ListTile(
            title: Text('Tanggal Dibuat'),
            subtitle: Text('22 November 2022',),
          ),

          ListTile(
            title: Text('Jumlah Tagihan'),
            subtitle: Text('Rp.200.000,000',),
          ),

          ListTile(
            title: Text('Status'),
            subtitle: Text('BELUM LUNAS', style: TextStyle(color: Colors.red),),
          ),

          ElevatedButton(
            onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const KonfirmasiPembayaranPage()),
                  );
                },
            child: const Text('Bayar'),
          ),
        ],
      ),
    );
  }
}