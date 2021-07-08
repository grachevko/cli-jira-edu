package console

class Resolver(private var commands: Map<Int, Command>) {
    fun resolve(command: Int): Command? {
        return commands[command]
    }

    fun list(): String {
        return commands.map { (key, value) -> "$key. ${value.name()}" }.joinToString(separator = "\n")
    }
}
