package webapp.workitem

import java.time.LocalDateTime
import java.util.UUID

case class WorkItem(id: UUID = UUID.randomUUID(), description: String, due: LocalDateTime, assignedUserId: Option[UUID] = None)
