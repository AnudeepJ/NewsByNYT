package com.anudeep.nyt.app.io

import com.anudeep.nyt.app.util.SerializationUtils
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlinx.serialization.Serializable

@Serializable
data class TopStoryResponse(
    val results: List<Story>
)

@Serializable
data class Story(
    val title: String,
    val abstract: String,
    val url: String,
    val byline: String,
    val item_type: String,
    val updated_date: String,
    val created_date: String,
    val published_date: String,
    val material_type_facet: String,
    val kicker: String,
    val des_facet: List<String>,
    val org_facet: List<String>,
    val per_facet: List<String>,
    val geo_facet: List<String>,
    var multimedia: List<Multimedia>?
) : java.io.Serializable {
    @Transient
    private val multimediaBytes: ByteArray? = null

    private fun writeObject(out: ObjectOutputStream) {
        out.defaultWriteObject()

        // Convert the List<Multimedia> to a byte array
        val bytes = multimedia?.let { SerializationUtils.serialize(it) }
        out.writeObject(bytes)
    }

    private fun readObject(inStream: ObjectInputStream) {
        inStream.defaultReadObject()

        // Read the byte array and convert it back to List<Multimedia>
        val bytes = inStream.readObject() as ByteArray
        val deserializedMultimedia = SerializationUtils.deserialize(bytes) as List<Multimedia>

        // Create a new instance of Story with deserialized multimedia
        val deserializedStory = copy(multimedia = deserializedMultimedia)

        // Assign the deserialized fields to this instance
        multimedia = deserializedStory.multimedia
    }
}
@Serializable
data class Multimedia(
    val url: String,
    val format: String,
    val height: Int,
    val width: Int,
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String
) : java.io.Serializable
