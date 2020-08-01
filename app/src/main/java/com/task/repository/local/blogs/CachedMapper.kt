package com.task.repository.local.blogs

import com.task.models.Row
import com.task.repository.local.blogs.room.RowCachedEntity
import com.task.utils.EntityMapping
import javax.inject.Inject

class CachedMapper @Inject constructor() : EntityMapping<RowCachedEntity, Row> {
    override fun mapFromEntity(entity: RowCachedEntity): Row {
        return Row(
            title = entity.title,
            description = entity.description,
            imageHref = entity.imageHref
        )
    }

    override fun mapToEntity(domainModel: Row): RowCachedEntity {
        return RowCachedEntity(
            title = domainModel.title,
            description = domainModel.description,
            imageHref = domainModel.imageHref
        )
    }

    fun mapFromEntityList(entity: List<RowCachedEntity>): List<Row> {
        return entity.map {
            mapFromEntity(it)
        }
    }

}