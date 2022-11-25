import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/myApp.dart';


class KonfirmasiPembayaranPage extends StatelessWidget {
  const KonfirmasiPembayaranPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:
      Center(
        child: Card(
          child: Padding(

            padding: EdgeInsets.all(16.0),
            child: Container(

              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                mainAxisSize: MainAxisSize.min,
                children: <Widget> [

                  const ListTile(
                    title: Text('Pembayaran sebesar Rp.300.000,00'),
                    subtitle: Text('Tekan konfirmasi untuk melanjutkan pembayaran'),
                  ),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      ElevatedButton(
                        child: Text('Konfirmasi'),
                        onPressed: () {
                          Navigator.push(context,
                          MaterialPageRoute(builder: (context) => const MyApp()),
                          );
                        },
                      )
                    ],
                  )
                ]
              ),
            )
        )
        ),
          )
    );
  }
}