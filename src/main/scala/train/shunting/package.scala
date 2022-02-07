package train

package object shunting {
  class PointSwitcher() {
    def switch(): Unit = println("switching")
  }
  class TrainCarCoupler() {
    def couple(): Unit = println("coupling")
  }
  class TrainShunter(pointSwitcher: PointSwitcher,
                     trainCarCoupler: TrainCarCoupler) {
    def shunt(): Unit = {
      println("shunting")
      pointSwitcher.switch()
      trainCarCoupler.couple()
    }
  }
}
