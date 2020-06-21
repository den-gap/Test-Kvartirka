package com.example.testkvartira.ui.flat

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.testkvartira.R
import com.squareup.picasso.Picasso

class FlatImageAdapter(private val context: Context, private val imageUrls: List<String>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val mImageView = ImageView(context)
        Picasso.get()
            .load(imageUrls[position])
            .placeholder(R.drawable.ic_baseline_house_24)
            .fit()
            .centerCrop()
            .into(mImageView)
        container.addView(mImageView)
        return mImageView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int = imageUrls.size
}