package project

import org.jetbrains.exposed.sql.Table
import user.Users

object ProjectAssignees : Table(name = "project_assignees") {
    val project = reference("project", Projects)
    val user = reference("user", Users)

    override val primaryKey = PrimaryKey(project, user)
}
