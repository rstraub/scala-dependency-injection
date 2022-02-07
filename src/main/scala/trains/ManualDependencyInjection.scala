package trains

object ManualDependencyInjection extends App {
  val trainCarCoupler = new TrainCarCoupler()
  val pointSwitcher = new PointSwitcher()
  val trainShunter = new TrainShunter(pointSwitcher, trainCarCoupler)

  val craneController = new CraneController()
  val trainLoader = new TrainLoader(craneController, pointSwitcher)

  val trainDispatch = new TrainDispatch()

  val trainStation = new TrainStation(trainShunter, trainLoader, trainDispatch)

  trainStation.prepareAndDispatchNextTrain()
}
