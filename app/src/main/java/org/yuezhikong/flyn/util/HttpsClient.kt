package org.yuezhikong.flyn.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun buildRequest(url: String, jsonBody: String): Request {
    val mediaType = "application/json;".toMediaTypeOrNull()
    val body = jsonBody.toRequestBody(mediaType)
    return Request.Builder()
        .url(url)
        .addHeader("Accept", "application/json")
        .post(body)
        .build()
}

fun executeRequest(client: OkHttpClient, request: Request): JSONObject? {
    client.newCall(request).execute().use { response ->
        if (response.isSuccessful) {
            response.body.string().let {
                return JSONObject(it)
            }
        }
        else return null
    }
}