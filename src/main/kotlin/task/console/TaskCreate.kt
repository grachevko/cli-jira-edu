package task.console

import console.Command
import project.Project
import task.Task
import java.util.*

class TaskCreate : Command {
    override fun name(): String {
        return "Создать задачу"
    }

    override fun start() {
        println("Введите ID проекта")

        val uuid = try {
            UUID.fromString(readLine().toString())
        } catch (e: Exception) {
            println(e.message)

            return
        }

        val projectEntity = Project.findById(uuid)

        if (null == projectEntity) {
            println("Проект с ID $uuid не найден.")

            return
        }

        println("Введите имя задачи")
        val inputName = readLine().toString()

        val task = Task.new {
            name = inputName
            project = projectEntity
        }

        println("Создана задача ${task.name} с ID: ${task.id}")
    }
}
