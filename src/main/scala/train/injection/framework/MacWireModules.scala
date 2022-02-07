package train.injection.framework

object MacWireModules extends App {
  val modules = new ShuntingModule with LoadingModule with StationModule

  modules.trainStation.prepareAndDispatchNextTrain()
}
