package console

class Resolver(private var commands: Map<Int, Command>) {
    fun resolve(command: Int?): Command? {
        return if (null == command) null else commands[command]
    }

    fun list(): String {
        return commands.map { (key, value) -> "$key. ${value.name()}" }.joinToString(separator = "\n")
    }
}
