package webapp.user

import java.util.UUID

case class User(id: UUID = UUID.randomUUID(), name: String)
