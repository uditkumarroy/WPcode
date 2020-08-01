package com.task.repository

import com.task.models.Row
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.BlogsDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import com.task.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class BlogRepository
constructor(
    private val blogsDao: BlogsDao,
    private val blogApi: BlogApi,
    private val cachedMapper: CachedMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Row>>> = flow {
        emit(DataState.Loading)
        //Just to check the progress
        delay(1000)

        try {
            //Retrieve data from the api
            val networBlogs = blogApi.getBlogs()
            //map to entity
            val blogs = networkMapper.mapFormEntityList(networBlogs.rows)
            //insert to db
            for (blog in blogs) {
                blogsDao.insert(cachedMapper.mapToEntity(blog))
            }
            //retrieve from db
            val cachedBlogs = blogsDao.getBlogs()
            emit(DataState.Sucess(cachedMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }

    }

}