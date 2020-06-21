package com.example.testkvartira.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.testkvartira.R
import com.example.testkvartira.ui.flat.FlatImageAdapter
import com.example.testkvartira.ui.flat.FlatViewModel
import kotlinx.android.synthetic.main.activity_flat.*

class FlatActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private val viewModel by viewModels<FlatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flat)
        if (intent == null && !intent.hasExtra(FLAT_ID)) {
            finish()
            return
        }

        val flatId = intent.getIntExtra(FLAT_ID, 0)
        viewModel.loadFlat(flatId)

        viewModel.flat.observe(this, Observer {
            priceTV.text = it.prices?.day.toString()
            addressTV.text = it.address
            typeTV.text = it.buildingType
            descriptionTV.text = it.description
            infoCV.visibility = View.VISIBLE
            flatPBAR.visibility = View.INVISIBLE
            flat_images.adapter = FlatImageAdapter(this,
                it.photos?.map { photo -> photo.url } as List<String>)
        })
    }

    companion object {
        const val FLAT_ID = "flatId"

        fun newIntent(context: Context, flatId: Int): Intent {
            val intent = Intent(context, FlatActivity::class.java)
            intent.putExtra(FLAT_ID, flatId)
            return intent
        }
    }
}