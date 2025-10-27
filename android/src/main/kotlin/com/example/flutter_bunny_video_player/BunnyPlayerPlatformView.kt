package com.example.flutter_bunny_video_player

import android.annotation.SuppressLint
import android.content.Context
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import io.flutter.plugin.platform.PlatformView
import net.bunny.bunnystreamapi.BunnyStreamApi
import net.bunny.bunnystreamplayer.model.PlayerIconSet
import net.bunny.bunnystreamplayer.ui.BunnyStreamPlayer

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class BunnyVideoPlatformView(
    context: Context,
    creationParams: Map<String, Any>?,
) : PlatformView {

    private val rootView: View

    init {
        val wrappedContext =
            ContextThemeWrapper(context, androidx.appcompat.R.style.Theme_AppCompat_NoActionBar)

        // Params from Flutter
        val videoId = (creationParams?.get("videoId") as? String).orEmpty()
        val accessKey = (creationParams?.get("accessKey") as? String).orEmpty()
        val libraryId = (creationParams?.get("libraryId") as? Number)?.toLong() ?: 0L
        // keep flags to avoid breaking the API (not used by SDK 3.x right now)
        val _isPortrait = creationParams?.get("isPortrait") as? Boolean ?: false
        val _isScreenshotProtect = creationParams?.get("isScreenShotProtectEnable") as? Boolean ?: false

        // Inflate your layout containing <net.bunny.bunnystreamplayer.ui.BunnyStreamPlayer .../>
        rootView = LayoutInflater.from(wrappedContext)
            .inflate(R.layout.activity_flutter_bunny_video, null)

        // SDK 3.x init: accessKey (can be empty) + libraryId
        BunnyStreamApi.initialize(context.applicationContext, accessKey, libraryId)

        val videoPlayer = rootView.findViewById<BunnyStreamPlayer>(R.id.videoPlayer)

        // Optional custom icons
        videoPlayer.iconSet = PlayerIconSet(
            rewindIcon = R.drawable.ic_replay,
            forwardIcon = R.drawable.ic_forward,
            settingsIcon = R.drawable.ic_setting,
            fullscreenOnIcon = R.drawable.ic_fullscreen,
            fullscreenOffIcon = R.drawable.ic_fullscreen_exit,
        )

        // SDK 3.x play call (no token/expire/referer)
        if (videoId.isNotEmpty()) {
            videoPlayer.playVideo(videoId)
        }
    }

    override fun getView(): View = rootView
    override fun dispose() {}
}
