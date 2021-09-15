package ru.geekbrains.lessions2345.yandexweather_corrected.repository.facadeuser

import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.DataWeather

interface RepositoryWeather {
    fun getWeatherFromRemoteSource(lat: Double, lon: Double, lang: String)
    fun getWeatherFromLocalSource() : DataWeather
}