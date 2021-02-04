package com.arslan.youtube.converter

import androidx.room.TypeConverter
import com.arslan.youtube.data.models.Medium
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MediumConverter {
    @TypeConverter
    fun fromRaw(raw: String?): Medium? {
        if (raw == null) return null
        val gson = Gson()
        val type = object : TypeToken<Medium?>() {}.type
        return gson.fromJson<Medium>(raw, type)
    }

    @TypeConverter
    fun toRaw(medium: Medium?): String? {
        if (medium == null) return null
        val gson = Gson()
        val type = object : TypeToken<Medium?>() {}.type
        return gson.toJson(medium, type)
    }
}