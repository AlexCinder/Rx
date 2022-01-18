package com.example.rx.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rx.CryptoApp
import com.example.rx.pojo.RAWJson
import com.example.rx.TAG
import com.example.rx.db.CryptoDatabase
import com.example.rx.db.entity.ConvertCryptoToCurrency
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val applicationFromActivity = application as CryptoApp
    private val db = CryptoDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    val priceList = db.dao().getFullInfo()
    init{
        loadData()
    }



    private fun loadData() {
        val disposable = applicationFromActivity.apiService.getTopCoinInfo()
            .map {
                it.list.joinToString(",") { coin -> coin.coinInfo.name }
            }.flatMap {
                applicationFromActivity.apiService.getFullPrice(fSym = it)
            }
            .map { getPriceListFromJson(it) }
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.dao().insertPriceList(it)
                Log.d(TAG, "onSuccess: $it")
            }, {

                Log.d(TAG, "onError: ${it.localizedMessage} ")
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromJson(rawJson: RAWJson): List<ConvertCryptoToCurrency> {
        val listOfCoins = ArrayList<ConvertCryptoToCurrency>()
        val json = rawJson.rawJson
        val keySet = json.keySet()
        for (key in keySet) {
            val cryptoCurrency = json.getAsJsonObject(key)
            val currencyKeySet = cryptoCurrency.keySet()
            for (currencyKey in currencyKeySet) {
                val coinInfo = Gson()
                    .fromJson(
                        cryptoCurrency.getAsJsonObject(currencyKey),
                        ConvertCryptoToCurrency::class.java
                    )
                listOfCoins.add(coinInfo)
            }
        }
        return listOfCoins
    }

    override fun onCleared() {

        compositeDisposable.dispose()
        super.onCleared()
    }

}