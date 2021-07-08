package project.console

import console.Command
import org.jetbrains.exposed.sql.SizedCollection
import project.Project
import project.Projects
import user.User
import java.util.*

class ProjectAssign : Command {
    override fun name(): String {
        return "Назначить исполнителя на проект"
    }

    override fun start() {
        println("Введите id проекта")

        val projectId = readLine().toString()

        val project = Project.find() { Projects.id eq UUID.fromString(projectId) }.singleOrNull()

        if (null == project) {
            println("Проект с ID $projectId не найден")

            return
        }

        println("Введите id пользователя")

        val userId = readLine().toString()

        val user = User.findById(UUID.fromString(userId))
        if (null == user) {
            println("Пользователь с ID $userId не найден.")

            return
        }

        val assigneesList = project.assignees.toMutableList()
        assigneesList.add(user)

        project.assignees = SizedCollection(assigneesList)
    }
}
