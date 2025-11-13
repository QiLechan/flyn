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
    private var verification_id: String = ""
    private var verification_token: String = ""

    fun isLoggedIn(): Boolean {
        return setRefreshToken()
    }

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 用户密码
     * @return Boolean 是否登录成功
     */
    fun userLogin(username: String, password: String): Boolean {
        val json = JSONObject()
        json.put("username", username)
        json.put("password", password)
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/signin"
        val request = buildRequest(url, json.toString(), RequestMethod.POST)
        executeRequest(createHttpClient(), request)?.let {
            return parseLoginResponse(it)
        }
        return false
    }

    fun userLogout(): Boolean {
        access_token = ""
        refresh_token = ""
        SecurePrefs.getPrefs().edit {
            remove("refresh_token")
        }
        return true
    }

    fun userSignup(username: String, password: String, email: String, verification_code: String): Boolean {
        if (!verifyEmail_Signup_Verification_code(verification_code)) {
            return false
        }
        val json = JSONObject()
        json.put("email", email)
        json.put("username", username)
        json.put("password", password)
        json.put("verification_token", verification_token)
        json
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/signup"
        val request = buildRequest(url, json.toString(), RequestMethod.POST)
        executeRequest(createHttpClient(), request)?.let {
            return parseSignupResponse(it)
        }
        return false
    }

    fun sendEmail_Signup_Verification_code(email: String): Boolean{
        val json = JSONObject()
        json.put("email", email)
        json.put("target", "NOT_USER")
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/verification"
        val request = buildRequest(url, json.toString(), RequestMethod.POST)
        executeRequest(createHttpClient(), request)?.let {
            return parseEmail_Signup_Verification_codeResponse(it)
        }
        return false
    }

    fun verifyEmail_Signup_Verification_code(verification_code: String): Boolean{
        if (verification_id == "") {
            return false
        }
        val json = JSONObject()
        json.put("verification_id", verification_id)
        json.put("verification_code", verification_code)
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/verification/verify"
        val request = buildRequest(url, json.toString(), RequestMethod.POST)
        executeRequest(createHttpClient(), request)?.let {
            verification_token = it.optString("verification_token", null)
            return true
        }
        return false
    }

    /**
     * 解析登录响应
     * @param response JSON 对象，包含登录响应数据
     * @return Boolean 表示解析是否成功
     */
    fun parseLoginResponse(response: JSONObject): Boolean {
        val access = response.optString("access_token", null)
        val refresh = response.optString("refresh_token", null)

        if (access == null || refresh == null) return false

        access_token = access
        refresh_token = refresh
        return true
    }

    fun parseSignupResponse(response: JSONObject): Boolean {
        val access = response.optString("access_token", null)
        val refresh = response.optString("refresh_token", null)

        if (access == null || refresh == null) return false

        access_token = access
        refresh_token = refresh
        return true
    }

    fun parseEmail_Signup_Verification_codeResponse(response: JSONObject): Boolean {
        val is_user_exists = response.optBoolean("is_user", false)
        if (is_user_exists){
            return false
        }
        verification_id = response.optString("verification_id", null)
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

    fun getUserInfo(): Boolean {
        val url = "https://app-8go3d13e29358a5e.api.tcloudbasegateway.com/auth/v1/user/me"
        val request = buildRequest(url, method = RequestMethod.GET).newBuilder().addHeader("Authorization", "Bearer $access_token").build()
        executeRequest(createHttpClient(), request)?.let {
            username = it.optString("username", null)
            email = it.optString("email", null)
            return true
        }
        return false
    }

    fun getUsername():String {
        return username
    }

    fun getUseremail(): String {
        return email
    }

//    fun getRefreshToken(): String? {
//        return SecurePrefs.getPrefs().getString("refresh_token", null)
//    }
}