package com.task.repository.remote

import com.task.models.Row
import com.task.repository.remote.blogs.models.RowNetworkEntity
import com.task.utils.EntityMapping
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapping<RowNetworkEntity, Row> {
    override fun mapFromEntity(entity: RowNetworkEntity): Row {
        return Row(
            description = "$entity.description",
            imageHref = "$entity.imageHref",
            title = "$entity.title"
        )
    }

    override fun mapToEntity(domainModel: Row): RowNetworkEntity {
        return RowNetworkEntity(
            title = "$domainModel.title",
            description = "$domainModel.description",
            imageHref = "$domainModel.imageHref"
        )
    }

    fun mapFormEntityList(entity: List<RowNetworkEntity>): List<Row> {
        return entity.map {
            mapFromEntity(it)
        }
    }

}