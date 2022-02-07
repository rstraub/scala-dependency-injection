package train

import com.softwaremill.macwire.wire
import train.loading.TrainLoader
import train.shunting.TrainShunter

package object station {
  trait StationModule {
    lazy val trainDispatch: TrainDispatch = wire[TrainDispatch]

    lazy val trainStation: TrainStation = wire[TrainStation]

    // dependencies of the module
    def trainShunter: TrainShunter
    def trainLoader: TrainLoader
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
      trainLoader.load()
      trainDispatch.dispatch()
    }
  }
}
