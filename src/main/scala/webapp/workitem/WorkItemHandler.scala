package webapp.workitem

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
