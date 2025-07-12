
import 'dart:io';

import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

class BunnyPlayerView extends StatefulWidget {
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
  State<BunnyPlayerView> createState() => _BunnyPlayerViewState();
}

class _BunnyPlayerViewState extends State<BunnyPlayerView> {

  @override
  void initState() {
// SystemChrome.setPreferredOrientations([
//   DeviceOrientation.portraitUp,
//   DeviceOrientation.portraitDown,
//   DeviceOrientation.landscapeLeft,
//   DeviceOrientation.landscapeRight,
// ]);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    const viewType = 'bunny_player_view';

    if(Platform.isMacOS){
    return UiKitView(
      viewType: viewType,
      layoutDirection: TextDirection.ltr,
      creationParams: {
        'accessKey': widget.accessKey,
        'videoId': widget.videoId,
        'libraryId': widget.libraryId,
        'playIconAsset': widget.playIconAsset,
      },
      creationParamsCodec: const StandardMessageCodec(),
    );
    }
    // if(Platform.isAndroid){
    //   return Text("helo");
    // }
    return SizedBox(
      height: 300,
      child: BunnyStreamPlatformView(
              viewType: viewType,
              accessKey: widget.accessKey,
              videoId: widget.videoId,
              libraryId: widget.libraryId,
            ),
    );
  }
}


class BunnyStreamPlatformView extends StatelessWidget {
  final String viewType;
  final String? accessKey;
  final String videoId;
  final int libraryId;

  const BunnyStreamPlatformView({
    super.key,
    required this.viewType,
    required this.videoId,
    required this.libraryId,
    this.accessKey,
  });

  @override
  Widget build(BuildContext context) {
    final Map<String, dynamic> creationParams = {
      'accessKey': accessKey,
      'videoId': videoId,
      'libraryId': libraryId,
    };

    return PlatformViewLink(
      viewType: viewType,
      surfaceFactory: (context, controller) {
        return AndroidViewSurface(
          controller: controller as AndroidViewController,
          gestureRecognizers: const <Factory<OneSequenceGestureRecognizer>>{},
          hitTestBehavior: PlatformViewHitTestBehavior.opaque,
        );
      },
      onCreatePlatformView: (PlatformViewCreationParams params) {
        return PlatformViewsService.initSurfaceAndroidView(
          id: params.id,
          viewType: viewType,
          layoutDirection: TextDirection.ltr,
          creationParams: creationParams,
          creationParamsCodec: const StandardMessageCodec(),
        )
          ..addOnPlatformViewCreatedListener(params.onPlatformViewCreated)
          ..create();
      },
    );
  }
}
