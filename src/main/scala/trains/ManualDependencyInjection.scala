package trains

object ManualDependencyInjection extends App {
  lazy val trainCarCoupler = new TrainCarCoupler()
  lazy val trainShunter = new TrainShunter(pointSwitcher, trainCarCoupler)
  // Causes nullpointer (without lazy), used before instantiated
  lazy val pointSwitcher = new PointSwitcher()

  lazy val craneController = new CraneController()
  lazy val trainLoader = new TrainLoader(craneController, pointSwitcher)

  lazy val trainDispatch = new TrainDispatch()

  lazy val trainStation =
    new TrainStation(trainShunter, trainLoader, trainDispatch)

  trainStation.prepareAndDispatchNextTrain()
}
