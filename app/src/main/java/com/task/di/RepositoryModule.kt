package com.task.di

import com.task.repository.BlogRepository
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.BlogsDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBlogRepository(
        blogsDao: BlogsDao,
        blogApi: BlogApi,
        cachedMapper: CachedMapper,
        networkMapper: NetworkMapper
    ): BlogRepository {
        return BlogRepository(blogsDao, blogApi, cachedMapper, networkMapper)
    }
}