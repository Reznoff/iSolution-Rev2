package com.example.kemalmaulana.isolution.utils

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val default: CoroutineContext by lazy { Dispatchers.Default }
    open val main: CoroutineContext by lazy { Dispatchers.Main }
}