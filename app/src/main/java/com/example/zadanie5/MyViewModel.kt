package com.example.zadanie5

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(context: Context) : ViewModel() {

        private var myRepository = MyRepository(context)
        private val _itemsLiveData = MutableLiveData<List<DBItem>>()

        fun getAllItems() : MutableLiveData<List<DBItem>> {
            viewModelScope.launch(Dispatchers.IO) {
                val items = myRepository.getData()
                _itemsLiveData.postValue(items!!)
            }
            return _itemsLiveData
        }

        fun insertItem(item: DBItem?) {
            viewModelScope.launch(Dispatchers.IO) {
                myRepository.addItem(item)
                getAllItems()
            }
        }

        fun deleteItem(item: DBItem?) {
            viewModelScope.launch(Dispatchers.IO) {
                myRepository.deleteItem(item)
                getAllItems()
            }
        }

        fun updateItem(item: DBItem?) {
            viewModelScope.launch(Dispatchers.IO) {
                myRepository.updateItem(item)
            }
        }

        fun getItemOnIndex(index: Int) : DBItem? {
            return myRepository.getItemOnIndex(index)
        }

    companion object {
        val Factory: ViewModelProvider.Factory = object :
            ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST") override fun <T : ViewModel> create(modelClass: Class<T>,
                                                                            extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MyViewModel(application.applicationContext) as T
            }
        }
    }

}