package com.example.wikipix.di

import com.example.wikipix.data.source.local.WikipixCache
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(WikipixCache.Schema, "wikipix_cache")
    }
}
