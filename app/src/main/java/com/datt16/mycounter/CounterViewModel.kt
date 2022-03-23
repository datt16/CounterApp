package com.datt16.mycounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count
    var counterName = "New Counter"

    init {
        resetCounter()
        counterName = "NewCounter"
    }

    fun increaseCount() {
        _count.value = count.value!! + 1
    }

    fun decreaseCount() {
        _count.value = if (count.value != 0) {
            count.value!! - 1
        } else {
            count.value
        }
    }

    private fun resetCounter() {
        _count.value = 0
    }

}