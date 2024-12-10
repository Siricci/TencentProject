package com.example.tentcentproject.data.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(
    tableName = Picture.TABLE_NAME,
    indices = [Index(value = ["timestamp"], unique = true)]
)
data class Picture (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = URI) val uri: Uri,
    @ColumnInfo(name = TIMESTAMP) val timestamp: String,
) {
    override fun toString(): String {
        return "Picture(id=$id, uri=$uri, timestamp=$timestamp)"
    }

    companion object NamesVal {
        const val TABLE_NAME = "gallery"
        const val URI = "uri"
        const val TIMESTAMP = "timestamp"
    }
}

class Converters {
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return if (uriString == null) null else Uri.parse(uriString)
    }
}
