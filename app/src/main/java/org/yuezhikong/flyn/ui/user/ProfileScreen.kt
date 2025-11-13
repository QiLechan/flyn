package org.yuezhikong.flyn.ui.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.yuezhikong.flyn.user

@Preview
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("个人资料", style = MaterialTheme.typography.headlineMedium)
        Text("用户名")
        Text(user.getUsername())
        Text("电子邮件")
        Text(user.getUseremail())
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