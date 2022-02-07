package train.injection.manual

import train.shunting.{PointSwitcher, TrainCarCoupler, TrainShunter}

trait ShuntingModule {
  lazy val pointSwitcher: PointSwitcher = new PointSwitcher()
  lazy val trainCarCoupler: TrainCarCoupler = new TrainCarCoupler()
  lazy val trainShunter: TrainShunter =
    new TrainShunter(pointSwitcher, trainCarCoupler)
}
