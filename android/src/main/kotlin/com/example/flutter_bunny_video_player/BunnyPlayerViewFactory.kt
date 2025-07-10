package com.example.flutter_bunny_video_player

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.ComposeView
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class BunnyPlayerViewFactory (
    private val messenger: BinaryMessenger,
    private val activityProvider: () -> ComponentActivity? // Add a lambda to get the activity
) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    override fun create(context: Context, id: Int, args: Any?): PlatformView {
        val creationParams = args as? Map<String, Any>
        val methodChannel = MethodChannel(messenger, "my_compose_view_channel_$id")
        return BunnyVideoPlatformView(context, id, creationParams, methodChannel, activityProvider()) // Pass the activity
    }
}
