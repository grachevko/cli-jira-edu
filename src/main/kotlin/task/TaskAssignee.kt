package task

import org.jetbrains.exposed.sql.Table
import user.Users

object TaskAssignees : Table(name = "task_assignees") {
    val task = reference("task", Tasks)
    val user = reference("user", Users)

    override val primaryKey = PrimaryKey(task, user)
}
