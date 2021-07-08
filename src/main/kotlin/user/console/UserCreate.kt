package user.console

import console.Command
import user.User

class UserCreate : Command {
    override fun name(): String {
        return "Создать пользователя"
    }

    override fun start() {
        println("Введите имя пользователя")

        val inputName = readLine().toString()

        val user = User.new {
            name = inputName
        }

        println("Создан пользователь ${user.name} с ID: ${user.id}")
    }
}
