package org.yuezhikong.flyn.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

enum class RequestMethod {
    POST, GET
}

fun createHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .build()
}

fun buildRequest(url: String, jsonBody: String = "", method: RequestMethod): Request {
    val mediaType = "application/json".toMediaTypeOrNull()
    when (method){
        RequestMethod.POST -> {
            val body = jsonBody.toRequestBody(mediaType)
            return Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .post(body)
                .build()
        }
        RequestMethod.GET ->
            return Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .get()
                .build()
    }
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