import 'package:flutter/material.dart';
import 'detailTagihanLunas.dart';
import 'detailTagihan.dart';


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
        child: ListView(
          children: <Widget> [
            ListTile(
              title: Text('Code tagihan: 1234'),
              subtitle: Text('Jumlah Tagihan: Rp.300.000,00',
                              style: TextStyle(
                            color: Colors.green),),
              trailing: IconButton(
                icon: const Icon(Icons.keyboard_arrow_right),
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const DetailTagihanLunasPage()),
                  );
                },
              ),
            ),
            ListTile(
              title: Text('Code tagihan: 4321'),
              subtitle: Text('Jumlah Tagihan: Rp.300.000,00',
                              style: TextStyle(
                            color: Colors.red),),
              trailing: IconButton(
                icon: const Icon(Icons.keyboard_arrow_right),
                onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => const DetailTagihanPage()),
                    );
                  },
              ),
            ),
          ]
        ),
      ),
    );
  }
}
