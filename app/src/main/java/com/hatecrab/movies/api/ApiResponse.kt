package com.hatecrab.movies.api

import okhttp3.ResponseBody
import okio.BufferedSource

class ApiResponse<out T>(val typed: T, val raw: ResponseBody) {
    fun source() : BufferedSource = raw.source()
}