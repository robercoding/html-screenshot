package dev.robercoding.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.robercoding.app.ui.theme.MultiplatformlibrarytemplateTheme
import dev.robercoding.htmlscreenshot.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiplatformlibrarytemplateTheme {
                App()
                // Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //     Greeting(
                //         name = "Android",
                //         modifier = Modifier.padding(innerPadding),
                //     )
                // }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiplatformlibrarytemplateTheme {
        Greeting("Android")
    }
}