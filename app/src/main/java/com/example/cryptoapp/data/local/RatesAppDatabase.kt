package com.example.cryptoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [com.example.cryptoapp.data.entities.rates.RateData::class], version = 2, exportSchema = false)
abstract class RatesAppDatabase : RoomDatabase() {

    abstract fun ratesDAO():RatesDAO

    companion object{
        @Volatile private var INSTANCE: RatesAppDatabase?= null

        fun getDatabase(context: Context) : RatesAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also{ INSTANCE = it}
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, RatesAppDatabase::class.java, "RatesDB1")
                .fallbackToDestructiveMigration()
                .build()
    }

}