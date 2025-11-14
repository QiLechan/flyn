package org.yuezhikong.flyn.ui.Feed

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState

class FeedPagingSource : PagingSource<Int, FeedItem>() {
    //    override fun getRefreshKey(state: PagingState<Int, FeedItem>): Int? {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedItem> {
//        val page = params.key ?: 1
//        val response = api.loadFeed(page)
//
//        return LoadResult.Page(
//            data = response.items,
//            prevKey = null,
//            nextKey = if (response.hasMore) page + 1 else null
//        )
//    }
    override fun getRefreshKey(state: PagingState<Int, FeedItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedItem> {
        TODO("Not yet implemented")
    }

}