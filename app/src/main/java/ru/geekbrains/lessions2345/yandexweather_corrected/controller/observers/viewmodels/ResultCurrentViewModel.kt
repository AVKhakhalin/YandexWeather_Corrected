package ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather_corrected.controller.ConstantsController
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather_corrected.repository.facadeuser.RepositoryWeatherImpl

class ResultCurrentViewModel(
    private val liveDataToObserve: MutableLiveData<UpdateState> = MutableLiveData()
): ViewModel() {
    private var repositoryWeatherImpl: RepositoryWeatherImpl? = null

    fun getLiveData() = liveDataToObserve

    fun getDataFromRemoteSource(mainChooserSetter: MainChooserSetter, mainChooserGetter: MainChooserGetter) {
        // Отправка запроса на получение погодных данных с сервера Yandex
        repositoryWeatherImpl = RepositoryWeatherImpl(mainChooserSetter)
        repositoryWeatherImpl?.getWeatherFromRemoteSource(mainChooserGetter.getCurrentKnownCity()!!.lat, mainChooserGetter.getCurrentKnownCity()!!.lon, ConstantsController.ANSWER_LANGUAGE)
        // Отслеживание состояния загрузки погодных данных
        with(liveDataToObserve) {
            // Отправка сообщения О ПРОЦЕССЕ ЗАГРУЗКИ
            postValue(UpdateState.Loading)
            Thread {
                Thread.sleep(1000)
                if ((mainChooserGetter.getDataWeather()?.error == null) && (mainChooserGetter.getDataWeather()?.temperature != null)) {
                    // УСПЕШНАЯ ПЕРЕДАЧА погодных данных в основном потоке через postValue (postValue два раза подряд использовать нельзя)
                    postValue(UpdateState.Success(mainChooserGetter))
                } else {
                    // Передача СООБЩЕНИЯ ОБ ОШИБКЕ при получении погодных данных с сервера Yandex
                    postValue(UpdateState.Error(mainChooserGetter.getDataWeather()?.error))
                }
            }.start()
        }
    }
}