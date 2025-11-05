package org.yuezhikong.flyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SettingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SettingScreen()
        }
    }
}

@Composable
fun SettingScreen() {
    Text("设置页面", style = MaterialTheme.typography.headlineMedium)
}