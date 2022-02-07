package object trains {
  class PointSwitcher() {
    def switch(): Unit = println("switching")
  }
  class TrainCarCoupler()
  class TrainShunter(pointSwitcher: PointSwitcher,
                     trainCarCoupler: TrainCarCoupler) {
    def shunt(): Unit = {
      println("shunting")
      pointSwitcher.switch()
    }
  }

  class CraneController() {
    def move(): Unit = println("move with crane")

  }

  class TrainLoader(craneController: CraneController,
                    pointSwitcher: PointSwitcher) {
    def load(): Unit = {
      println("loading train")
      craneController.move()
      pointSwitcher.switch()
    }
  }

  class TrainDispatch() {
    def dispatch(): Unit = println("dispatching train")
  }

  class TrainStation(trainShunter: TrainShunter,
                     trainLoader: TrainLoader,
                     trainDispatch: TrainDispatch) {

    def prepareAndDispatchNextTrain(): Unit = {
      println("preparing and dispatching next train")
      trainShunter.shunt()
    }
  }
}
