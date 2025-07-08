
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'flutter_bunny_video_player_platform_interface.dart';

class FlutterBunnyVideoPlayer {
  Future<String?> getPlatformVersion() {
    return FlutterBunnyVideoPlayerPlatform.instance.getPlatformVersion();
  }
}
class BunnyPlayerView extends StatelessWidget {
  final String videoId;

  const BunnyPlayerView({super.key, required this.videoId});

  @override
  Widget build(BuildContext context) {
    const viewType = 'bunny_player_view';

    if (Platform.isIOS) {
      return SizedBox(
        width: 300,
        height: 200,
        child: UiKitView(
          viewType: viewType,
          layoutDirection: TextDirection.ltr,
          creationParams: {'videoId': videoId},
          creationParamsCodec: const StandardMessageCodec(),
        ),
      );
    }

    return const Text("BunnyPlayer only supports iOS.");
  }
}