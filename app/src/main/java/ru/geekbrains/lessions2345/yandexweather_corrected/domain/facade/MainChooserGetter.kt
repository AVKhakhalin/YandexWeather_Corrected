package ru.geekbrains.lessions2345.yandexweather_corrected.domain.facade

import ru.geekbrains.lessions2345.yandexweather_corrected.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.City
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.DataWeather

class MainChooserGetter(mainChooser: MainChooser) {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var mainChooser: MainChooser = mainChooser
    //endregion

    // Получение данных о погоде
    fun getDataWeather(): DataWeather? = mainChooser.getDataWeather()

    // Получение количества известных мест (городов)
    fun getNumberKnownCites(): Int = mainChooser.getNumberKnownCities()

    //region МЕТОДЫ ПОЛУЧЕНИЯ СПИСКА ИЗВЕСТНЫХ МЕСТ (ГОРОДОВ)
    fun getKnownCites(filterCity: String, filterCountry: String): MutableList<City>? =
        mainChooser.getKnownCities(filterCity, filterCountry)

    fun getKnownCites(): MutableList<City>? = mainChooser.getKnownCities()
    //endregion

    // Получение данных об известном городе, по которому последний раз запрошены погодные данные или который выбран в списке известных городов
    fun getCurrentKnownCity(): City? = mainChooser.getCurrentKnownCity()

    // Получение позиции известного города, по которому последний раз запрошены погодные данные
    fun getPositionCurrentKnownCity(): Int = mainChooser.getPositionCurrentKnownCity()

    // Получение фильтра выбора места (города) по-умолчанию
    fun getDefaultFilterCity(): String = mainChooser.getDefaultFilterCity()

    // Получение фильтра выбора страны по-умолчанию
    fun getDefaultFilterCountry(): String = mainChooser.getDefaultFilterCountry()
}