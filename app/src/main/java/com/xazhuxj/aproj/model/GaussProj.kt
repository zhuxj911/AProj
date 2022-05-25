package com.xazhuxj.aproj

import com.xazhuxj.aproj.model.Ellipsoid
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class GaussProj(private val ellipsoid: Ellipsoid) {

    fun BLtoXY(
        B: Double,
        L: Double,
        L0: Double,
        YKM: Double,
        Zone: Int
    ): Result {
        val l = L - L0

        val sinB = sin(B)
        val cosB = cos(B)
        val cosB2 = cosB * cosB
        val cosB4 = cosB2 * cosB2

        val t = tan(B)
        val t2 = t * t
        val t4 = t2 * t2

        val g2 = ellipsoid.funG2(cosB2)
        val g4 = g2 * g2

        val l2 = l * l
        val l4 = l2 * l2

        val N = ellipsoid.funN(sinB * sinB)

        val x = ellipsoid.funX(B) + N * sinB * cosB * l2 * (
                0.5
                        + cosB2 / 24.0 * (5 - t2 + 9 * g2 + 4 * g4) * l2
                        + cosB4 / 720.0 * (61 - 58 * t2 + t4) * l4)
        val y = N * cosB * l * (
                1
                        + cosB2 / 6.0 * (1 - t2 + g2) * l2
                        + cosB4 / 120.0 * (5 - 18 * t2 + t4 + 14 * g2 - 58 * g2 * t2) * l4)
        val gamma = sinB * l * (
                1
                        + (1 + 3 * g2 + 2 * g4) / 3.0 * cosB2 * l2
                        + (2 - t2) / 15.0 * cosB4 * l4)
        val m = 1 + 0.5 * l2 * cosB2 * (1 + g2) + l4 * cosB4 * (5 - 4 * t2) / 24.0

        return Result(x, y + YKM * 1000 + Zone * 1000000, B, L, gamma, m)
    }

    fun XYtoBL(
        x: Double,
        y: Double,
        L0: Double,
        YKM: Double,
        Zone: Int
    ): Result {
        val yy = y - YKM * 1000 - Zone * 1000000

        val Bf = ellipsoid.funBf(x)
        val tf = tan(Bf)
        val tf2 = tf * tf
        val tf4 = tf2 * tf2

        val sinBf = sin(Bf)
        val sinBf2 = sinBf * sinBf

        val Mf = ellipsoid.funM(sinBf2)
        val Nf = ellipsoid.funN(sinBf2)
        val Nf2 = Nf * Nf
        val Nf4 = Nf2 * Nf2

        val cosBf = cos(Bf)
        val gf2 = ellipsoid.funG2(cosBf * cosBf)

        val y2 = yy * yy
        val y4 = y2 * y2

        val B = Bf + tf / Mf / Nf * y2 * (
                -0.5
                        + y2 / 24.0 / Nf2 * (5 + 3 * tf2 + gf2 - 9 * gf2 * tf2)
                        - y4 / 720.0 / Nf4 * (61 + 90 * tf2 + 45 * tf4)
                )
        val l = y / Nf / cosBf * (
                1
                        - y2 / 6.0 / Nf2 * (1 + 2 * tf2 + gf2)
                        + y4 / 120.0 / Nf4 * (5 + 28 * tf2 + 24 * tf4 + 6 * gf2 + 8 * gf2 * tf2)
                )

        val gamma = tf / Nf * y * (
                1
                        - (1 + tf2 - gf2) / 3.0 / Nf2 * y2
                        + (2 + 5 * tf2 + 3 * tf4) / 15.0 / Nf4 * y4
                )

        val sinB = sin(B)
        val sinB2 = sinB * sinB
        val R = ellipsoid.funR(sinB2)
        val R2 = R * R
        val R4 = R2 * R2
        val m = 1 + y2 / 2.0 / R2 + y4 / 24.0 / R4

        return Result(x, y, B, l + L0, gamma, m)
    }
}

data class Result(
    val x: Double, val y: Double,
    val B: Double, val L: Double,
    val gamma: Double, val m: Double
)