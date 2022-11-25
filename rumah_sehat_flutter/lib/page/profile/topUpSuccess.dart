import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/myApp.dart';
import 'profile.dart';


class TopUpSuccessPage extends StatelessWidget {
  const TopUpSuccessPage({Key? key}) : super(key: key);

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
                    title: Text('Top-Up sebesar RP.200.000,00 Berhasi!'),
                    subtitle: Text('saldo: Rp.700.000,00'),
                  ),

                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      ElevatedButton(
                        child: Text('Kembali ke halaman utama'),
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