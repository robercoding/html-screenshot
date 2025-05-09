# HTML Screenshot

A Kotlin Multiplatform Mobile (KMM) library for converting HTML markup into a `ByteArray`.

---

## Overview

`html-screenshot` was created for the [Tikbit](https://robercoding.dev/projects/tikbit) application to enable the generation of receipt images from HTML.
The library only handles local HTML-to-image conversion, meaning it does not support remote resources.


---

## Features

- **Local HTML Rendering**  
  Accepts HTML as a string; no network requests or remote resources required.

- **Coroutine-Based Architecture**  
  Uses Kotlin coroutines for non-blocking, asynchronous rendering.

- **Rendering Callbacks**  
  Relies on platform callbacks (Android and iOS) to detect when rendering is complete, avoiding arbitrary time-outs.

## Usage
```
val htmlContent = """
  <html>
    <body>
      <h1>Receipt</h1>
      <p>Amount: $19.99</p>
      <p>Date: 2025-05-04</p>
    </body>
  </html>
""".trimIndent()

private val imageConverter = HtmlToImageConverter(ScreenshotThatHtmlConfiguration(imageWidth = 480))
val result = imageConverter.convert(htmlContent)

when (result) {
    is Html2ScreenshotResult.Success -> {
        result.data
    }

    is Html2ScreenshotResult.Error -> {
        null
    }

    else -> null
}
```

## Contributing
1. Fork the repository. 
2. Create a feature or bug-fix branch. 
3. Submit a pull request with a clear description of changes.

## Acknowledgments
Based on [android-html2bitmap](https://github.com/iZettle/android-html2bitmap). 
Without their previous efforts this would've taken much longer to develop.
Adapted to Kotlin coroutines and updated rendering logic to improve reliability.