package train.manual

import org.scalatest.funspec.AnyFunSpec
import train.injection.manual.ManualModules

class LoadingSpec extends AnyFunSpec {
  describe("loading") {
    it("should use test implementation given test module") {
      val loader = new TestLoadingModule().trainLoader
      loader.load()
    }

    it("should use real implementation given real module") {
      val loader = ManualModules.modules.trainLoader
      loader.load()
    }
  }
}
