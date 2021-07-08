package project.console

import console.Command
import project.Project

class ProjectCreate : Command {
    override fun name(): String {
        return "Создать проект"
    }

    override fun start() {
        println("Введите название проекта")

        val inputName = readLine().toString()

        val project = Project.new {
            name = inputName
        }

        println("Создан проект ${project.name} с ID: ${project.id}")
    }
}
