package com.example.wikipix.di

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.NSLogLogger

actual class KermitFactory {
    actual fun createKermit(): Kermit {
        return Kermit(NSLogLogger()).withTag("Wikipix")
    }
}
