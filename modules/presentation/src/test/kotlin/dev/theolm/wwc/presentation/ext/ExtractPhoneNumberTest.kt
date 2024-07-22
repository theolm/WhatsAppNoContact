package dev.theolm.wwc.presentation.ext

import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("all")
class ExtractPhoneNumberTest {
    @Test
    fun `given a String with 51992088821 - expects 51992088821`() {
        val input = "51992088821"

        val result = input.extractPhoneNumber()

        assertEquals("51992088821", result)
    }

    @Test
    fun `given a String with 51992088821withRandomText - expects 51992088821`() {
        val input = "51992088821withRandomText"

        val result = input.extractPhoneNumber()

        assertEquals("51992088821", result)
    }

    @Test
    fun `given a String with only text - expects empty string`() {
        val input = "StringWithOnlyText"

        val result = input.extractPhoneNumber()
        assertEquals("", result)
    }

    @Test
    fun `given a string shared from chrome - expects to extract only the selected number`() {
        val chromeString = "\"0800 722 7152\"\n" +
            " https://www.google.com/search?q=n%C3%BAmero+de+telefone&oq=numero+de+te&gs_lcrp=EgZjaHJvbWUqCggBEAAYiwMYgAQyBggAEEUYOTIKCAEQABiLAxiABDIKCAIQABiLAxiABDIKCAMQABiLAxiABDIKCAQQABiLAxiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABDIHCAoQABiABDIHCAsQABiABDIHCAwQABiABDIHCA0QABiABDIHCA4QABiABNIBCDY0ODRqMWo0qAIBsAIB&client=ms-android-xiaomi-rev1&sourceid=chrome-mobile&ie=UTF-8#:~:text=HDI%20Seguros%20%C2%B7-,0800%20722%207152,-%C2%B7%200800%20775%204036"

        val result = chromeString.extractPhoneNumber()
        assertEquals("08007227152", result)
    }
}
