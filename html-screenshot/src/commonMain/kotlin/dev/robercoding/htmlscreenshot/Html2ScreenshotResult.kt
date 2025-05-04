package dev.robercoding.htmlscreenshot

sealed class Html2ScreenshotResult {
    data class Success(val data: ByteArray) : Html2ScreenshotResult() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as Success

            return data.contentEquals(other.data)
        }

        override fun hashCode(): Int {
            return data.contentHashCode()
        }
    }

    data class Error(val exception: Throwable) : Html2ScreenshotResult()
}

// enum class Type {
//     BITMAP,
//     BYTE_ARRAY,
// }

// sealed interface Type {
//     data class Bitmap(val bitmap: ByteA) : Type
//     data class
// }