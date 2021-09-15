package ru.geekbrains.lessions2345.yandexweather_corrected.ui.fragments.content.domain

import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.City

interface OnItemViewClickListener {
    fun onItemClick(city: City)
}