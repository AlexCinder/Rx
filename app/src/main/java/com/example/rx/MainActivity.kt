package com.example.rx

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.rx.DetailActivity.Companion.EXTRA_FROM_SYMBOL
import com.example.rx.adapters.CoinAdapter
import com.example.rx.db.entity.ConvertCryptoToCurrency

import com.example.rx.viewmodel.CoinViewModel
import io.reactivex.Observable

/**
 * API key -> ef62e3464e9735bbf341f66f68553101f6e111bcf523600cd62dc59473e08688
 */
const val TAG = "TAG"

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CoinAdapter(object : CoinAdapter.CoinListener {
            override fun onCoinListener(coin: ConvertCryptoToCurrency) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(EXTRA_FROM_SYMBOL,coin.fromSymbol)
                startActivity(intent)
            }

        })
        recyclerView = findViewById(R.id.rvCoinList)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.priceList.observe(
            this, {
                adapter.list = it
            })
    }

}

