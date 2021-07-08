package project.console

import console.Command
import org.jetbrains.exposed.sql.deleteWhere
import project.Projects
import java.util.*

class ProjectDelete : Command {
    override fun name(): String {
        return "Удалить проект"
    }

    override fun start() {
        println("Введите id проекта")

        val inputId = readLine().toString()

        Projects.deleteWhere { Projects.id eq UUID.fromString(inputId) }
    }
}
