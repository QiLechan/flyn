package org.yuezhikong.flyn.util

import org.json.JSONObject
import androidx.core.content.edit

class User {
    private var access_token: String = ""
    private var refresh_token: String = ""

    private var userid: String = ""
    private var username: String = ""
    private var email: String = ""
    private var number: String = ""

    fun isLoggedIn(): Boolean {
        return setRefreshToken()
    }

    fun userLogin(username: String, password: String): Boolean {
        val json = JSONObject()
        json.put("username", username)
        json.put("password", password)
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/signin"
        val request = buildRequest(url, json.toString())
        executeRequest(createHttpClient(), request)?.let {
            return parseLoginResponse(it)
        }
        return false
    }

    fun parseLoginResponse(response: JSONObject): Boolean {
        val access = response.optString("access_token", null)
        val refresh = response.optString("refresh_token", null)

        if (access == null || refresh == null) return false

        access_token = access
        refresh_token = refresh
        return true
    }

    fun saveRefreshToken(): Boolean {
        SecurePrefs.getPrefs().edit {
            putString("refresh_token", refresh_token)
        }
        return true
    }

    fun setRefreshToken(): Boolean {
        refresh_token = SecurePrefs.getPrefs().getString("refresh_token", null) ?: return false
        return true
    }

//    fun getRefreshToken(): String? {
//        return SecurePrefs.getPrefs().getString("refresh_token", null)
//    }
}