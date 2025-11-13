package org.yuezhikong.flyn.ui.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.yuezhikong.flyn.user

class LoginViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            var result: Boolean = false

            withContext(Dispatchers.IO) {
                if (user.userLogin(username, password)) {
                    result = user.saveRefreshToken()
                }
            }
            isLoading = false

            if (result) {
                loginSuccess = true
            } else {
                errorMessage = "账号或密码错误"
            }
        }
    }

    fun dismissSuccess() {
        loginSuccess = false
    }

    fun dismissError() {
        errorMessage = null
    }

    fun resetStates() {
        isLoading = false
        loginSuccess = false
        errorMessage = null
    }
}

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current
    var number by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    LaunchedEffect(viewModel.loginSuccess, viewModel.errorMessage) {
        // 监听 loginSuccess 与 errorMessage 的变化
        when {
            viewModel.loginSuccess -> {
                // 登录成功：提示用户、重置 ViewModel 状态并返回上一个界面
                Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show()
                withContext(Dispatchers.IO) { user.getUserInfo() }
                viewModel.resetStates()
                navController.popBackStack()
            }
            viewModel.errorMessage != null -> {
                // 登录失败或出现错误：显示错误信息并重置 ViewModel 状态
                Toast.makeText(context, viewModel.errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.resetStates()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("用户登录", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            label = { Text("用户名") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        Button(
            onClick = { viewModel.login(number, password) },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("登录")
        }
        Button(
            onClick = {
                navController.navigate("signin"){
                    launchSingleTop = true
                    restoreState = true
                }
                      },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("注册")
        }
        // 登录中弹窗
        if (viewModel.isLoading) {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {},
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("正在登录中…")
                    }
                }
            )
        }
    }
}