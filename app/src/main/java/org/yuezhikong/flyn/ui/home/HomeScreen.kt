package org.yuezhikong.flyn.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import org.yuezhikong.flyn.ui.Feed.FeedItem
import org.yuezhikong.flyn.ui.Feed.FeedPagingSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { SearchBar(navController) },
                modifier = Modifier
                    .height(70.dp)
            )
        }
    ) { paddingValues ->
            HomeCard(modifier = Modifier.padding(paddingValues))
        }
}
@Composable
fun HomeCard(modifier: Modifier) {
    //val isRefreshing = pagingItems.loadState.refresh is LoadState.Loading
    val refreshState = rememberPullToRefreshState()

    Column(
        modifier.padding(top = 16.dp)
    ) {
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            state = refreshState,
            isRefreshing = false,
            onRefresh = { }
        ) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(2) { article ->
                    article.let {
                        HotPointCard()
                        ServiceStaffCard()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(navController: NavController){
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
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
            .padding(16.dp)
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

class HomeViewModel : ViewModel() {

    val pager = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance = 2
        )
    ) {
        FeedPagingSource()
    }.flow.cachedIn(viewModelScope)
}