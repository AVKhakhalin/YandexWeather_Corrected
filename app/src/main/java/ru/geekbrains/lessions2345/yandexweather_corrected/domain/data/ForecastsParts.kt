package ru.geekbrains.lessions2345.yandexweather_corrected.domain.data

class ForecastsParts (
    partName: String,   // part_name   Название времени суток. Возможные значения:
    // night — ночь.
    // morning — утро.
    // day — день.
    // evening — вечер.	Строка.
    tempMin: Float,     // temp_min    Минимальная температура для времени суток (°C).	Число
    tempMax: Float,     // temp_max    Максимальная температура для времени суток (°C).	Число
    tempAvg: Float,     // temp_avg    Cредняя температура для времени суток (°C).	Число
    feelsLike: Float,   // feels_like  Ощущаемая температура (°C).	Число
    iconCode: String,   // icon        Код иконки погоды. Иконка доступна по адресу https://yastatic.net/weather/i/icons/funky/dark/<значение из поля icon>.svg.	Строка
    conditionCode: String, // condition   Код расшифровки погодного описания. Возможные значения:
    //   clear — ясно.
    //   partly-cloudy — малооблачно.
    //   cloudy — облачно с прояснениями.
    //   overcast — пасмурно.
    //   drizzle — морось.
    //   light-rain — небольшой дождь.
    //   rain — дождь.
    //   moderate-rain — умеренно сильный дождь.
    //   heavy-rain — сильный дождь.
    //   continuous-heavy-rain — длительный сильный дождь.
    //   showers — ливень.
    //   wet-snow — дождь со снегом.
    //   light-snow — небольшой снег.
    //   snow — снег.
    //   snow-showers — снегопад.
    //   hail — град.
    //   thunderstorm — гроза.
    //   thunderstorm-with-rain — дождь с грозой.
    //   thunderstorm-with-hail — гроза с градом.	Строка
    daytime: String,    // daytime	Светлое или темное время суток. Возможные значения:
    //   «d» — светлое время суток.
    //   «n» — темное время суток.	Строка
    polar: Boolean,     // polar	Признак того, что время суток, указанное в поле daytime, является полярным.	Логический
    wind_speed: Float,  // wind_speed	Скорость ветра (в м/с).	Число
    windGust: Float,    // wind_gust	Скорость порывов ветра (в м/с).	Число
    windDirection: String,  // wind_dir	Направление ветра. Возможные значения:
    //   «nw» — северо-западное.
    //   «n» — северное.
    //   «ne» — северо-восточное.
    //   «e» — восточное.
    //   «se» — юго-восточное.
    //   «s» — южное.
    //   «sw» — юго-западное.
    //   «w» — западное.
    //   «с» — штиль.	Строка
    mmPressure: Float,   // pressure_mm	Давление (в мм рт. ст.).	Число
    paPressure: Float,   // pressure_pa	Давление (в гектопаскалях).	Число
    humidity: Float,     // humidity	Влажность воздуха (в процентах).	Число
    mmPrec: Float,       // prec_mm	Прогнозируемое количество осадков (в мм).	Число
    prec_period: Float,  // prec_period	Прогнозируемый период осадков (в минутах).	Число
    prec_prob: Float,    // prec_prob	Вероятность выпадения осадков.	Число
)