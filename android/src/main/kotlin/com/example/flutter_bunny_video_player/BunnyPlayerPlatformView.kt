package com.example.flutter_bunny_video_player

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class BunnyVideoPlatformView(
    context: Context,
    id: Int,
    creationParams: Map<String, Any>?,
    private val methodChannel: MethodChannel,
    private val lifecycleOwner: ComponentActivity? // Accept the lifecycleOwner
) : PlatformView {

    private val composeView: ComposeView = ComposeView(context)
    private var initialData: String? = creationParams?.get("initialData") as? String

    init {
        // IMPORTANT: Attach the LifecycleOwner and SavedStateRegistryOwner before setContent
        // This is the core fix for the ViewTreeLifecycleOwner not found error.
        lifecycleOwner?.let {
            return@let ViewTreeLifecycleOwner.set(composeView, lifecycleOwner)
        } ?: run {
            // Log a warning or throw an exception if lifecycleOwner is null
            println("Warning: LifecycleOwner is null for ComposeView. Composition might not behave correctly.")
            // You might choose to throw an error or use a fallback strategy here
        }


        composeView.setContent {
            MaterialTheme {
                MyComposeContent(
                    initialText = initialData ?: "No data from Flutter",
                    onButtonPressed = { message ->
                        methodChannel.invokeMethod("onButtonPressed", message)
                    }
                )
            }
        }
    }

    override fun getView(): View {
        return composeView
    }

    override fun dispose() {
        // Clean up resources if necessary
        composeView.disposeComposition()
        // It's good practice to clear the lifecycle owners when the view is disposed,
        // although ComposeView's disposeComposition() might implicitly handle some of this.
        // ViewTreeLifecycleOwner.set(composeView, null)
        // ViewTreeSavedStateRegistryOwner.set(composeView, null)
    }

}

// @Composable and @Preview functions remain the same
@Composable
fun MyComposeContent(initialText: String, onButtonPressed: (String) -> Unit) {
    androidx.compose.foundation.layout.Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        Text(text = "Compose View: $initialText")
        Button(onClick = { onButtonPressed("Hello from Compose!") }) {
            Text("Send back to Flutter")
        }
    }
}
