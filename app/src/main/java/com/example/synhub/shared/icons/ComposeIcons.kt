package com.example.synhub.shared.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

private var _personSVG: ImageVector? = null
public val personSVG: ImageVector
    get() {
        if (_personSVG != null) {
            return _personSVG!!
        }
        _personSVG = ImageVector.Builder(
            name = "PersonCircle",
            defaultWidth = 22.dp,
            defaultHeight = 22.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11f, 6f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = true, isPositiveArc = true, -6f, 0f)
                arcToRelative(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6f, 0f)
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0f, 8f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, 16f, 0f)
                arcTo(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 8f)
                moveToRelative(8f, -7f)
                arcToRelative(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = false, -5.468f, 11.37f)
                curveTo(3.242f, 11.226f, 4.805f, 10f, 8f, 10f)
                reflectiveCurveToRelative(4.757f, 1.225f, 5.468f, 2.37f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = false, 8f, 1f)
            }
        }.build()
        return _personSVG!!
    }

private var _lockSVG: ImageVector? = null
public val lockSVG: ImageVector
    get() {
        if (_lockSVG != null) {
            return _lockSVG!!
        }
        _lockSVG = ImageVector.Builder(
            name = "Lock",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(240f, 880f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(160f, 800f)
                verticalLineToRelative(-400f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(240f, 320f)
                horizontalLineToRelative(40f)
                verticalLineToRelative(-80f)
                quadToRelative(0f, -83f, 58.5f, -141.5f)
                reflectiveQuadTo(480f, 40f)
                reflectiveQuadToRelative(141.5f, 58.5f)
                reflectiveQuadTo(680f, 240f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(40f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(800f, 400f)
                verticalLineToRelative(400f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(720f, 880f)
                close()
                moveToRelative(0f, -80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(-400f)
                horizontalLineTo(240f)
                close()
                moveToRelative(240f, -120f)
                quadToRelative(33f, 0f, 56.5f, -23.5f)
                reflectiveQuadTo(560f, 600f)
                reflectiveQuadToRelative(-23.5f, -56.5f)
                reflectiveQuadTo(480f, 520f)
                reflectiveQuadToRelative(-56.5f, 23.5f)
                reflectiveQuadTo(400f, 600f)
                reflectiveQuadToRelative(23.5f, 56.5f)
                reflectiveQuadTo(480f, 680f)
                moveTo(360f, 320f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(-80f)
                quadToRelative(0f, -50f, -35f, -85f)
                reflectiveQuadToRelative(-85f, -35f)
                reflectiveQuadToRelative(-85f, 35f)
                reflectiveQuadToRelative(-35f, 85f)
                close()
                moveTo(240f, 800f)
                verticalLineToRelative(-400f)
                close()
            }
        }.build()
        return _lockSVG!!
    }

private var _abcSVG: ImageVector? = null
public val abcSVG: ImageVector
    get() {
        if (_abcSVG != null) {
            return _abcSVG!!
        }
        _abcSVG = ImageVector.Builder(
            name = "Abc",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(680f, 600f)
                quadToRelative(-17f, 0f, -28.5f, -11.5f)
                reflectiveQuadTo(640f, 560f)
                verticalLineToRelative(-160f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(680f, 360f)
                horizontalLineToRelative(120f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(840f, 400f)
                verticalLineToRelative(40f)
                horizontalLineToRelative(-60f)
                verticalLineToRelative(-20f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-20f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(40f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(800f, 600f)
                close()
                moveToRelative(-300f, 0f)
                verticalLineToRelative(-240f)
                horizontalLineToRelative(160f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(580f, 400f)
                verticalLineToRelative(40f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(540f, 480f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(580f, 520f)
                verticalLineToRelative(40f)
                quadToRelative(0f, 17f, -11.5f, 28.5f)
                reflectiveQuadTo(540f, 600f)
                close()
                moveToRelative(60f, -150f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-30f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(0f, 90f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-30f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(-320f, 60f)
                verticalLineToRelative(-200f)
                quadToRelative(0f, -17f, 11.5f, -28.5f)
                reflectiveQuadTo(160f, 360f)
                horizontalLineToRelative(120f)
                quadToRelative(17f, 0f, 28.5f, 11.5f)
                reflectiveQuadTo(320f, 400f)
                verticalLineToRelative(200f)
                horizontalLineToRelative(-60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(60f)
                close()
                moveToRelative(60f, -120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-80f)
                close()
            }
        }.build()
        return _abcSVG!!
    }

private var _mailSVG: ImageVector? = null
public val mailSVG: ImageVector
    get() {
        if (_mailSVG != null) {
            return _mailSVG!!
        }
        _mailSVG = ImageVector.Builder(
            name = "Mail",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(160f, 800f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 720f)
                verticalLineToRelative(-480f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 160f)
                horizontalLineToRelative(640f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(880f, 240f)
                verticalLineToRelative(480f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(800f, 800f)
                close()
                moveToRelative(320f, -280f)
                lineTo(160f, 320f)
                verticalLineToRelative(400f)
                horizontalLineToRelative(640f)
                verticalLineToRelative(-400f)
                close()
                moveToRelative(0f, -80f)
                lineToRelative(320f, -200f)
                horizontalLineTo(160f)
                close()
                moveTo(160f, 320f)
                verticalLineToRelative(-80f)
                verticalLineToRelative(480f)
                close()
            }
        }.build()
        return _mailSVG!!
    }

private var _linkSVG: ImageVector? = null
val linkSVG: ImageVector
    get() {
        if (_linkSVG != null) {
            return _linkSVG!!
        }
        _linkSVG = ImageVector.Builder(
            name = "Link",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(440f, 680f)
                horizontalLineTo(280f)
                quadToRelative(-83f, 0f, -141.5f, -58.5f)
                reflectiveQuadTo(80f, 480f)
                reflectiveQuadToRelative(58.5f, -141.5f)
                reflectiveQuadTo(280f, 280f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(80f)
                horizontalLineTo(280f)
                quadToRelative(-50f, 0f, -85f, 35f)
                reflectiveQuadToRelative(-35f, 85f)
                reflectiveQuadToRelative(35f, 85f)
                reflectiveQuadToRelative(85f, 35f)
                horizontalLineToRelative(160f)
                close()
                moveTo(320f, 520f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(200f, 160f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                quadToRelative(50f, 0f, 85f, -35f)
                reflectiveQuadToRelative(35f, -85f)
                reflectiveQuadToRelative(-35f, -85f)
                reflectiveQuadToRelative(-85f, -35f)
                horizontalLineTo(520f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                quadToRelative(83f, 0f, 141.5f, 58.5f)
                reflectiveQuadTo(880f, 480f)
                reflectiveQuadToRelative(-58.5f, 141.5f)
                reflectiveQuadTo(680f, 680f)
                close()
            }
        }.build()
        return _linkSVG!!
    }
private var _groupSVG: ImageVector? = null
public val groupSVG: ImageVector
    get() {
        if (_groupSVG != null) {
            return _groupSVG!!
        }
        _groupSVG = ImageVector.Builder(
            name = "Groups",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(0f, 720f)
                verticalLineToRelative(-63f)
                quadToRelative(0f, -43f, 44f, -70f)
                reflectiveQuadToRelative(116f, -27f)
                quadToRelative(13f, 0f, 25f, 0.5f)
                reflectiveQuadToRelative(23f, 2.5f)
                quadToRelative(-14f, 21f, -21f, 44f)
                reflectiveQuadToRelative(-7f, 48f)
                verticalLineToRelative(65f)
                close()
                moveToRelative(240f, 0f)
                verticalLineToRelative(-65f)
                quadToRelative(0f, -32f, 17.5f, -58.5f)
                reflectiveQuadTo(307f, 550f)
                reflectiveQuadToRelative(76.5f, -30f)
                reflectiveQuadToRelative(96.5f, -10f)
                quadToRelative(53f, 0f, 97.5f, 10f)
                reflectiveQuadToRelative(76.5f, 30f)
                reflectiveQuadToRelative(49f, 46.5f)
                reflectiveQuadToRelative(17f, 58.5f)
                verticalLineToRelative(65f)
                close()
                moveToRelative(540f, 0f)
                verticalLineToRelative(-65f)
                quadToRelative(0f, -26f, -6.5f, -49f)
                reflectiveQuadTo(754f, 563f)
                quadToRelative(11f, -2f, 22.5f, -2.5f)
                reflectiveQuadToRelative(23.5f, -0.5f)
                quadToRelative(72f, 0f, 116f, 26.5f)
                reflectiveQuadToRelative(44f, 70.5f)
                verticalLineToRelative(63f)
                close()
                moveToRelative(-455f, -80f)
                horizontalLineToRelative(311f)
                quadToRelative(-10f, -20f, -55.5f, -35f)
                reflectiveQuadTo(480f, 590f)
                reflectiveQuadToRelative(-100.5f, 15f)
                reflectiveQuadToRelative(-54.5f, 35f)
                moveTo(160f, 520f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 440f)
                quadToRelative(0f, -34f, 23.5f, -57f)
                reflectiveQuadToRelative(56.5f, -23f)
                quadToRelative(34f, 0f, 57f, 23f)
                reflectiveQuadToRelative(23f, 57f)
                quadToRelative(0f, 33f, -23f, 56.5f)
                reflectiveQuadTo(160f, 520f)
                moveToRelative(640f, 0f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(720f, 440f)
                quadToRelative(0f, -34f, 23.5f, -57f)
                reflectiveQuadToRelative(56.5f, -23f)
                quadToRelative(34f, 0f, 57f, 23f)
                reflectiveQuadToRelative(23f, 57f)
                quadToRelative(0f, 33f, -23f, 56.5f)
                reflectiveQuadTo(800f, 520f)
                moveToRelative(-320f, -40f)
                quadToRelative(-50f, 0f, -85f, -35f)
                reflectiveQuadToRelative(-35f, -85f)
                quadToRelative(0f, -51f, 35f, -85.5f)
                reflectiveQuadToRelative(85f, -34.5f)
                quadToRelative(51f, 0f, 85.5f, 34.5f)
                reflectiveQuadTo(600f, 360f)
                quadToRelative(0f, 50f, -34.5f, 85f)
                reflectiveQuadTo(480f, 480f)
                moveToRelative(0f, -80f)
                quadToRelative(17f, 0f, 28.5f, -11.5f)
                reflectiveQuadTo(520f, 360f)
                reflectiveQuadToRelative(-11.5f, -28.5f)
                reflectiveQuadTo(480f, 320f)
                reflectiveQuadToRelative(-28.5f, 11.5f)
                reflectiveQuadTo(440f, 360f)
                reflectiveQuadToRelative(11.5f, 28.5f)
                reflectiveQuadTo(480f, 400f)
                moveToRelative(0f, -40f)
            }
        }.build()
        return _groupSVG!!
    }
private var _membersSVG: ImageVector? = null
public val membersSVG: ImageVector
    get() {
        if (_membersSVG != null) {
            return _membersSVG!!
        }
        _membersSVG = ImageVector.Builder(
            name = "PersonBoundingBox",
            defaultWidth = 20.dp,
            defaultHeight = 20.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(1.5f, 1f)
                arcToRelative(0.5f, 0.5f, 0f, false, false, -0.5f, 0.5f)
                verticalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineToRelative(-3f)
                arcTo(1.5f, 1.5f, 0f, false, true, 1.5f, 0f)
                horizontalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, 1f)
                close()
                moveTo(11f, 0.5f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                horizontalLineToRelative(3f)
                arcTo(1.5f, 1.5f, 0f, false, true, 16f, 1.5f)
                verticalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -1f, 0f)
                verticalLineToRelative(-3f)
                arcToRelative(0.5f, 0.5f, 0f, false, false, -0.5f, -0.5f)
                horizontalLineToRelative(-3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, -0.5f, -0.5f)
                moveTo(0.5f, 11f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, false, 0.5f, 0.5f)
                horizontalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, 1f)
                horizontalLineToRelative(-3f)
                arcTo(1.5f, 1.5f, 0f, false, true, 0f, 14.5f)
                verticalLineToRelative(-3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
                moveToRelative(15f, 0f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, 0.5f)
                verticalLineToRelative(3f)
                arcToRelative(1.5f, 1.5f, 0f, false, true, -1.5f, 1.5f)
                horizontalLineToRelative(-3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0f, -1f)
                horizontalLineToRelative(3f)
                arcToRelative(0.5f, 0.5f, 0f, false, false, 0.5f, -0.5f)
                verticalLineToRelative(-3f)
                arcToRelative(0.5f, 0.5f, 0f, false, true, 0.5f, -0.5f)
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(3f, 14f)
                reflectiveCurveToRelative(-1f, 0f, -1f, -1f)
                reflectiveCurveToRelative(1f, -4f, 6f, -4f)
                reflectiveCurveToRelative(6f, 3f, 6f, 4f)
                reflectiveCurveToRelative(-1f, 1f, -1f, 1f)
                close()
                moveToRelative(8f, -9f)
                arcToRelative(3f, 3f, 0f, true, true, -6f, 0f)
                arcToRelative(3f, 3f, 0f, false, true, 6f, 0f)
            }
        }.build()
        return _membersSVG!!
    }
private var _tasksSVG: ImageVector? = null
public val tasksSVG: ImageVector
    get() {
        if (_tasksSVG != null) {
            return _tasksSVG!!
        }
        _tasksSVG = ImageVector.Builder(
            name = "ClipboardDocumentCheck",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF0F172A)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.3495f, 3.83619f)
                curveTo(11.2848f, 4.046f, 11.25f, 4.2689f, 11.25f, 4.5f)
                curveTo(11.25f, 4.9142f, 11.5858f, 5.25f, 12f, 5.25f)
                horizontalLineTo(16.5f)
                curveTo(16.9142f, 5.25f, 17.25f, 4.9142f, 17.25f, 4.5f)
                curveTo(17.25f, 4.2689f, 17.2152f, 4.046f, 17.1505f, 3.8362f)
                moveTo(11.3495f, 3.83619f)
                curveTo(11.6328f, 2.9176f, 12.4884f, 2.25f, 13.5f, 2.25f)
                horizontalLineTo(15f)
                curveTo(16.0116f, 2.25f, 16.8672f, 2.9176f, 17.1505f, 3.8362f)
                moveTo(11.3495f, 3.83619f)
                curveTo(10.9739f, 3.8586f, 10.5994f, 3.8853f, 10.2261f, 3.9163f)
                curveTo(9.095f, 4.0102f, 8.25f, 4.9732f, 8.25f, 6.1082f)
                verticalLineTo(8.25f)
                moveTo(17.1505f, 3.83619f)
                curveTo(17.5261f, 3.8586f, 17.9006f, 3.8853f, 18.2739f, 3.9163f)
                curveTo(19.405f, 4.0102f, 20.25f, 4.9732f, 20.25f, 6.1082f)
                verticalLineTo(16.5f)
                curveTo(20.25f, 17.7426f, 19.2426f, 18.75f, 18f, 18.75f)
                horizontalLineTo(15.75f)
                moveTo(8.25f, 8.25f)
                horizontalLineTo(4.875f)
                curveTo(4.2537f, 8.25f, 3.75f, 8.7537f, 3.75f, 9.375f)
                verticalLineTo(20.625f)
                curveTo(3.75f, 21.2463f, 4.2537f, 21.75f, 4.875f, 21.75f)
                horizontalLineTo(14.625f)
                curveTo(15.2463f, 21.75f, 15.75f, 21.2463f, 15.75f, 20.625f)
                verticalLineTo(18.75f)
                moveTo(8.25f, 8.25f)
                horizontalLineTo(14.625f)
                curveTo(15.2463f, 8.25f, 15.75f, 8.7537f, 15.75f, 9.375f)
                verticalLineTo(18.75f)
                moveTo(7.5f, 15.75f)
                lineTo(9f, 17.25f)
                lineTo(12f, 13.5f)
            }
        }.build()
        return _tasksSVG!!
    }
private var _reportsSVG: ImageVector? = null
public val reportsSVG: ImageVector
    get() {
        if (_reportsSVG != null) {
            return _reportsSVG!!
        }
        _reportsSVG = ImageVector.Builder(
            name = "Table_chart_view",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(296f, 880f)
                lineToRelative(-56f, -56f)
                lineToRelative(276f, -277f)
                lineToRelative(140f, 140f)
                lineToRelative(207f, -207f)
                lineToRelative(57f, 57f)
                lineToRelative(-264f, 263f)
                lineToRelative(-140f, -140f)
                close()
                moveToRelative(-136f, -40f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 760f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 120f)
                horizontalLineToRelative(560f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(800f, 200f)
                verticalLineToRelative(168f)
                horizontalLineTo(160f)
                close()
                moveToRelative(0f, -552f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-88f)
                horizontalLineTo(160f)
                close()
                moveToRelative(0f, 0f)
                verticalLineToRelative(-88f)
                close()
            }
        }.build()
        return _reportsSVG!!
    }
private var _requestSVG: ImageVector? = null
public val requestSVG: ImageVector
    get() {
        if (_requestSVG != null) {
            return _requestSVG!!
        }
        _requestSVG = ImageVector.Builder(
            name = "Forward_to_inbox",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(480f, 520f)
                lineTo(160f, 320f)
                verticalLineToRelative(400f)
                horizontalLineToRelative(360f)
                verticalLineToRelative(80f)
                horizontalLineTo(160f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(80f, 720f)
                verticalLineToRelative(-480f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(160f, 160f)
                horizontalLineToRelative(640f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(880f, 240f)
                verticalLineToRelative(280f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(-200f)
                close()
                moveToRelative(0f, -80f)
                lineToRelative(320f, -200f)
                horizontalLineTo(160f)
                close()
                moveTo(760f, 920f)
                lineToRelative(-56f, -56f)
                lineToRelative(63f, -64f)
                horizontalLineTo(600f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(167f)
                lineToRelative(-64f, -64f)
                lineToRelative(57f, -56f)
                lineToRelative(160f, 160f)
                close()
                moveTo(160f, 320f)
                verticalLineToRelative(440f)
                verticalLineToRelative(-240f)
                verticalLineToRelative(3f)
                verticalLineToRelative(-283f)
                close()
            }
        }.build()
        return _requestSVG!!
    }
private var _logoutSVG: ImageVector? = null
public val logoutSVG: ImageVector
    get() {
        if (_logoutSVG != null) {
            return _logoutSVG!!
        }
        _logoutSVG = ImageVector.Builder(
            name = "CrossCircled",
            defaultWidth = 25.dp,
            defaultHeight = 25.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0.877075f, 7.49988f)
                curveTo(0.8771f, 3.8422f, 3.8422f, 0.877f, 7.4999f, 0.877f)
                curveTo(11.1576f, 0.877f, 14.1227f, 3.8422f, 14.1227f, 7.4999f)
                curveTo(14.1227f, 11.1575f, 11.1576f, 14.1227f, 7.4999f, 14.1227f)
                curveTo(3.8422f, 14.1227f, 0.8771f, 11.1575f, 0.8771f, 7.4999f)
                close()
                moveTo(7.49991f, 1.82704f)
                curveTo(4.3669f, 1.827f, 1.8271f, 4.3669f, 1.8271f, 7.4999f)
                curveTo(1.8271f, 10.6329f, 4.3669f, 13.1727f, 7.4999f, 13.1727f)
                curveTo(10.6329f, 13.1727f, 13.1727f, 10.6329f, 13.1727f, 7.4999f)
                curveTo(13.1727f, 4.3669f, 10.6329f, 1.827f, 7.4999f, 1.827f)
                close()
                moveTo(9.85358f, 5.14644f)
                curveTo(10.0488f, 5.3417f, 10.0488f, 5.6583f, 9.8536f, 5.8536f)
                lineTo(8.20713f, 7.49999f)
                lineTo(9.85358f, 9.14644f)
                curveTo(10.0488f, 9.3417f, 10.0488f, 9.6583f, 9.8536f, 9.8536f)
                curveTo(9.6583f, 10.0488f, 9.3417f, 10.0488f, 9.1465f, 9.8536f)
                lineTo(7.50002f, 8.2071f)
                lineTo(5.85358f, 9.85355f)
                curveTo(5.6583f, 10.0488f, 5.3417f, 10.0488f, 5.1465f, 9.8536f)
                curveTo(4.9512f, 9.6583f, 4.9512f, 9.3417f, 5.1465f, 9.1464f)
                lineTo(6.79292f, 7.49999f)
                lineTo(5.14647f, 5.85355f)
                curveTo(4.9512f, 5.6583f, 4.9512f, 5.3417f, 5.1465f, 5.1464f)
                curveTo(5.3417f, 4.9512f, 5.6583f, 4.9512f, 5.8536f, 5.1464f)
                lineTo(7.50002f, 6.79289f)
                lineTo(9.14647f, 5.14644f)
                curveTo(9.3417f, 4.9512f, 9.6583f, 4.9512f, 9.8536f, 5.1464f)
                close()
            }
        }.build()
        return _logoutSVG!!
    }

private var _keyboardSVG: ImageVector? = null
public val keyboardSVG: ImageVector
    get() {
        if (_keyboardSVG != null) {
            return _keyboardSVG!!
        }
        _keyboardSVG = ImageVector.Builder(
            name = "Keyboard_alt",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(120f, 840f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(40f, 760f)
                verticalLineToRelative(-520f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(120f, 160f)
                horizontalLineToRelative(720f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(920f, 240f)
                verticalLineToRelative(520f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(840f, 840f)
                close()
                moveToRelative(0f, -80f)
                horizontalLineToRelative(720f)
                verticalLineToRelative(-520f)
                horizontalLineTo(120f)
                close()
                moveToRelative(200f, -80f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(-40f)
                horizontalLineTo(320f)
                close()
                moveTo(200f, 560f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveTo(200f, 400f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                close()
                moveTo(120f, 760f)
                verticalLineToRelative(-520f)
                close()
            }
        }.build()
        return _keyboardSVG!!
    }