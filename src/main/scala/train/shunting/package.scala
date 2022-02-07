package train

import com.softwaremill.macwire.wire

package object shunting {
  trait ShuntingModule {
    lazy val pointSwitcher: PointSwitcher = wire[PointSwitcher]
    lazy val trainCarCoupler: TrainCarCoupler = wire[TrainCarCoupler]
    lazy val trainShunter: TrainShunter = wire[TrainShunter]
  }
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
