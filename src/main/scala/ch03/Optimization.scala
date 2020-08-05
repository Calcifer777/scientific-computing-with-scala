package ch03

import breeze.linalg._
import scala.math.{pow}
import breeze.optimize._

object Optimization extends App {

  def fn(x: DenseVector[Double]): Double = 
    pow(x(0) + 1.0, 2.0) +
      pow(x(1) - 2.0, 2.0) +
      pow(x(2) + 4.0, 2.0)

  def gradient(x: DenseVector[Double]): DenseVector[Double] = 
    DenseVector(
      2.0 * (x(0) + 1),
      2.0 * (x(1) - 2.0),
      2.0 * (x(2) + 4.0)
    )

  val approxDiffFn = new ApproximateGradientFunction(fn)

  val diffFn = new DiffFunction[DenseVector[Double]] {
    def calculate(x: DenseVector[Double]) = {
      (fn(x), gradient(x))
    }
  }

  val minimum = DenseVector(-1.0, 2.0, -4.0)
  println(diffFn.valueAt(minimum))
  println(diffFn.gradientAt(minimum))
  println(diffFn.calculate(minimum))

  val lbfgs = new LBFGS[DenseVector[Double]](
    maxIter = 100,
    m = 10,
    tolerance = 0.001
  )
  val solution = lbfgs.minimize(diffFn, DenseVector(0.0, 0.0, 0.0))
  println(s"Minimum: $solution")

}
