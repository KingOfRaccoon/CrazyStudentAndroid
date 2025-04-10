package ru.kingofraccoons.crazystudent.domain.entity.response

import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.modules.SerializersModule
import ru.kingofraccoons.crazystudent.domain.entity.PointPlace

@Serializable(TimetableUnitResponse.Serializer::class)
interface TimetableUnitResponse {
    val timeStart: String
    val timeEnd: String
    val place: PointPlace

    object Serializer : KSerializer<TimetableUnitResponse> {

        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
            prettyPrint = true
            coerceInputValues = true
            classDiscriminator = "type"
            serializersModule = SerializersModule {
                polymorphic(
                    TimetableUnitResponse::class,
                    LessonResponse::class,
                    LessonResponse.serializer()
                )
                polymorphic(
                    TimetableUnitResponse::class,
                    RoadTimeResponse::class,
                    RoadTimeResponse.serializer()
                )
                polymorphic(
                    TimetableUnitResponse::class,
                    BreakResponse::class,
                    BreakResponse.serializer()
                )
                polymorphic(
                    TimetableUnitResponse::class,
                    EventResponse::class,
                    EventResponse.serializer()
                )
            }
        }
        override val descriptor: SerialDescriptor
            get() = PolymorphicSerializer(TimetableUnitResponse::class).descriptor

        override fun serialize(encoder: Encoder, value: TimetableUnitResponse) {
            when (value) {
                is LessonResponse -> encoder.encodeSerializableValue(
                    LessonResponse.serializer(),
                    value
                )

                is RoadTimeResponse -> encoder.encodeSerializableValue(
                    RoadTimeResponse.serializer(),
                    value
                )
            }
        }

        override fun deserialize(decoder: Decoder): TimetableUnitResponse {
            val jsonElement = (decoder as JsonDecoder).decodeJsonElement()
            return when (val itemType = jsonElement.jsonObject["type"]?.jsonPrimitive?.content) {
                "ru.kingofraccons.models.response.EventResponse" -> json.decodeFromJsonElement(
                    EventResponse.serializer(), jsonElement
                )

                "ru.kingofraccons.models.response.RoadTimeResponse" -> json.decodeFromJsonElement(
                    RoadTimeResponse.serializer(), jsonElement
                )

                "ru.kingofraccons.models.response.LessonResponse" -> json.decodeFromJsonElement(
                    LessonResponse.serializer(), jsonElement
                )

                "ru.kingofraccons.models.response.BreakResponse" -> json.decodeFromJsonElement(
                    BreakResponse.serializer(), jsonElement
                )

                else -> throw SerializationException("Unknown itemType: $itemType")
            }
        }
    }
}