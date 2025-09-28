# Flutter Bunny Video Player

A Flutter plugin that provides a native video player implementation for Android using Bunny.net streaming services. This plugin allows you to easily integrate and play videos from your Bunny Stream library in your Flutter applications.

## Platform Support

| Android | iOS | MacOS | Web | Linux | Windows |
|---------|-----|-------|-----|--------|----------|
| ✅      | ❌   | ❌     | ❌   | ❌      | ❌        |

## Features

- Native Android video player implementation
- Seamless integration with Bunny.net streaming services
- Support for both portrait and landscape orientations

## Installation

Add this to your package's `pubspec.yaml` file:

```yaml
dependencies:
  flutter_bunny_video_player: ^1.0.3
```

Or install via command line:

```bash
flutter pub add flutter_bunny_video_player
```

Add the following Gradle configuration to your Android:

  android/build.gradle.kt
  ```
  allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url =uri("https://jitpack.io") } // add this line
    }
  } 
  ```
  update the minsdk 
  ```
  minSdk = 26
  ```
  `AndroidManifest.xml`
  ```
  <application
    ....
    android:theme="@style/Theme.AppCompat.Light.NoActionBar">
  ```
  



## Usage

1. First, import the package in your Dart file:

```dart
import 'package:flutter_bunny_video_player/flutter_bunny_video_player.dart';
```

2. Use the `BunnyPlayerView` widget in your Flutter app:

```dart
SizedBox(
  height: 300, // Set desired height
  child: BunnyPlayerView(
    accessKey: 'your_bunny_stream_access_key',  // Optional
    videoId: 'your_video_id',                    // Required
    libraryId: your_library_id,                  // Required
    token: 'your_secure_token',                  // Optional - for secured videos
    expire: 20250922120000,                      // Optional - token expiration timestamp
  ),
)
```

### Required Parameters

- `videoId` (String): The ID of the video you want to play from your Bunny Stream library
- `libraryId` (int): Your Bunny Stream library ID

### Optional Parameters

- `accessKey` (String?): Your Bunny Stream access key for secured videos
- `token` (String?): Secure token for authenticated video access
- `expire` (int?): Token expiration timestamp (Unix timestamp format)

### Token Authentication

For secured videos that require authentication, you can use the `token` and `expire` parameters:

```dart
BunnyPlayerView(
  videoId: "your-video-id",
  libraryId: your_library_id,
  token: "your_secure_token",     // Authentication token
  expire: 20250922120000,         // Token expiration timestamp
)
```

**Note:** When using token authentication, the `accessKey` parameter is not required as the token provides the necessary authentication.

### Sizing

The video player view needs to be wrapped in a container with specific dimensions (like `SizedBox` or `Container`) to properly display the video player.

## Complete Example

```dart
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
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Bunny Video Player Example'),
        ),
        body: const Center(
          child: SizedBox(
            height: 300,
            child: BunnyPlayerView(
              accessKey: null,  // Set to your access key if needed
              videoId: "your-video-id",
              libraryId: your_library_id,
              token: null,      // Set to your secure token if needed
              expire: null,     // Set to token expiration timestamp if using token
            ),
          ),
        ),
      ),
    );
  }
}
```

## Requirements

- Android 8.0 (API level 26)
- Internet permission in Android Manifest

## Notes

- This plugin currently only supports Android platform
- The video player automatically handles device orientation changes
- Wrap the `BunnyPlayerView` in a sized container for proper display
- Ensure your Android app has the necessary permissions and configurations in the manifest file


