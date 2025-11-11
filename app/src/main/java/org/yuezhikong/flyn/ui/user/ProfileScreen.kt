package org.yuezhikong.flyn.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.yuezhikong.flyn.user

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("个人资料", style = MaterialTheme.typography.headlineMedium)
        Button(
            onClick = {
                user.userLogout()
            },
            content = {
                Text("退出登录")
            }
        )
    }
}