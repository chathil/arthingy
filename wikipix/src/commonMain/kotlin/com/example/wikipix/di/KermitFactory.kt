package com.example.wikipix.di

import co.touchlab.kermit.Kermit

expect class KermitFactory {
    fun createKermit(): Kermit
}
