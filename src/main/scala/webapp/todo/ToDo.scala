package webapp.todo

import webapp.user.User

import java.time.LocalDateTime
import java.util.UUID

case class ToDo(id: UUID, description: String, due: LocalDateTime, assignedUser: Option[User])
