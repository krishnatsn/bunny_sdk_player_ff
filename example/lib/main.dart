import 'package:flutter/material.dart';
import 'package:bunny_sdk_player_ff/flutter_bunny_video_player.dart';

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
  videoId: "1e5b2551-adde-4ee9-b5b3-876c58ebfd33",
  libraryId: 316762, 
  expire:  20250922120000,
  token: "db6ba6794ebdccedfc9e7719ce6eb4e8700b2ee4b0159fb3b8b311fd99ade91f",
  referer: "https://sabitur.klasio.dev",
  isPortrait: true,
),
          ),
        ),
      ),
    );
  }
}
