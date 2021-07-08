package user

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Users : UUIDTable() {
    val name = varchar("name", length = 50)
}

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<User>(Users)

    var name by Users.name
}
