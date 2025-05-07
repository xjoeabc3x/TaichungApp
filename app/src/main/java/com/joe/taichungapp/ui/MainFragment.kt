package com.joe.taichungapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.joe.taichungapp.MainActivity
import com.joe.taichungapp.R
import com.joe.taichungapp.adapter.AttractionsAdapter
import com.joe.taichungapp.adapter.FlowerAdapter
import com.joe.taichungapp.viewmodel.MainViewModel
import java.util.Locale

class MainFragment : Fragment() {

    private val tag = "MainFragment"
    private val viewModel: MainViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化 RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 初始化 TabLayout
        tabLayout = view.findViewById(R.id.menuTab)
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.flower_list_title_flower)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.flower_list_title_sea)))

        viewModel.flowers.observe(viewLifecycleOwner) { flowers ->
            recyclerView.adapter = FlowerAdapter(flowers) { flowerInfo ->
                // 使用 SafeArgs 傳遞 FlowerInfo
                val action = MainFragmentDirections.actionMainFragmentToInfoDetailFragment(flowerInfo, null)
                findNavController().navigate(action)
            }
        }
        viewModel.fetchFlowers()

        // 設置 TabLayout 切換事件
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    getString(R.string.flower_list_title_flower) -> {
                        viewModel.flowers.observe(viewLifecycleOwner) { flowers ->
                            recyclerView.adapter = FlowerAdapter(flowers) { flowerInfo ->
                                // 使用 SafeArgs 傳遞 FlowerInfo
                                val action = MainFragmentDirections.actionMainFragmentToInfoDetailFragment(flowerInfo, null)
                                findNavController().navigate(action)
                            }
                        }
                        viewModel.fetchFlowers()
                    }
                    getString(R.string.flower_list_title_sea) -> {
                        viewModel.attractions.observe(viewLifecycleOwner) { attractions ->
                            recyclerView.adapter = AttractionsAdapter(attractions) { attractionsInfo ->
                                // 使用 SafeArgs 傳遞 AttractionsInfo
                                val action = MainFragmentDirections.actionMainFragmentToInfoDetailFragment(null, attractionsInfo)
                                findNavController().navigate(action)
                            }
                        }
                        viewModel.fetchAttractions()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // 設置語言切換 Spinner
        val spinner = view.findViewById<Spinner>(R.id.languageSpinner)
        spinner.setSelection(if (Locale.getDefault().language == "zh") 1 else 0)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLanguage = if (position == 0) "en" else "zh-rTW"
                if (Locale.getDefault().language != selectedLanguage) {
//                    setLanguage(selectedLanguage)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        (activity as MainActivity).recreate()// reload Activity to refresh language
    }
}