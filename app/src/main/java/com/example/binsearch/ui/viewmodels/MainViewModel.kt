package com.example.binsearch.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.binsearch.data.models.request.BIN
import com.example.binsearch.data.repository.BINRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val binRepository: BINRepository) : ViewModel() {

    var binList : MutableLiveData<List<BIN>> = MutableLiveData()

    var search by mutableStateOf("")
    private set

    var isError by mutableStateOf(false)
    private set
    var errorText by mutableStateOf("")
    private set

    fun updateSearch(newSearch : String){
        search = newSearch
    }

    fun getBin(digits: String) : Boolean {

        resetErrors()

        if (digits.isEmpty()){
            isError = true
            errorText = "Вы ничего не ввели"
            return false
        }

        digits.forEach { char ->
            if (!char.isDigit()){
                isError = true
                errorText = "Вы ввели не числа"
                return false
            }
        }

        viewModelScope.launch(Dispatchers.IO) {

            val response = binRepository.getBIN(digits)
            if (response.isSuccessful){
                response.body()?.let { binRepository.addBIN(it) }
            }
            else {
                isError = true
                errorText = "Ничего не найдено"
            }
        }
        return true
    }

    private fun resetErrors(){
        isError = false
    }

    init {
        viewModelScope.launch {
            binRepository.getBinList().collect{
                binList.value = it
            }
        }
    }

}