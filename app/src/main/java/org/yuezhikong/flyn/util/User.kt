package org.yuezhikong.flyn.util

import org.json.JSONObject
import androidx.core.content.edit

class User {
    private var access_token: String = ""
    private var refresh_token: String = ""

    fun userLogin(number: String, password: String): JSONObject? {
        val request = buildRequest(number, password)
        executeRequest(createHttpClient(), request)?.let {
            return it
        }
        return null
    }

    fun parseLoginResponse(response: JSONObject): Boolean {
        val access = response.optString("access_token", null)
        val refresh = response.optString("refresh_token", null)

        if (access == null || refresh == null) return false

        access_token = access
        refresh_token = refresh
        return true
    }

    fun saveRefreshToken(): Unit {
        SecurePrefs.getPrefs().edit {
            putString("refresh_token", refresh_token)
        }
    }

    fun setRefreshToken(): Boolean {
        refresh_token = SecurePrefs.getPrefs().getString("refresh_token", null) ?: return false
        return true
    }
}