//
//  BunnyExtensionPlayer.swift
//  Runner
//
//  Created by Roothex200 on 7/9/25.
//
import SwiftUI
import Foundation
import BunnyStreamPlayer

public extension BunnyStreamPlayer {
  static func make(videoId: String) -> BunnyStreamPlayer {
    let playerIcons = PlayerIcons(play: Image(systemName: "play.fill"))

    return BunnyStreamPlayer(
      accessKey:nil,
      videoId: videoId,
      libraryId: 121,
    )
  }
}

//// Example view
//struct VideoPlayerDemoView: View {
//    var body: some View {
//       BunnyStreamPlayer.make(videoId: videoInfo.id)
//       .navigationBarTitle(Text("Video Player"), displayMode: .inline)
//    }
//}
