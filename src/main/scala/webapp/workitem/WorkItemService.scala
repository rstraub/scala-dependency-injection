package webapp.workitem

import cats.data.Reader

import java.util.UUID

trait WorkItemDomain extends HasWorkItemDataStore
object WorkItemService {
  implicit class WorkItemService(ctx: WorkItemDomain) {
    def getAllWorkItems: Either[Exception, Seq[WorkItem]] = ctx.workItemDataStore.getAllWorkItems

    def getWorkItemById(id: UUID): Either[Exception, WorkItem] = ctx.workItemDataStore.getWorkItemById(id)

    def getWorkItemByUserId(userId: UUID): Either[Exception, WorkItem] = ctx.workItemDataStore.getWorkItemByUserId(userId)

    def saveWorkItem(workItem: WorkItem): Either[Exception, WorkItem] = ctx.workItemDataStore.saveWorkItem(workItem)

    def deleteWorkItemById(id: UUID): Either[Exception, WorkItem] = ctx.workItemDataStore.deleteWorkItemById(id)
  }
}

object WorkItemReaderService {
  def getAllWorkItems: Reader[WorkItemDomain, Either[Exception, Seq[WorkItem]]] = Reader(ctx => ctx.workItemDataStore.getAllWorkItems)

  def getWorkItemById(id: UUID): Reader[WorkItemDomain, Either[Exception, WorkItem]] = Reader(ctx => ctx.workItemDataStore.getWorkItemById(id))

  def getWorkItemByUserId(userId: UUID): Reader[WorkItemDomain, Either[Exception, WorkItem]] = Reader(ctx => ctx.workItemDataStore.getWorkItemByUserId(userId))

  def saveWorkItem(workItem: WorkItem): Reader[WorkItemDomain, Either[Exception, WorkItem]] = Reader(ctx => ctx.workItemDataStore.saveWorkItem(workItem))

  def deleteWorkItemById(id: UUID): Reader[WorkItemDomain, Either[Exception, WorkItem]] = Reader(ctx => ctx.workItemDataStore.deleteWorkItemById(id))
}
