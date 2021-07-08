package user.console

import console.Command
import org.jetbrains.exposed.sql.deleteWhere
import user.Users
import java.util.*

class UserDelete : Command {
    override fun name(): String {
        return "Удалить пользователя"
    }

    override fun start() {
        println("Введите id пользователя")

        val inputId = readLine().toString()

        Users.deleteWhere { Users.id eq UUID.fromString(inputId) }
    }
}
