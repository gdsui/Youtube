package com.arslan.youtube.converter

import androidx.room.TypeConverter
import com.arslan.youtube.data.models.Snippet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SnippetConverter {
    @TypeConverter
    fun fromRaw(raw: String?): Snippet? {
        if (raw == null) return null
        val gson = Gson()
        val type = object : TypeToken<Snippet?>() {}.type
        return gson.fromJson<Snippet>(raw, type)
    }

    @TypeConverter
    fun toRaw(snippet: Snippet?): String? {
        if (snippet == null) return null
        val gson = Gson()
        val type = object : TypeToken<Snippet?>() {}.type
        return gson.toJson(snippet, type)
}}