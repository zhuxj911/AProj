package com.xazhuxj.aproj.model

import kotlin.math.abs

object SurMath { //kotlin 静态类的写法， 部分静态方法采用伴随对象方式实现 companion object

//    fun dmsToRad(dmsAngle: Double) : Double{
//        val dms : Double = dmsAngle * 10000
//        var angle = dms.toInt()
//        val d: Int  = angle / 10000
//        angle  -= d * 10000
//        val m: Int  = angle / 100
//        val s: Double  = dms - d * 10000 - m * 100
//
//        return (d + m/60.0 + s/3600.0)/180.0 * Math.PI
//    }

    /**
     * 度分秒角度提取度、分、秒
     * 101 02 20.1 => 101.02201  -> 101 02 20.1
     * 1 40 00 => 1.4  -> 1 40 00
     */
    fun dmsToDMS(dmsAngle: Double): Triple<Int, Int, Double> {
        val dms: Double = dmsAngle * 10000
        var angle: Int = dms.toInt()
        val d: Int = angle / 10000
        angle -= d * 10000
        val m: Int = angle / 100
        val s: Double = dms - d * 10000 - m * 100

        return Triple(d, m, s)
    }

    fun degreeToRad(degree : Double) :Double = degree / 180.0 * Math.PI

    fun dmsToDegree(d:Int, m:Int, s:Double): Double = d + m / 60.0 + s / 3600.0

    fun dmsToRad(d:Int, m:Int, s:Double): Double = degreeToRad(dmsToDegree(d, m, s))

    fun dmsToRad(dmsAngle: Double): Double {
        val dms = dmsToDMS(dmsAngle)
        return dmsToRad(dms.first, dms.second, dms.third)
    }

    fun dmsToString(dms:Triple<Int, Int, Double>) : String{
        val f = if((dms.first + dms.second/60.0 + dms.third/3600.0) >=0) 1 else -1
        val ff = if(f == 1)  "" else "-"
        return if (abs(dms.third) < 1e-10)
            "$ff${f * dms.first}°${f * dms.second}′0″";
        else
            "$ff${f * dms.first}°${f * dms.second}′${f * dms.third}″";
    }

    fun dmsToDmsString(dmsAngle:Double) : String = dmsToString( dmsToDMS(dmsAngle))


/////////////////////////////////////////////////////////////////////////////

    /**
     * 核心函数
     * 思路：先将弧度转换为秒，再提取相应的度分秒值
     */
    fun radToDMS(radAngle: Double): Triple<Int, Int, Double> {
        val rad = radAngle * 180.0 * 3600.0 / Math.PI
        var angle = rad.toInt()
        val d = angle / 3600
        angle -= d * 3600
        val m = angle / 60
        val s = rad - d * 3600 - m * 60
        return Triple(d, m, s)
    }

//    fun radToDms(radAngle: Double): Double {
//        val rad = radAngle * 180.0 * 3600.0 / Math.PI
//        var angle = rad.toInt()
//        val d = angle / 3600
//        angle -= d * 3600
//        val m = angle / 60
//        val s = rad - d * 3600 - m * 60
//        return d + m / 100.0 + s / 10000.0
//    }

    /**
     * 将弧度转换为度值
     */
    fun radToDegree(radAngle: Double): Double {
        val dms = radToDMS(radAngle)
        return dms.first + dms.second / 60.0 + dms.third / 3600.0
    }

    /**
     * 将弧度转换为度分秒的组合值
     */
    fun radToDms(radAngle: Double): Double {
        val dms = radToDMS(radAngle)
        return dms.first + dms.second / 100.0 + dms.third / 10000.0
    }

    fun radToDmsString(radAngle: Double) : String = dmsToString(radToDMS(radAngle))
}
