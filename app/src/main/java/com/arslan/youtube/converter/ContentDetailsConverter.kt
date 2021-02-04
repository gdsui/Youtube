package com.arslan.youtube.converter

import androidx.room.TypeConverter
import com.arslan.youtube.data.models.ContentDetails
import com.arslan.youtube.data.models.Snippet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContentDetailsConverter {

    @TypeConverter
    fun fromRaw(raw: String?): ContentDetails? {
        if (raw == null) return null
        val gson = Gson()
        val type = object : TypeToken<ContentDetails?>() {}.type
        return gson.fromJson<ContentDetails>(raw, type)
    }

    @TypeConverter
    fun toRaw(contentDetails: ContentDetails?): String? {
        if (contentDetails == null) return null
        val gson = Gson()
        val type = object : TypeToken<ContentDetails?>() {}.type
        return gson.toJson(contentDetails, type)
    }
}