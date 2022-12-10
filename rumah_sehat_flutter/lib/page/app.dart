import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:rumah_sehat_flutter/main.dart';
import 'package:rumah_sehat_flutter/page/appointment/daftar_appoitnment.dart';
import 'package:rumah_sehat_flutter/page/tagihan/daftarTagihan.dart';
import 'package:rumah_sehat_flutter/page/profile/home.dart';
import 'package:rumah_sehat_flutter/page/profile/profile.dart';
import 'package:http/http.dart' as http;

class MyStatefulWidget extends StatefulWidget {
  const MyStatefulWidget({Key? key}) : super(key: key);
  @override
  State<MyStatefulWidget> createState() => _MyStatefulWidgetState();
}

class _MyStatefulWidgetState extends State<MyStatefulWidget> {
  int _selectedIndex = 0;
  static const TextStyle optionStyle =
  TextStyle(fontSize: 30, fontWeight: FontWeight.bold);
  static const List<Widget>_widgetOptions=[HomePage(), AppointmentPage(), DaftarTagihanPage(), ProfilePage()];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
            backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.schedule),
            label: 'Appointment',
            backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.request_page),
            label: 'Daftar Tagihan',
            backgroundColor: Colors.blue,
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Profile',
            backgroundColor: Colors.blue,


          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.white,
        onTap: _onItemTapped,

      ),
    );
  }
}