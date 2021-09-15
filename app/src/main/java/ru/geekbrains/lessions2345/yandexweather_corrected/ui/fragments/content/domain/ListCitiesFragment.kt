package ru.geekbrains.lessions2345.yandexweather_corrected.ui.fragments.content.domain

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.lessions2345.yandexweather_corrected.R
import ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.domain.ListCitiesPublisherDomain
import ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels.ListCitiesViewModel
import ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels.ListCitiesViewModelSetter
import ru.geekbrains.lessions2345.yandexweather_corrected.controller.observers.viewmodels.UpdateState
import ru.geekbrains.lessions2345.yandexweather_corrected.databinding.FragmentListCitiesBinding
import ru.geekbrains.lessions2345.yandexweather_corrected.domain.data.City
import ru.geekbrains.lessions2345.yandexweather_corrected.ui.ConstantsUi
import ru.geekbrains.lessions2345.yandexweather_corrected.ui.activities.MainActivity
import ru.geekbrains.lessions2345.yandexweather_corrected.ui.fragments.content.result.ResultCurrentFragment

class ListCitiesFragment(isDataSetRusInitial: Boolean): Fragment(), OnItemViewClickListener {
    private var _binding: FragmentListCitiesBinding? = null
    private val binding: FragmentListCitiesBinding
        get() {
            return _binding!!
        }
    private var isDataSetRus: Boolean = isDataSetRusInitial
    private var adapter = ListCitiesFragmentAdapter()


    // Ссылка на ResultCurrentViewModel
    private lateinit var listCitiesViewModel: ListCitiesViewModel

    companion object {
        fun newInstance(isDataSetRusInitial: Boolean) = ListCitiesFragment(isDataSetRusInitial)
    }

    // Создание наблюдателя в domain
    var listCitiesPublisherDomain : ListCitiesPublisherDomain = ListCitiesPublisherDomain()
    //endregion

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Создание viewModel
        listCitiesViewModel = ViewModelProvider(this).get(ListCitiesViewModel::class.java)
        // Задание наблюдателя для данного фрагмента (viewModel)
        (context as ListCitiesViewModelSetter).setListCitiesViewModel(listCitiesViewModel)
        // Получение наблюдателя для domain
        listCitiesPublisherDomain = (context as MainActivity).getListSitiesPublisherDomain()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Начальная установка вида кнопки переключения фильтра стран
        if (isDataSetRus) {
            binding.fragmentListCitiesFAB.setImageResource(R.drawable.ic_earth)
        } else {
            binding.fragmentListCitiesFAB.setImageResource(R.drawable.ic_russia)
        }

        binding.fragmentListCitiesRecyclerView.adapter = adapter
        adapter.setOnItemViewClickListener(this)
        binding.fragmentListCitiesFAB.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                isDataSetRus = !isDataSetRus
                if(!isDataSetRus){
                    binding.fragmentListCitiesFAB.setImageResource(R.drawable.ic_russia)
                    with(listCitiesPublisherDomain) {
                        notifyDefaultFilterCountry(ConstantsUi.FILTER_NOT_RUSSIA)
                        notifyDefaultFilterCity(ConstantsUi.DEFAULT_FILTER_CITY)
                    }
                    listCitiesViewModel.getListCities()
                }else {
                    binding.fragmentListCitiesFAB.setImageResource(R.drawable.ic_earth)
                    with(listCitiesPublisherDomain) {
                        notifyDefaultFilterCountry(ConstantsUi.FILTER_RUSSIA)
                        notifyDefaultFilterCity(ConstantsUi.DEFAULT_FILTER_CITY)
                    }
                    listCitiesViewModel.getListCities()
                }
            }
        })
        listCitiesViewModel = ViewModelProvider(this).get(ListCitiesViewModel::class.java)
        listCitiesViewModel.getLiveData()
            .observe(viewLifecycleOwner, Observer<UpdateState> { updateState: UpdateState ->
                renderData(updateState)
            })
        listCitiesViewModel.getListCities()
    }

    private fun renderData(updateState: UpdateState) {
        when (updateState) {
            is UpdateState.ListCities -> {
                val weather = updateState.mainChooserGetter.getKnownCites()
                if (weather != null) {
                    adapter.setWeather(weather)
                }
            }
            //else -> //TODO: Добавить случай с неуспешной загрузкой списка
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(city: City) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_result_weather_container, ResultCurrentFragment.newInstance(city))
            .commit()
    }
}