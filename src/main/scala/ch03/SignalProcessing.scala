package ch03

import breeze.linalg._
import scala.math._
import breeze.signal._
import breeze.plot._
import breeze.math.Complex


object Process {
  def generate(index: Int, size: Int): Double = {
    val fIndex = index.toDouble
    val fSize = size.toDouble
    (0.1 * sin(fIndex / fSize * 5.0 * 2.0 * Pi) +
      0.4 * sin(fIndex / fSize * 10.0 * 2.0 * Pi) +
      0.5 * sin(fIndex / fSize * 50.0 * 2.0 * Pi))
  }
}

object PlotSignal extends App {

  val signal = DenseVector.tabulate(512) { i => Process.generate(i, 512) }
  val f = Figure()
  val p = f.subplot(0)
  val x = linspace(0.0, 512.0, 512)
  p += plot(x, signal)
  p.xlabel = "sample"
  p.ylabel = "value"
  // f.saveas("signal.png")
}

object SignalProcessing extends App {

  val signal = DenseVector.tabulate(512){i => Process.generate(i, 512)}
  val f = Figure()
  val p = f.subplot(0)
  val x = linspace(0.0, 256.0, 256)
  val csignal = for (sample <- signal)
    yield new Complex(sample, 0.0)
  val transformed = fourierTr(csignal)
  val amplitudes = for (bin <- transformed)
    yield bin.abs
  val firstHalf = amplitudes(0 until 256)
  p += plot(x, firstHalf)
  p.xlabel = "bin"
  p.ylabel = "amplitude"
  // f.saveas("transformed.png")
}
