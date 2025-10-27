package com.example.flutter_bunny_video_player

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import io.flutter.plugin.platform.PlatformView
import net.bunny.api.BunnyStreamApi
import net.bunny.bunnystreamplayer.model.PlayerIconSet
import net.bunny.bunnystreamplayer.ui.BunnyStreamPlayer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class BunnyPlayerPlatformView(
    context: Context,
    creationParams: Map<String, Any>?,
) : PlatformView {

    private val rootView: View

    init {
        val wrappedContext =
            ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat_NoActionBar)

        val videoId   = (creationParams?.get("videoId") as? String).orEmpty()
        val accessKey = (creationParams?.get("accessKey") as? String).orEmpty()
        val libraryId = (creationParams?.get("libraryId") as? Number)?.toLong() ?: 0L

        rootView = LayoutInflater.from(wrappedContext)
            .inflate(R.layout.activity_flutter_bunny_video, null)

        // Init SDK 3.x
        BunnyStreamApi.initialize(context.applicationContext, accessKey, libraryId)

        val videoPlayer = rootView.findViewById<BunnyStreamPlayer>(R.id.videoPlayer)
        videoPlayer.iconSet = PlayerIconSet(
            rewindIcon = R.drawable.ic_replay,
            forwardIcon = R.drawable.ic_forward,
            settingsIcon = R.drawable.ic_setting,
            fullscreenOnIcon = R.drawable.ic_fullscreen,
            fullscreenOffIcon = R.drawable.ic_fullscreen_exit,
        )

        if (videoId.isNotEmpty()) {
            // SDK 3.x signature: (videoId, libraryId, videoTitle)
            videoPlayer.playVideo(videoId, libraryId, "")
        }
    }

    override fun getView(): View = rootView
    override fun dispose() {}
}
