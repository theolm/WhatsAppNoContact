package dev.theolm.wwc.data.serializer

import android.util.Log
import androidx.datastore.core.Serializer
import dev.theolm.wwc.data.models.AppLocalData
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
internal object AppLocalDataSerializer : Serializer<AppLocalData> {
    override val defaultValue: AppLocalData = AppLocalData()
    override suspend fun readFrom(input: InputStream): AppLocalData {
        return try {
            Json.decodeFromString(
                deserializer = AppLocalData.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            Log.d("AppLocalDataSerializer", "Error reading AppLocalData", e)
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppLocalData, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppLocalData.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
