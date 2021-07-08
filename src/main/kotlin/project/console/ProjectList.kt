package project.console

import console.Command
import org.jetbrains.exposed.sql.selectAll
import project.Projects

class ProjectList : Command {
    override fun name(): String {
        return "Список проектов"
    }

    override fun start() {
        val projects = Projects.selectAll()

        if (projects.empty()) {
            println("Список проектов пуст")

            return
        }

        projects.forEach {
            println("${it[Projects.id]}: ${it[Projects.name]}")
        }
    }
}
