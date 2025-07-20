# Flutter Bunny Video Player

A Flutter plugin that provides a native video player implementation for Android using Bunny.net streaming services. This plugin allows you to easily integrate and play videos from your Bunny Stream library in your Flutter applications.

## Features

- Native Android video player implementation
- Seamless integration with Bunny.net streaming services
- Support for both portrait and landscape orientations

## Installation

Add this to your package's `pubspec.yaml` file:

```yaml
dependencies:
  flutter_bunny_video_player: ^1.0.0
```

Or install via command line:

```bash
flutter pub add flutter_bunny_video_player
```

Add the following Gradle configuration to your Android:

1.Go to your github profile => [Developer settings] => [Personal access token] => [Generate new token] on github.com
2.Check read:packages
3.Click Generate token
4.Copy the generated token
5.Add below lines to your ~/.gradle/gradle.properties (the path may be different on Windows) or export as environment variables:

  ```
  GITHUB_ACTOR={your github id}
  GITHUB_TOKEN={the generated token}
  ```
Merge below lines to your /settings.gradle (If you are using the recent version of Android Gradle Plugin)
  ```
  dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/BunnyWay/bunny-stream-android")
            credentials {
                // Use environment variables for GitHub Packages authentication
                // Ensure GITHUB_ACTOR and GITHUB_TOKEN are set in your environment
                // or gradle.properties file
                username = providers.gradleProperty("gpr.user").orNull
                    ?: providers.environmentVariable("GITHUB_ACTOR").orNull
                    ?: System.getenv("GITHUB_ACTOR")
                    ?: ""
                password = providers.gradleProperty("gpr.key").orNull
                    ?: providers.environmentVariable("GITHUB_TOKEN").orNull
                    ?: System.getenv("GITHUB_TOKEN")
                    ?: ""
            }
        }
    }
  }
  ```
  or to your /build.gradle
  ```
  allprojects {
      repositories {
        maven {
            url = uri("https://maven.pkg.github.com/BunnyWay/bunny-stream-android")
            credentials {
                // Use environment variables for GitHub Packages authentication
                // Ensure GITHUB_ACTOR and GITHUB_TOKEN are set in your environment 
                // or gradle.properties file
                username = providers.gradleProperty("gpr.user").orNull
                    ?: providers.environmentVariable("GITHUB_ACTOR").orNull
                    ?: System.getenv("GITHUB_ACTOR")
                    ?: ""
                password = providers.gradleProperty("gpr.key").orNull
                    ?: providers.environmentVariable("GITHUB_TOKEN").orNull
                    ?: System.getenv("GITHUB_TOKEN")
                    ?: ""
            }
        }
    }
  }
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
  ),
)
```

### Required Parameters

- `videoId` (String): The ID of the video you want to play from your Bunny Stream library
- `libraryId` (int): Your Bunny Stream library ID

### Optional Parameters

- `accessKey` (String?): Your Bunny Stream access key for secured videos

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


