package train.injection.manual

object ManualModules extends App {
  val modules = new ShuntingModule with LoadingModule with StationModule

  modules.trainStation.prepareAndDispatchNextTrain()
}
