package com.example.rx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rx.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail.*

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var ivLogoCoin : ImageView
    private lateinit var tvFromSymbol: TextView
    private lateinit var tvSlash: TextView
    private lateinit var tvToSymbol: TextView
    private lateinit var tvPriceLabel: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvMinPriceLabel: TextView
    private lateinit var tvMaxPriceLabel: TextView
    private lateinit var tvMaxPrice: TextView
    private lateinit var tvLastMarketLabel: TextView
    private lateinit var tvLastMarket: TextView
    private lateinit var tvLastUpdateLabel: TextView
    private lateinit var tvLastUpdate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        init()
        if(!intent.hasExtra(EXTRA_FROM_SYMBOL)){
            this.finish()
            return
        }
        val extra = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if(extra != null) {
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.getDetailedInfo(extra).observe(this, Observer {
                tvPrice.text = it.price.toString()
                tvMinPrice.text = it.lowDay.toString()
                tvMaxPrice.text = it.highDay.toString()
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.getFormattedTime()
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Glide
                    .with(this)
                    .load(it.getUrl())
                    .into(ivLogoCoin)


            })
        }
    }


    companion object{
        const val EXTRA_FROM_SYMBOL = "fSym"
    }
    private fun init(){
        ivLogoCoin = findViewById(R.id.ivLogoCoin)
        tvFromSymbol = findViewById(R.id.tvFromSymbol)
        tvSlash = findViewById(R.id.tvSlash)
        tvToSymbol = findViewById(R.id.tvToSymbol)
        tvPriceLabel = findViewById(R.id.tvPriceLabel)
        tvPrice = findViewById(R.id.tvPrice)
        tvMinPriceLabel = findViewById(R.id.tvMinPriceLabel)
        tvMaxPriceLabel = findViewById(R.id.tvMaxPriceLabel)
        tvMaxPrice = findViewById(R.id.tvMaxPrice)
        tvLastMarketLabel = findViewById(R.id.tvLastMarketLabel)
        tvLastMarket = findViewById(R.id.tvLastMarket)
        tvLastUpdateLabel = findViewById(R.id.tvLastUpdateLabel)
        tvLastUpdate = findViewById(R.id.tvLastUpdate)

    }
}