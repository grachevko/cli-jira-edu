package task

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import project.Project
import project.Projects
import user.User
import java.util.*

object Tasks : UUIDTable() {
    val name = varchar("name", 50) // Column<String>
    val project = reference("project", Projects)
}

class Task(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Task>(Tasks)

    var name by Tasks.name
    var project by Project referencedOn Tasks.project
    var assignees by User via TaskAssignees
}
