
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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

    if(Platform.isMacOS){
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
    // if(Platform.isAndroid){
    //   return Text("helo");
    // }
    return AndroidView(
            viewType: viewType,
            creationParams: {
        'accessKey': accessKey,
        'videoId': videoId,
        'libraryId': libraryId,
      },
            creationParamsCodec: const StandardMessageCodec(),
          );
  }
}
