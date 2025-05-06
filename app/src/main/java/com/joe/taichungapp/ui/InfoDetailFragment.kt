package com.joe.taichungapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.joe.taichungapp.R

class InfoDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_info_detail, container, false) // 更新名稱
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = InfoDetailFragmentArgs.fromBundle(requireArguments())
        val flowerInfo = args.flowerInfo
        val attractionsInfo = args.attractionsInfo

        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)
        val otherInfoTextView: TextView = view.findViewById(R.id.otherInfoTextView)

        if (flowerInfo != null) {
            titleTextView.text = flowerInfo.flowerType ?: "-"
            descriptionTextView.text = "觀賞時期: ${flowerInfo.viewingPeriod ?: "-"}"
            addressTextView.text = "地址: ${flowerInfo.address ?: "-"}"
            otherInfoTextView.text = "地點: ${flowerInfo.location ?: "-"}"
        } else if (attractionsInfo != null) {
            titleTextView.text = attractionsInfo.nameChinese ?: "-"
            descriptionTextView.text = attractionsInfo.descriptionChinese ?: "-"
            addressTextView.text = "地址: ${attractionsInfo.addressChinese ?: "-"}"
            otherInfoTextView.text = "電話: ${attractionsInfo.servicePhone ?: "-"}"
        }
    }
}