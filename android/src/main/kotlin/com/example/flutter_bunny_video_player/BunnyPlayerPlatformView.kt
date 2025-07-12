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
        // Extract creation params
        val videoId = creationParams?.get("videoId") as? String
        val accessKey = creationParams?.get("accessKey") as? String ?: ""
        val libraryId = (creationParams?.get("libraryId") as? Number)?.toLong() ?: 0L
        val playIconAsset = creationParams?.get("playIconAsset") as? String
        rootView = LayoutInflater.from(themedContext)
            .inflate(R.layout.activity_flutter_bunny_video, null)
        BunnyStreamApi.initialize(context, accessKey, libraryId)
        // Optional: find and configure the BunnyStreamPlayer view
        val videoPlayer = rootView.findViewById<BunnyStreamPlayer>(R.id.videoPlayer)

        videoPlayer.playVideo(videoId!!, libraryId)
    }

    override fun getView(): View = rootView

    override fun dispose() {
        // Handle any cleanup here if necessary
    }
}