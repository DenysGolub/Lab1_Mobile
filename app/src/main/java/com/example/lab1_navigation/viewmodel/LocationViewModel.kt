package com.example.lab1_navigation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LocationData(
    val latitude: Double,
    val longitude: Double
)

class LocationViewModel : ViewModel() {

    private val _location = MutableLiveData<LocationData>()
    val location: LiveData<LocationData> = _location

    private val _status = MutableLiveData<String>("Карта ще не завантажена")
    val status: LiveData<String> = _status

    fun fetchLocation() {
        viewModelScope.launch {
            _status.postValue("Отримую координати...")
            delay(1000)
            // Фейкові координати Києва
            val fakeLocation = LocationData(50.4501, 30.5234)
            _location.postValue(fakeLocation)
            _status.postValue("Місце знайдено: Київ, Україна")
        }
    }

    fun clearLocation() {
        _location.postValue(null)
        _status.postValue("Карта прихована")
    }
}
