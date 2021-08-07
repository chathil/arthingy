package com.example.wikipix.di

import com.example.wikipix.data.source.local.WikipixCache
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

/**
 * TODO: 06/08/21 Android Studio says unresolved dependency but gradle build is successful.
 * Just gonna leave it like this for now.
 */
actual val platformModule = module {
    single<SqlDriver> { NativeSqliteDriver(WikipixCache.Schema, "wikipix_cache") }
}
