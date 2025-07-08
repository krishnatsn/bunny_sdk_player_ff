//
//  BunnyPlayerViewController.swift
//  Runner
//
//  Created by Roothex200 on 7/9/25.
//

import Foundation
import SwiftUI
import UIKit
import BunnyStreamPlayer
class BunnyPlayerViewController: UIViewController {
    private let videoId: String

    init(videoId: String) {
        self.videoId = videoId
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        let playerView = BunnyStreamPlayer.make(videoId: videoId)
        let hostingController = UIHostingController(rootView: playerView)
        addChild(hostingController)
        hostingController.view.frame = view.bounds
        hostingController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        view.addSubview(hostingController.view)
        hostingController.didMove(toParent: self)
    }
}
