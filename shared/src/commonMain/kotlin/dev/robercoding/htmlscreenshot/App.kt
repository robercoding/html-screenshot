package dev.robercoding.htmlscreenshot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import dev.robercoding.htmlscreenshot.examples.DEFAULT_HTML
import dev.robercoding.htmlscreenshot.examples.listExamples
import dev.robercoding.htmlscreenshot.helper.ScreenshotThatHtmlConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        var byteArray by remember { mutableStateOf<ByteArray?>(null) }
        var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }
        LaunchedEffect(byteArray) {
            bitmap = null
            launch(Dispatchers.IO) {
                bitmap = byteArray?.decodeToImageBitmap()
            }
        }

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            var value by remember { mutableStateOf("") }
            Column(Modifier.weight(1f)) {
                TextField(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    value = value,
                    onValueChange = { value = it }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 3)
                ) {
                    items(listExamples) {
                        val (name, html) = it
                        Button(
                            onClick = {
                                value = html
                            }
                        ) {
                            Text(name, fontSize = 10.sp)
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                bitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(Color.LightGray)
                    )

                }
            }

            val converter = remember { HtmlToImageConverter(ScreenshotThatHtmlConfiguration(
                imageWidth = 480,
            )) }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            val result = converter.convert(value)
                            when(result) {
                                is Html2ScreenshotResult.Success -> {
                                    byteArray = result.data
                                }
                                is Html2ScreenshotResult.Error -> {
                                    // println("Error: ${result.exception.message}")
                                }
                            }
                            // byteArray = converter.urlToByteArray("https://www.google.com/")
                            // byteArray = converter.urlToByteArray("https://consum.es/")
                        }
                    }
                ) {
                    Text("Transform!")
                }
                Button(
                    onClick = {
                        value = ""
                    }
                ) {
                    Text("Clear!")
                }
                Button(
                    onClick = {
                        value = DEFAULT_HTML
                    }
                ) {
                    Text("Default!")
                }
            }
        }
    }
}
