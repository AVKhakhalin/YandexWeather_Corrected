package ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels

import ru.geekbrains.lessions2345.yandexweather_corrected.domain.facade.MainChooserGetter

sealed class UpdateState() {
    object Loading: UpdateState()
    data class Success(val mainChooserGetter: MainChooserGetter): UpdateState()
    data class Error(val error: Throwable?): UpdateState()
    data class ListCities(val mainChooserGetter: MainChooserGetter): UpdateState()
}