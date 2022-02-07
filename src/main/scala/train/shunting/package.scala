package train

import com.softwaremill.macwire.wire

package object shunting {
  trait ShuntingModule {
    lazy val pointSwitcher: PointSwitcher = wire[PointSwitcher]
    lazy val trainCarCoupler: TrainCarCoupler = wire[TrainCarCoupler]
    lazy val trainShunter: TrainShunter = wire[TrainShunter]
  }
}
