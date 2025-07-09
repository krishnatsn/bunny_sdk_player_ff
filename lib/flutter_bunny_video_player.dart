
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'flutter_bunny_video_player_platform_interface.dart';

class FlutterBunnyVideoPlayer {
  Future<String?> getPlatformVersion() {
    return FlutterBunnyVideoPlayerPlatform.instance.getPlatformVersion();
  }
}
class BunnyPlayerView extends StatelessWidget {
  final String? accessKey;
  final String videoId;
  final int libraryId;
  final String? playIconAsset;

  const BunnyPlayerView({
    super.key,
    required this.accessKey,
    required this.videoId,
    required this.libraryId,
    this.playIconAsset,
  });

  @override
  Widget build(BuildContext context) {
    const viewType = 'bunny_player_view';

    return UiKitView(
      viewType: viewType,
      layoutDirection: TextDirection.ltr,
      creationParams: {
        'accessKey': accessKey,
        'videoId': videoId,
        'libraryId': libraryId,
        'playIconAsset': playIconAsset,
      },
      creationParamsCodec: const StandardMessageCodec(),
    );
  }
}
