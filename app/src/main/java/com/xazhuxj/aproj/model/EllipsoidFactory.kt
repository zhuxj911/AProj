package com.xazhuxj.aproj.model

import com.xazhuxj.aproj.model.Ellipsoid

object EllipsoidFactory {
    val ellipsoids = listOf(
        Ellipsoid(6378137.0, 298.257222101, "CGCS2000", "CGCS2000大地坐标系"),
        Ellipsoid(6378137.0, 298.257223563, "WGS84", "WGS84坐标系"),
        Ellipsoid(6378140.0, 298.257, "XA80", "80西安坐标系"),
        Ellipsoid(6378245.0, 298.3, "BJ54", "54北京坐标系"),
        Ellipsoid(6378137.0, 298.257222101, "CS00", "自定义坐标系"),
    )
}