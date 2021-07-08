package task.console

import console.Command
import org.jetbrains.exposed.sql.SizedCollection
import task.Task
import task.Tasks
import user.User
import java.util.*

class TaskAssign : Command {
    override fun name(): String {
        return "Назначить исполнителя на задачу"
    }

    override fun start() {
        println("Введите id задачи")

        val taskId = readLine().toString()

        val task = Task.find() { Tasks.id eq UUID.fromString(taskId) }.singleOrNull()

        if (null == task) {
            println("Задача с ID $taskId не найден")

            return
        }

        println("Введите id пользователя")

        val userId = readLine().toString()

        val user = User.findById(UUID.fromString(userId))
        if (null == user) {
            println("Пользователь с ID $userId не найден.")

            return
        }

        val assigneesList = task.assignees.toMutableList()
        assigneesList.add(user);

        task.assignees = SizedCollection(assigneesList)
    }
}
