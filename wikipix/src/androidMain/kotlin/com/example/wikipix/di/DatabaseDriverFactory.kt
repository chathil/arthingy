package com.example.wikipix.di

import android.content.Context
import com.example.wikipix.data.source.local.WikipixCache
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            WikipixCache.Schema,
            context,
            "wikipix_cache"
        )
    }
}
