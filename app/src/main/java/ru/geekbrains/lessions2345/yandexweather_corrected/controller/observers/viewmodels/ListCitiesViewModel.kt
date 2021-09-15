package ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.facade.MainChooserGetter

class ListCitiesViewModel(
    private val liveDataToObserve: MutableLiveData<UpdateState> = MutableLiveData()
) : ViewModel() {
    private var mainChooserGetter: MainChooserGetter? = null

    fun setMainChooserGetter(mainChooserGetter: MainChooserGetter) {
        this.mainChooserGetter = mainChooserGetter
    }

    fun getLiveData() = liveDataToObserve

    fun getListCities() {
        if (mainChooserGetter != null) {
            // Передача данных в основном потоке postValue (postValue два раза подряд использовать нельзя)
            liveDataToObserve.postValue(UpdateState.ListCities(mainChooserGetter as MainChooserGetter))
        }
    }
}