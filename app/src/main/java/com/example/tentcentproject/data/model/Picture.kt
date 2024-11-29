package com.example.tentcentproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = Picture.TABLE_NAME,
    indices = [Index(value = ["timestamp"], unique = true)]
)
data class Picture (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = URI) val filename: String?,
    @ColumnInfo(name = TIMESTAMP) val timestamp: String,
) {
    companion object NamesVal {
        const val TABLE_NAME = "gallery"
        const val URI = "filename"
        const val TIMESTAMP = "timestamp"
    }
}
