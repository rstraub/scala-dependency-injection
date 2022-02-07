package webapp.workitem

import cats.implicits._

import java.time.LocalDateTime
import java.util.UUID

class LiveWorkItemDataStore extends WorkItemDataStore {

  private var workItems: List[WorkItem] = List(
    WorkItem(
      description = "Do something",
      due = LocalDateTime.now().plusDays(2),
      assignedUserId = Some(UUID.fromString("e9918fd2-6e44-4f30-845b-677ab286ea5a"))
    ),
    WorkItem(description = "Do something else", due = LocalDateTime.now().plusDays(4))
  )

  override def getAllWorkItems: Either[Exception, Seq[WorkItem]] = Right(workItems)

  override def getWorkItemById(id: UUID): Either[Exception, WorkItem] =
    Either.fromOption(workItems.find(_.id == id), new RuntimeException(s"Work item with uuid $id not found"))

  override def getWorkItemByUserId(userId: UUID): Either[Exception, WorkItem] =
    Either.fromOption(workItems.find(_.assignedUserId.contains(userId)), new RuntimeException(s"Work item for user with id $userId not found"))

  override def saveWorkItem(workItem: WorkItem): Either[Exception, WorkItem] = {
    workItems = workItem :: workItems
    Right(workItem)
  }

  override def deleteWorkItemById(id: UUID): Either[Exception, WorkItem] = getWorkItemById(id)
    .map { workItem =>
      workItems = workItems.filterNot(_.id == id)
      workItem
    }

}
