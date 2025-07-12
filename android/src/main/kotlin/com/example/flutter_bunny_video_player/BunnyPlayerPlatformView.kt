package com.example.flutter_bunny_video_player

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import androidx.lifecycle.Lifecycle
import net.bunnystream.api.BunnyStreamApi
import net.bunnystream.bunnystreamplayer.ui.BunnyStreamPlayer


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class BunnyVideoPlatformView(
    context: Context,
    creationParams: Map<String, Any>?,
) : PlatformView {

    private val rootView: View

    init {
        val themedContext = ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat)

        rootView = LayoutInflater.from(themedContext)
            .inflate(R.layout.activity_flutter_bunny_video, null)

        // Optional: find and configure the BunnyStreamPlayer view
        val videoPlayer = rootView.findViewById<BunnyStreamPlayer>(R.id.videoPlayer)
        BunnyStreamApi.initialize(context.applicationContext, "", 759)
        videoPlayer.playVideo("eb1c4f77-0cda-46be-b47d-1118ad7c2ffe", 759)
    }

    override fun getView(): View = rootView

    override fun dispose() {
        // Handle any cleanup here if necessary
    }
}