package webapp.workitem

import cats.data.Reader
import webapp.common.Utils

trait WorkItemContext extends WorkItemDomain
class WorkItemHandler(ctx: WorkItemContext) {
  import WorkItemService._

  def bindGetAll: Either[Exception, Seq[WorkItem]] = ctx.getAllWorkItems

  def bindGetById(id: String): Either[Exception, WorkItem] = for {
    uuid     <- Utils.parseId(id)
    workItem <- ctx.getWorkItemById(uuid)
  } yield workItem

  def bindPost(workItem: WorkItem): Either[Exception, WorkItem] = ctx.saveWorkItem(workItem)

  def bindDeleteById(id: String): Either[Exception, WorkItem] = for {
    uuid     <- Utils.parseId(id)
    workItem <- ctx.deleteWorkItemById(uuid)
  } yield workItem

}

object WorkItemReaderHandler {
  def bindGetAll: Reader[WorkItemContext, Either[Exception, Seq[WorkItem]]] = WorkItemReaderService.getAllWorkItems

  def bindGetById(id: String): Reader[WorkItemContext, Either[Exception, WorkItem]] = Reader(ctx =>
    for {
      uuid     <- Utils.parseId(id)
      workItem <- WorkItemReaderService.getWorkItemById(uuid)(ctx)
    } yield workItem
  )
  def bindPost(workItem: WorkItem): Reader[WorkItemContext, Either[Exception, WorkItem]] = WorkItemReaderService.saveWorkItem(workItem)

  def bindDeleteById(id: String): Reader[WorkItemContext, Either[Exception, WorkItem]] = Reader(ctx =>
    for {
      uuid     <- Utils.parseId(id)
      workItem <- WorkItemReaderService.deleteWorkItemById(uuid)(ctx)
    } yield workItem
  )
}
