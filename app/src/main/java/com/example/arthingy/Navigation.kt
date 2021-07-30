package com.example.arthingy

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uimain.HomeScreen
import com.example.uimain.HomeViewModel

/**
 * Destinations used in the ([ArthingyApp])
 */
object MainDestinations {
//    const val WELCOME_ROUTE = "welcome"
//    const val DETAIL_ROUTE = "detail"
    /** id for the item opened in detail page */
//    const val DETAIL_ID_KEY = "courseId"
    const val HOME_ROUTE = "home"
}

@Composable
fun NavGraph(
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
//    showWelcomeInitially: Boolean = true
) {
    /**
     * Use this to navigation, for example:
     * composable(...) {
     *     WelcomeScreen(
     *       ...
     *       welcomeToHome: actions.welcomeToHome
     *       ...
     *     )
     * }
     */
//    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        /**
         * Decide what the start destination is right here by doing this
         * [if (showWelcomeInitially) MainDestinations.WELCOME_ROUTE else startDestination]
         * */
        startDestination = startDestination,
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            /**
             * Intercept back to Welcome: make it finish the activity,
             * without this the welcome page will show up again, if you
             * have a welcome page.
             */
            BackHandler {
                finishActivity()
            }

            /**
             * Workaround because calling [viewModel()] directly
             * in composable function declaration causing this error
             * java.lang.RuntimeException: Cannot create an instance of class
             * com.example.uimain.HomeViewModel
             */
            val homeViewModel: HomeViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, it))

            HomeScreen(homeViewModel)
        }

        /** Navigation with parameters */
//
//        composable(
//            "${MainDestinations.COURSE_DETAIL_ROUTE}/{$COURSE_DETAIL_ID_KEY}",
//            arguments = listOf(
//                navArgument(COURSE_DETAIL_ID_KEY) { type = NavType.LongType }
//            )
//        ) { backStackEntry ->
//            val arguments = requireNotNull(backStackEntry.arguments)
//            CourseDetails(
//                courseId = arguments.getLong(COURSE_DETAIL_ID_KEY),
//                selectCourse = actions.selectCourse,
//                upPress = actions.upPress
//            )
//        }
    }
}

/**
 * Models the navigation actions in the app.
 */
// class MainActions(navController: NavHostController) {

/**
 * Navigating from welcome page to home page
 */
//    val welcomeToHome: () -> Unit = {
//        navController.navigate(MainDestinations.HOME_ROUTE)
//    }

/**
 * Navigating with parameters
 */
//    val selectCourse: (Long) -> Unit = { courseId: Long ->
//        navController.navigate("${MainDestinations.COURSE_DETAIL_ROUTE}/$courseId")
//    }

// }
