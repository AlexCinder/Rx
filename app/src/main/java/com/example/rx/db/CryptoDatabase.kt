package com.example.rx.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rx.db.dao.Dao
import com.example.rx.db.entity.ConvertCryptoToCurrency

@Database(entities = [ConvertCryptoToCurrency::class], version = 1, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var db: CryptoDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): CryptoDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        CryptoDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun dao(): Dao
}