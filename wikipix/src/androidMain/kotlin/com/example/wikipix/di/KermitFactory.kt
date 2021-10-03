package com.example.wikipix.di

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger

actual class KermitFactory {
    actual fun createKermit(): Kermit {
        return Kermit(LogcatLogger()).withTag("Wikipix")
    }
}
