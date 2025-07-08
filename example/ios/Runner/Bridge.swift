//
//  Bridge.swift
//  Runner
//
//  Created by Roothex200 on 7/9/25.
//

import Foundation
import Flutter
import UIKit

class BunnyPlayerPlatformView: NSObject, FlutterPlatformView {
    private var _view: UIView

    init(
        frame: CGRect,
        viewIdentifier viewId: Int64,
        arguments args: Any?,
        messenger: FlutterBinaryMessenger?
    ) {
        let params = args as? [String: Any]
        let videoId = params?["videoId"] as? String ?? ""

        let controller = BunnyPlayerViewController(videoId: videoId)
        _view = controller.view
        super.init()
    }

    func view() -> UIView {
        return _view
    }
}

class BunnyPlayerPlatformViewFactory: NSObject, FlutterPlatformViewFactory {
    private var messenger: FlutterBinaryMessenger

    init(messenger: FlutterBinaryMessenger) {
        self.messenger = messenger
        super.init()
    }

    func create(
        withFrame frame: CGRect,
        viewIdentifier viewId: Int64,
        arguments args: Any?
    ) -> FlutterPlatformView {
        return BunnyPlayerPlatformView(frame: frame, viewIdentifier: viewId, arguments: args, messenger: messenger)
    }

    func createArgsCodec() -> FlutterMessageCodec & NSObjectProtocol {
        return FlutterStandardMessageCodec.sharedInstance()
    }
}

