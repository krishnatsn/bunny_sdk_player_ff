package com.example.flutter_bunny_video_player

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import io.flutter.plugin.platform.PlatformView
import net.bunny.api.BunnyStreamApi
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

        val videoId   = (creationParams?.get("videoId") as? String).orEmpty()
        val accessKey = (creationParams?.get("accessKey") as? String).orEmpty() // ok to be empty for public videos
        val libraryId = (creationParams?.get("libraryId") as? Number)?.toLong() ?: 0L

        rootView = LayoutInflater.from(wrappedContext)
            .inflate(R.layout.activity_flutter_bunny_video, null)

        // Init SDK 3.x
        BunnyStreamApi.initialize(context.applicationContext, accessKey, libraryId)

        val videoPlayer = rootView.findViewById<BunnyStreamPlayer>(R.id.videoPlayer)
       

        if (videoId.isNotEmpty()) {
            // --- Auto-resume: seek to last saved position (if any)
            val prefs = context.getSharedPreferences("bunny_resume", Context.MODE_PRIVATE)
            val resumeKey = "pos_${libraryId}_${videoId}"
            val savedPos = prefs.getLong(resumeKey, 0L)
            if (savedPos > 0L) {
                // try player.seekTo(ms); fall back to inner exoplayer if needed
                try {
                    val seek = videoPlayer.javaClass.getMethod("seekTo", java.lang.Long.TYPE)
                    seek.invoke(videoPlayer, savedPos)
                } catch (_: Throwable) {
                    try {
                        val playerField = videoPlayer.javaClass.getDeclaredField("player")
                        playerField.isAccessible = true
                        val exo = playerField.get(videoPlayer)
                        val seekExo = exo.javaClass.getMethod("seekTo", java.lang.Long.TYPE)
                        seekExo.invoke(exo, savedPos)
                    } catch (_: Throwable) { /* ignore */ }
                }
            }

            // Play (SDK 3.x signature: videoId, libraryId, videoTitle)
            videoPlayer.playVideo(videoId, libraryId, "")

            // --- Auto-save: poll current position every 2s
            val handler = Handler(Looper.getMainLooper())
            val saver = object : Runnable {
                override fun run() {
                    try {
                        var pos = 0L
                        try {
                            val getPos = videoPlayer.javaClass.getMethod("getCurrentPosition")
                            pos = (getPos.invoke(videoPlayer) as? Long) ?: 0L
                        } catch (_: Throwable) {
                            try {
                                val playerField = videoPlayer.javaClass.getDeclaredField("player")
                                playerField.isAccessible = true
                                val exo = playerField.get(videoPlayer)
                                val getPos2 = exo.javaClass.getMethod("getCurrentPosition")
                                pos = (getPos2.invoke(exo) as? Long) ?: 0L
                            } catch (_: Throwable) { /* ignore */ }
                        }
                        prefs.edit().putLong(resumeKey, pos).apply()
                    } finally {
                        handler.postDelayed(this, 2000)
                    }
                }
            }
            handler.postDelayed(saver, 2000)
        }
    }

    override fun getView(): View = rootView
    override fun dispose() {}
}
