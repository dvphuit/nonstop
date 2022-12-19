package dvp.data.youtube.models;

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
enum class AudioQuality(val value: String) {
    AudioQualityHigh("AUDIO_QUALITY_HIGH"),
    AudioQualityLow("AUDIO_QUALITY_LOW"),
    AudioQualityMedium("AUDIO_QUALITY_MEDIUM");

    companion object : KSerializer<AudioQuality> {
        override val descriptor: SerialDescriptor
            get() {
            return PrimitiveSerialDescriptor("quicktype.AudioQuality", PrimitiveKind.STRING)
        }
        override fun deserialize(decoder: Decoder): AudioQuality = when (val value = decoder.decodeString()) {
            "AUDIO_QUALITY_HIGH"   -> AudioQualityHigh
            "AUDIO_QUALITY_LOW"    -> AudioQualityLow
            "AUDIO_QUALITY_MEDIUM" -> AudioQualityMedium
            else                   -> throw IllegalArgumentException("AudioQuality could not parse: $value")
        }
        override fun serialize(encoder: Encoder, value: AudioQuality) {
            return encoder.encodeString(value.value)
        }
    }
}