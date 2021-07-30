package com.example.arthingy

import androidx.compose.runtime.Composable
import com.example.commonui.theme.ComposeKitTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun ArthingyApp(
    /** if you have a welcome screen that only open once, this decide to show it or not, */
//  showWelcomeInitially: Boolean,
    /** and call this when welcome is showing */
//    welcomeShown: () -> Unit,
    finishActivity: () -> Unit,
) {
    ProvideWindowInsets {
        ComposeKitTheme {
            NavGraph(finishActivity = finishActivity)
        }
    }
}
