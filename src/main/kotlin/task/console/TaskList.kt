package task.console

import console.Command
import project.Project
import task.Task
import task.Tasks
import java.util.*

class TaskList : Command {
    override fun name(): String {
        return "Список задач"
    }

    override fun start() {
        println("Введите ID проекта")

        val uuid = try {
            UUID.fromString(readLine().toString())
        } catch (e: Exception) {
            println(e.message)

            return
        }

        val project = Project.findById(uuid)

        if (null == project) {
            println("Проект с ID $uuid не найден.")

            return
        }

        val tasks = Task.find { Tasks.project eq project.id }

        if (tasks.empty()) {
            println("Список задач для проекта \"${project.name}\" пуст.")

            return
        }

        tasks.forEach {
            println("${it.id}: ${it.name}")
        }
    }
}
