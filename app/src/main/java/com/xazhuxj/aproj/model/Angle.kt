package com.xazhuxj.aproj.model

import com.xazhuxj.aproj.model.SurMath

class Angle(d: Int=0, m: Int=0, s: Double=0.0) { //以 度、分、秒 为单位构造角度
    private var _d:Int = 0//度
    var d: Int
        get() = _d
        set(value) {
            _d = value
            calculate()
        }

    private var _m = 0 //分
    var m: Int
        get() = _m
        set(value) {
            _m = value
            calculate()
        }

    private var _s:Double = 0.0 //秒
    var s:Double
        get() = _s
        set(value) {
            _s = value
            calculate()
        }

    private var _degree = 0.0 //角度值
    var degree
        get() = _degree
        set(value) {
            _degree = value
            _radian = SurMath.degreeToRad(_degree)
            val dms = SurMath.radToDMS(_radian)
            _d = dms.first
            _m = dms.second
            _s = dms.third
        }

    private var _radian = 0.0//弧度值
    var radian
        get() = _radian
        set(value) {
            _radian = value
            _degree = SurMath.radToDegree(_radian)
            val dms = SurMath.radToDMS(_radian)
            _d = dms.first
            _m = dms.second
            _s = dms.third
        }

    constructor(degree: Double) : this() { //以度为单位的构造函数
        this.degree = degree
    }

    private fun calculate() { //根据度分秒值计算度与弧度值
        _degree = SurMath.dmsToDegree(_d, _m, _s)
        _radian = SurMath.dmsToRad(_d, _m, _s)
    }

    init {
        this._d = d
        this._m = m
        this._s = s
        calculate()
    }
}