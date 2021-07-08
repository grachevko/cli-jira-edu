package user.console

import console.Command
import org.jetbrains.exposed.sql.selectAll
import user.Users

class UserList : Command {
    override fun name(): String {
        return "Список пользователей"
    }

    override fun start() {
        val users = Users.selectAll()

        if (users.empty()) {
            println("Список пользователей пуст")

            return
        }

        users.forEach {
            println("${it[Users.id]}: ${it[Users.name]}")
        }
    }
}
