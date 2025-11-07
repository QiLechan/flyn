package org.yuezhikong.flyn.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Text("欢迎使用法粒援农！", style = MaterialTheme.typography.headlineMedium)
}