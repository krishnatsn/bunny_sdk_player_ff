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
  final String? token;
  final int? expire;
  final String? referer;
  final bool isPortrait;
  final bool isScreenShotProtectEnable;
  const BunnyPlayerView({
    super.key,
    required this.accessKey,
    required this.videoId,
    required this.libraryId,
    this.token,
    this.referer,
    this.expire,
    this.isPortrait = false,
    this.isScreenShotProtectEnable = false,
  });

  @override
  State<BunnyPlayerView> createState() => _BunnyPlayerViewState();
}

class _BunnyPlayerViewState extends State<BunnyPlayerView> {
  @override
  void initState() {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
      DeviceOrientation.landscapeLeft,
      DeviceOrientation.landscapeRight,
    ]);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    const viewType = 'bunny_player_view';

    if (Platform.isAndroid) {
      return BunnyStreamAndroidPlatformView(
        viewType: viewType,
        accessKey: widget.accessKey,
        videoId: widget.videoId,
        libraryId: widget.libraryId,
        token: widget.token,
        expire: widget.expire,
        referer: widget.referer,
        isPortrait: widget.isPortrait,
        isScreenShotProtectEnable: widget.isScreenShotProtectEnable,
      );
    }
    return const SizedBox();
  }
}

class BunnyStreamAndroidPlatformView extends StatelessWidget {
  final String viewType;
  final String? accessKey;
  final String videoId;
  final int libraryId;
  final String? token;
  final int? expire;
  final String? referer;
  final bool isPortrait;
  final bool isScreenShotProtectEnable;
  const BunnyStreamAndroidPlatformView({
    super.key,
    required this.viewType,
    required this.videoId,
    required this.libraryId,
    this.accessKey,
    this.token,
    this.expire,
    this.referer,
    required this.isPortrait,
    required this.isScreenShotProtectEnable,
  });

  @override
  Widget build(BuildContext context) {
    final Map<String, dynamic> creationParams = {
      'accessKey': accessKey,
      'videoId': videoId,
      'libraryId': libraryId,
      'token': token,
      'expire': expire,
      'referer': referer,
      'isPortrait': isPortrait,
      'isScreenShotProtectEnable': isScreenShotProtectEnable,
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
