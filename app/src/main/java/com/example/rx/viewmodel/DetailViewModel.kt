package com.example.rx.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.rx.db.CryptoDatabase
import com.example.rx.db.entity.ConvertCryptoToCurrency

class DetailViewModel(application: Application) :AndroidViewModel(application){
    private val db = CryptoDatabase.getInstance(application)

    fun getDetailedInfo(fSym: String): LiveData<ConvertCryptoToCurrency> {
        return db.dao().getDetailedInfo(fSym)
    }
}