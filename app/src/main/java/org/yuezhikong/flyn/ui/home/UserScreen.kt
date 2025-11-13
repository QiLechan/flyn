package org.yuezhikong.flyn.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.yuezhikong.flyn.user

@Composable
fun UserScreen(navController: NavController) {
    val isLoggedIn = user.isLoggedIn()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.LightGray)
                .padding(32.dp)
        ){
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                        if ( !isLoggedIn ) {
                            navController.navigate("login") {
                                launchSingleTop = true
                                restoreState = true
                            }
                        } else {
                            navController.navigate("profile") {
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.Yellow),
                )
                Spacer(modifier = Modifier.width(12.dp))
                if ( !isLoggedIn )
                    Text("未登录", style = MaterialTheme.typography.headlineSmall)
                else
                    Text("已登录", style = MaterialTheme.typography.headlineSmall)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("setting") {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "设置"
                    )
                }
            }
        }
    }
}