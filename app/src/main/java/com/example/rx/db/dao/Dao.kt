package com.example.rx.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rx.db.entity.ConvertCryptoToCurrency

@Dao
interface Dao {
    @Query("SELECT * FROM crypto_database ORDER BY lastUpdate DESC")
    fun getFullInfo(): LiveData<List<ConvertCryptoToCurrency>>

    @Query("SELECT * FROM crypto_database WHERE fromSymbol == :fSym LIMIT 1")
    fun getDetailedInfo(fSym: String): LiveData<ConvertCryptoToCurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(list: List<ConvertCryptoToCurrency>)


}