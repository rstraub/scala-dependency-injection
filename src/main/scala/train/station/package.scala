package train

import train.loading.TrainLoader
import train.shunting.TrainShunter

package object station {
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
