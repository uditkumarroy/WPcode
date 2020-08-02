package com.task.repository.remote

import android.util.Log
import com.task.models.Row
import com.task.repository.remote.blogs.models.RowNetworkEntity
import com.task.utils.EntityMapping
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() :
    EntityMapping<RowNetworkEntity, Row> {

    override fun mapFromEntity(entity: RowNetworkEntity): Row {
        Log.e("Row", " : ${entity.title}")
        return Row(
            description = "${entity.description}",
            imageHref = "${entity.imageHref}",
            title = "${entity.title}"
        )
    }

    override fun mapToEntity(domainModel: Row): RowNetworkEntity {
        return RowNetworkEntity(
            description = "${domainModel.description}",
            imageHref = "${domainModel.imageHref}",
            title = "${domainModel.title}"
        )
    }


    fun mapFromEntityList(entities: List<RowNetworkEntity>): List<Row> {
        return entities.map { mapFromEntity(it) }
    }

}