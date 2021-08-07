package com.example.wikipix.util

import com.example.wikipix.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.handleErrors(): Flow<T> =
    catch { e -> ApiResponse.Error(e.toString()) }
