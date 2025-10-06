import 'package:flutter/material.dart';
import 'package:flutter_bunny_video_player/flutter_bunny_video_player.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
  }


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: const Center(
          child:  SizedBox(
            height: 300,
            child: BunnyPlayerView(   
  accessKey: null,
  videoId: "b773d67d-8006-45d5-b22a-731262d61af9",
  libraryId: 267648, 
  expire:  1759778247,
  token: "3cb863a5f95c08595c4773a551b2d37118230a4b5b26cef097c0e283ea1d507c",
  referer: "https://sabitur.klasio.dev",
),
          ),
        ),
      ),
    );
  }
}
