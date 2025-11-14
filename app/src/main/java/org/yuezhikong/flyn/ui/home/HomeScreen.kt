package org.yuezhikong.flyn.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SearchBar(navController)
        HotPointCard()
        ServiceStaffCard()
    }
}

@Composable
fun SearchBar(navController: NavController){
    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp)
    ){
        Card(
            modifier = Modifier
                .fillMaxSize(),
            onClick = {
                navController.navigate("search") {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        ) {
            Column(
                modifier = Modifier.padding(6.dp)
            ) {
                Icon(Icons.Default.Search, contentDescription = "搜索")
            }
        }
    }
}
@Composable
fun HotPointCard() {
    Box(
        modifier = Modifier
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .height(200.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
        ){
            Text("热门咨询TOP3", style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    }
}

@Preview
@Composable
fun ServiceStaffCard() {
    Box(
        modifier = Modifier
            .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            .height(200.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
        ){
            Text("服务人员", style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

            }
        }
    }
}