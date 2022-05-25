package com.xazhuxj.aproj.model

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class Ellipsoid(val a: Double, val f: Double, val id: String="", val name: String ="") {

    val IsCustomEllipsoid: Boolean
        get() = id == "CS00"

    private val b: Double = a - a / f
    private val e2: Double = 1 - b / a * b / a
    private val eT2: Double = a / b * a / b - 1

    private val m0: Double = a * (1 - e2)
    private val e4: Double = e2 * e2
    private val e6: Double = e4 * e2
    private val e8: Double = e6 * e2

    private val A0: Double = (1
            + 0.75 * e2
            + 45.0 / 64.0 * e4
            + 175.0 / 256.0 * e6
            + 11025.0 / 16384.0 * e8) * m0
    private val A2: Double = -0.5 * (0.75 * e2
            + 15.0 / 16.0 * e4
            + 525.0 / 512.0 * e6
            + 2205.0 / 2048.0 * e8) * m0
    private val A4: Double = 0.25 * (15.0 / 64.0 * e4
            + 105.0 / 256.0 * e6
            + 2205.0 / 4096.0 * e8) * m0
    private val A6: Double = -(35.0 / 512.0 * e6
            + 315.0 / 2048.0 * e8) * m0 / 6.0
    private val A8: Double = 315.0 / 16384.0 * e8 * m0 / 8.0


    fun funX(B: Double) = A0 * B + A2 * sin(2 * B) + A4 * sin(4 * B) + A6 * sin(6 * B) + A8 * sin(8 * B)


    fun funBf(x: Double) :Double
    {
        var B0 = x / A0
        var Bi = 0.0

        var i = 0
        while (i < 1000)
        {
            i++
            Bi = (x - (
                    +A2 * sin(2 * B0)
                            + A4 * sin(4 * B0)
                            + A6 * sin(6 * B0)
                            + A8 * sin(8 * B0))) / A0

            if (abs(Bi - B0) < 1e-10) break

            B0 = Bi
        }

        return Bi
    }

    fun funM(sinB2: Double) = a * (1 - e2) / (1 - e2 * sinB2).pow(1.5)

    fun funN(sinB2: Double) =  a / sqrt(1 - e2 * sinB2)

    fun funR(sinB2: Double) = sqrt(funM(sinB2) * funN(sinB2))

    fun funG2(cosB2 : Double) = eT2 * cosB2
}