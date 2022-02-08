package webapp.workitem

import java.util.UUID

trait HasWorkItemDataStore {
  val workItemDataStore: WorkItemDataStore
}

trait WorkItemDataStore {

  def getAllWorkItems: Either[Exception, Seq[WorkItem]]

  def getWorkItemById(id: UUID): Either[Exception, WorkItem]

  def getWorkItemByUserId(userId: UUID): Either[Exception, WorkItem]

  def saveWorkItem(workItem: WorkItem): Either[Exception, WorkItem]

  def deleteWorkItemById(id: UUID): Either[Exception, WorkItem]

}
