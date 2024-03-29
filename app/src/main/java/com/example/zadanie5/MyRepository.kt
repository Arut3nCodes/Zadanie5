package com.example.zadanie5

import android.content.Context
import androidx.lifecycle.LiveData

class MyRepository(context: Context) {
    private var dataList: MutableList<DBItem>? = null
    private var myDao: MyDao
    private var db: MyDB

    companion object{
        private var R_INSTANCE: MyRepository? = null

        fun getinstance(context: Context): MyRepository {
            if (R_INSTANCE == null)
                R_INSTANCE = MyRepository(context)
            return R_INSTANCE as MyRepository
        }
    }

    init{
        db = MyDB.getDatabase(context)!!
        myDao = db.myDao()!!
    }

    fun getData() : MutableList<DBItem>? {
        dataList = myDao.getAllData()!!
        return dataList
    }

    fun addItem(item: DBItem?) : Boolean {
        if(myDao.insert(item) >= 0) return true
        return false
    }

    fun deleteItem(item: DBItem?) : Boolean {
        if(myDao.delete(item) > 0) return true
        return false
    }

    fun updateItem(item: DBItem?) : Boolean{
        if(myDao.update(item) > 0) return true
        return false
    }

    fun getItemOnIndex(index : Int) : DBItem?{
        return myDao.getAllData()!![index]
    }
}