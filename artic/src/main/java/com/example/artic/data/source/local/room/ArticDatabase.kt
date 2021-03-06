package com.example.artic.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.artic.data.source.local.entity.AgentEntity
import com.example.artic.data.source.local.entity.ArtworkEntity
import com.example.artic.data.source.local.entity.ArtworkTypeConverters
import com.example.artic.data.source.local.entity.ArtworkTypeEntity
import com.example.artic.data.source.local.entity.AudioEntity
import com.example.artic.data.source.local.entity.IntTypeConverters
import com.example.artic.data.source.local.entity.StringTypeConverters

@Database(
    entities = [ArtworkEntity::class, AgentEntity::class, ArtworkTypeEntity::class, AudioEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntTypeConverters::class, StringTypeConverters::class, ArtworkTypeConverters::class)
abstract class ArticDatabase : RoomDatabase() {
    abstract val dao: ArticDao
}
