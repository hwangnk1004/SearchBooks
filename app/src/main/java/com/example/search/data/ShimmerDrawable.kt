package com.example.search.data

import androidx.core.content.ContextCompat
import com.example.search.ApplicationClass
import com.example.search.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

object ShimmerDrawable {

    fun makeShimmerDrawable(): ShimmerDrawable {
        val shimmer = Shimmer.ColorHighlightBuilder()
            .setBaseColor(ContextCompat.getColor(ApplicationClass.context, R.color.title_color))
            .setBaseAlpha(0.7f)
            .setHighlightAlpha(0.7f)
            .setHighlightColor(ContextCompat.getColor(ApplicationClass.context, R.color.white))
            .setDuration(1800)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

        val shimmerDrawable = ShimmerDrawable()
        shimmerDrawable.setShimmer(shimmer)
        return shimmerDrawable
    }

}