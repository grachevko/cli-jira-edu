import console.Resolver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import project.ProjectAssignees
import project.Projects
import project.console.ProjectAssign
import project.console.ProjectCreate
import project.console.ProjectDelete
import project.console.ProjectList
import task.TaskAssignees
import task.Tasks
import task.console.TaskAssign
import task.console.TaskCreate
import task.console.TaskDelete
import task.console.TaskList
import user.Users
import user.console.UserCreate
import user.console.UserDelete
import user.console.UserList

fun main(args: Array<String>) = runBlocking {
    val resolver = Resolver(
        mapOf(
            1 to UserCreate(),
            2 to UserList(),
            3 to UserDelete(),
            4 to ProjectCreate(),
            5 to ProjectList(),
            6 to ProjectDelete(),
            7 to ProjectAssign(),
            8 to TaskCreate(),
            9 to TaskList(),
            10 to TaskDelete(),
            11 to TaskAssign(),
        )
    )

    val databaseUrl = args.getOrNull(0) ?: "jdbc:sqlite:db.sqlite"

    Database.connect(databaseUrl)

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(
            Users,
            Projects,
            ProjectAssignees,
            Tasks,
            TaskAssignees,
        )
    }

    withContext(Dispatchers.Default) {
        loop(resolver)
    }
}

fun loop(resolver: Resolver) {
    while (true) {
        println("Список доступных команд:\n${resolver.list()} \n\nВведите команду")

        val input = readLine()?.toIntOrNull() ?: break
        val command = resolver.resolve(input)

        if (null == command) {
            println("Команда \"$input\" не найдена\n")

            continue
        }

        println("Выбрана команда: ${command.name()}")

        transaction {
            command.start()
        }

        println()
    }
}
