package org.yuezhikong.flyn.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SecurePrefs {
    private lateinit var prefs: SharedPreferences

/**
 * 初始化加密的 SharedPreferences。
 */
fun init(context: Context) {
    // 构建主密钥（MasterKey），使用 AES256_GCM 算法
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    // 创建加密的 SharedPreferences：
    // - 文件名：secure_prefs
    // - 键加密：AES256_SIV（保证键不可推断）
    // - 值加密：AES256_GCM（具备机密性与完整性校验）
    prefs = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

    fun getPrefs(): SharedPreferences {
        return prefs
    }
}