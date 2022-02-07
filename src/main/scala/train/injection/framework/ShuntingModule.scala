package train.injection.framework

import com.softwaremill.macwire.wire
import train.shunting.{PointSwitcher, TrainCarCoupler, TrainShunter}

trait ShuntingModule {
  lazy val pointSwitcher: PointSwitcher = wire[PointSwitcher]
  lazy val trainCarCoupler: TrainCarCoupler = wire[TrainCarCoupler]
  lazy val trainShunter: TrainShunter = wire[TrainShunter]
}
