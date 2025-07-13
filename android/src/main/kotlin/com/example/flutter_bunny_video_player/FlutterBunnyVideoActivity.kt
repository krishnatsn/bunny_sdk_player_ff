//package com.example.flutter_bunny_video_player
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import net.bunnystream.api.BunnyStreamApi
//import net.bunnystream.bunnystreamplayer.model.PlayerIconSet
//import net.bunnystream.bunnystreamplayer.ui.BunnyStreamPlayer
//
//class FlutterBunnyVideoActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_flutter_bunny_video)
//
//        val accessKey = intent.getStringExtra("accessKey") ?: ""
//        val videoId = intent.getStringExtra("videoId") ?: ""
//        val libraryId = intent.getLongExtra("libraryId", 0L)
//        val iconRes = intent.getIntExtra("playIcon", R.drawable.ic_play_48dp)
//
//        BunnyStreamApi.initialize(this, accessKey = accessKey, libraryId = libraryId)
//
////        val videoPlayer = findViewById<BunnyStreamPlayer>(R.id.videoPlayer)
////        videoPlayer.playVideo(videoId, libraryId = libraryId)
//////        videoPlayer.iconSet = PlayerIconSet(playIcon = iconRes)
//    }
//}
