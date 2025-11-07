package org.yuezhikong.flyn.util.user

import org.json.JSONObject
import org.yuezhikong.flyn.util.HttpsClient

class Login {
    var access_token: String = ""
    var refresh_token: String = ""

    fun userLogin(number: String, password: String): JSONObject? {
        val request = HttpsClient.buildRequest(number, password)
        HttpsClient.executeRequest(HttpsClient.createHttpClient(), request)?.let {
            return it
        }
        return null
    }

    fun parseLoginResponse(response: JSONObject): Boolean {
        response.get("access_token")?.let {
            access_token = it.toString()
        } ?: return false
        response.get("refresh_token")?.let {
            refresh_token = it.toString()
        } ?: return false
        return true
    }
}