package com.joe.taichungapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
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
import com.joe.taichungapp.util.LocaleHelper
import com.joe.taichungapp.viewmodel.MainViewModel
import java.util.Locale

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var languageButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FlowerApp", "onViewCreated")
        val currentLang = Locale.getDefault().language
        Log.d("FlowerApp", "currentLang :$currentLang")
        languageButton = view.findViewById(R.id.languageButton)
        languageButton.text = getString(R.string.selected_language)

        languageButton.setOnClickListener {
            val newLang = if (currentLang == "zh") "en" else "zh"
            setLanguage(newLang)
        }

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

    }

    private fun setLanguage(languageCode: String) {
        Log.d("FlowerApp", "setLanguage $languageCode")
        LocaleHelper.setLocale(requireActivity(), languageCode)
        (activity as MainActivity).recreate()// reload Activity to refresh language
    }
}