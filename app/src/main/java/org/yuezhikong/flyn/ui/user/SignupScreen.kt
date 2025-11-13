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

class SignupViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var signupSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun sendVerificationCode(email: String) {
        viewModelScope.launch {
            errorMessage = null

            withContext(Dispatchers.IO) {
                user.sendEmail_Signup_Verification_code(email)
            }
        }
    }

    fun signup(username: String, email: String, password: String, verification_code: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            var result: Boolean = false

            withContext(Dispatchers.IO) {
                result = user.userSignup(username, password, email, verification_code)
            }
            isLoading = false

            if (result) {
                signupSuccess = true
            } else {
                errorMessage = "注册失败，请检查信息是否正确"
            }
        }
    }

    fun resetStates() {
        isLoading = false
        signupSuccess = false
        errorMessage = null
    }
}

@Composable
fun SignupScreen(navController: NavController, viewModel: SignupViewModel = viewModel()) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verification_code by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.signupSuccess, viewModel.errorMessage) {
        when {
            viewModel.signupSuccess -> {
                Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show()
                withContext(Dispatchers.IO) { user.getUserInfo() }
                viewModel.resetStates()
                navController.popBackStack("user", false)
            }
            viewModel.errorMessage != null -> {
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
        Text("用户注册", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("邮箱") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            singleLine = true
        )
        Button(
            onClick = {
                if (email == "") {
                    Toast.makeText(context, "邮箱不能为空！", Toast.LENGTH_SHORT).show()
                }
                else {
                    viewModel.sendVerificationCode(email)
                    Toast.makeText(context, "验证码已发送至邮箱！", Toast.LENGTH_SHORT).show()
                }
            },
            content = {
                Text("获取验证码")
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true
        )
        OutlinedTextField(
            value = verification_code,
            onValueChange = { verification_code = it},
            label = { Text("验证码") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            singleLine = true
        )
        Button(
            onClick = {
                viewModel.signup(username, email, password, verification_code)
            },
            content = {
                Text("注册")
            }
        )
        if (viewModel.isLoading) {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {},
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("正在注册中…")
                    }
                }
            )
        }
    }
}