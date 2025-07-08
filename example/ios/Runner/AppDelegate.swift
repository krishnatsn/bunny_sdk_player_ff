import Flutter
import UIKit

@main
@objc class AppDelegate: FlutterAppDelegate {
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)

    guard let registrar = self.registrar(forPlugin: "flutter_bunny_video_player") else {
      fatalError("Could not get plugin registrar")
    }

    let factory = BunnyPlayerPlatformViewFactory(messenger: registrar.messenger())
    registrar.register(factory, withId: "bunny_player_view")

    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
