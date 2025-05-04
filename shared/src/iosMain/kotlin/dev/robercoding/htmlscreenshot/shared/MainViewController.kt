package dev.robercoding.htmlscreenshot.shared

import androidx.compose.ui.window.ComposeUIViewController
import dev.robercoding.htmlscreenshot.App
import platform.UIKit.UIViewController

public fun MainViewController(): UIViewController = ComposeUIViewController {
    App()
}
