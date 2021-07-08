package project

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import user.User
import java.util.*

object Projects : UUIDTable() {
    val name = varchar("name", 50)
}

class Project(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Project>(Projects)

    var name by Projects.name
    var assignees by User via ProjectAssignees
}
