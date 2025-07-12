package com.example.flutter_bunny_video_player

import androidx.activity.ComponentActivity
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
/** FlutterBunnyVideoPlayerPlugin */
class FlutterBunnyVideoPlayerPlugin: FlutterPlugin, ActivityAware { // Implement ActivityAware
  private lateinit var channel: MethodChannel
  private var activity: ComponentActivity? = null // To hold a reference to the host activity

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    flutterPluginBinding
      .platformViewRegistry
      .registerViewFactory(
        "bunny_player_view",
        BunnyPlayerViewFactory()
      )
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    // channel.setMethodCallHandler(null) // Unset method call handler if used
  }

  // --- ActivityAware methods ---
  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    // Cast to ComponentActivity to ensure it implements LifecycleOwner and SavedStateRegistryOwner
    if (binding.activity is ComponentActivity) {
      activity = binding.activity as ComponentActivity
    } else {
      // Handle cases where the host activity might not be a ComponentActivity
      // This is less common in modern Android/Flutter setups, but possible.
      // You might need to find a different way to get a LifecycleOwner.
      // For most cases, Flutter's default Activity will be a ComponentActivity.
      println("Warning: Host activity is not a ComponentActivity. ViewTreeLifecycleOwner might not be set correctly.")
    }
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    if (binding.activity is ComponentActivity) {
      activity = binding.activity as ComponentActivity
    }
  }

  override fun onDetachedFromActivity() {
    activity = null
  }
}

