package com.example.tentcentproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "gallery",
    indices = [Index(value = ["timestamp"], unique = true)]
)
data class Picture (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = URL) val uri: String?,
    @ColumnInfo(name = TIMESTAMP) val timestamp: String,
) {
    companion object NamesVal {
        const val URL = "uri"
        const val TIMESTAMP = "timestamp"
    }
}
