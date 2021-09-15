package ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.domain

import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.City

interface ObserverDomain {
    fun updateFilterCountry(filterCountry: String)
    fun updateFilterCity(filterCity: String)
    fun updateCity(city: City)
    fun updatePositionCurrentKnownCity(positionCurrentKnownCity: Int)
}