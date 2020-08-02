package com.task.repository

import com.task.models.Row
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.BlogsDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import com.task.utils.DataState
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
        try {
            //Retrieve data from the api
            val networBlogs = blogApi.getBlogs().rows
            //map to entity
            val blogs = networkMapper.mapFromEntityList(networBlogs)
            //insert to db
            for (blog in blogs) {
                if (blog.title !== "null")
                    blogsDao.insert(cachedMapper.mapToEntity(blog))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))

        } finally {
            //retrieve from db
            val cachedBlogs = blogsDao.getBlogs()
            emit(DataState.Sucess(cachedMapper.mapFromEntityList(cachedBlogs)))
        }

    }

}