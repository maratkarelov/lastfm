package com.example.lastfm.core.ui

import android.graphics.*
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.lastfm.R
import com.google.android.material.snackbar.Snackbar

fun View.setOnSingleClickListener(l: View.OnClickListener) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}

fun View.showSnackbar(msgId: Int, length: Int) {
    showSnackbar(context.getString(msgId), length)
}

fun View.showSnackbar(msg: String, length: Int) {
    showSnackbar(msg, length, null, {})
}

fun View.showSnackbar(
    msgId: Int,
    length: Int,
    actionMessageId: Int,
    action: (View) -> Unit
) {
    showSnackbar(context.getString(msgId), length, context.getString(actionMessageId), action)
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    }
}

fun <T> ImageView.loadCircularImage(
    model: T,
    borderSize: Float = 3F,
    borderColor: Int = resources.getColor(R.color.colorPrimaryDark)
) {
    Glide.with(context)
        .asBitmap()
        .load(model)
        .apply(RequestOptions.circleCropTransform())
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                setImageDrawable(
                    resource?.run {
                        RoundedBitmapDrawableFactory.create(
                            resources,
                            if (borderSize > 0) {
                                createBitmapWithBorder(
                                    resources.displayMetrics.scaledDensity * borderSize,
                                    borderColor
                                )
                            } else {
                                this
                            }
                        ).apply {
                            isCircular = true
                        }
                    }
                )
            }
        })
}

fun Bitmap.createBitmapWithBorder(
    borderSize: Float,
    borderColor: Int = Color.WHITE
): Bitmap {
    val borderOffset = (borderSize * 2).toInt()
    val halfWidth = width / 2
    val halfHeight = height / 2
    val circleRadius = Math.min(halfWidth, halfHeight).toFloat()
    val newBitmap = Bitmap.createBitmap(
        width + borderOffset,
        height + borderOffset,
        Bitmap.Config.ARGB_8888
    )

    // Center coordinates of the image
    val centerX = halfWidth + borderSize
    val centerY = halfHeight + borderSize

    val paint = Paint()
    val canvas = Canvas(newBitmap).apply {
        // Set transparent initial area
        drawARGB(0, 0, 0, 0)
    }

    // Draw the transparent initial area
    paint.isAntiAlias = true
    paint.style = Paint.Style.FILL
    canvas.drawCircle(centerX, centerY, circleRadius, paint)

    // Draw the image
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, borderSize, borderSize, paint)

    val paint1 = Paint()
    paint1.isAntiAlias = true
    paint1.xfermode = null
    paint1.style = Paint.Style.STROKE
    paint1.color = borderColor
    paint1.strokeWidth = borderSize / 3
    canvas.drawCircle(centerX, centerY, circleRadius, paint1)
    val paintCentral = Paint()
    paintCentral.isAntiAlias = true
    paintCentral.style = Paint.Style.STROKE
    paintCentral.color = Color.WHITE
    paintCentral.strokeWidth = borderSize * 2 / 3
    canvas.drawCircle(centerX, centerY, circleRadius + borderSize / 3, paintCentral)
    val paint2 = Paint()
    paint2.isAntiAlias = true
    paint2.xfermode = null
    paint2.style = Paint.Style.STROKE
    paint2.color = borderColor
    paint2.strokeWidth = borderSize
    canvas.drawCircle(centerX, centerY, circleRadius + borderSize, paint2)
    return newBitmap
}

