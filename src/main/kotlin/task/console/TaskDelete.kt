package task.console

import console.Command
import org.jetbrains.exposed.sql.deleteWhere
import task.Tasks
import user.Users
import java.util.*

class TaskDelete : Command {
    override fun name(): String {
        return "Удалить задачу"
    }

    override fun start() {
        println("Введите id задачи")

        val inputId = readLine().toString()

        Tasks.deleteWhere { Users.id eq UUID.fromString(inputId) }
    }
}
