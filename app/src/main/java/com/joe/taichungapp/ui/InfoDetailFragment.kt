package com.joe.taichungapp.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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

        val backbutton: ImageButton = view.findViewById(R.id.backButton)
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)
        val otherInfoTextView: TextView = view.findViewById(R.id.otherInfoTextView)
        val picture: ImageView = view.findViewById(R.id.iv_info_detail_picture)
        val webButton: Button = view.findViewById(R.id.bt_website)

        backbutton.setOnClickListener {
            findNavController().navigateUp()
        }

        if (flowerInfo != null) {
            titleTextView.text = flowerInfo.flowerType ?: "-"
            descriptionTextView.text = "觀賞時期: ${flowerInfo.viewingPeriod ?: "-"}"
            addressTextView.text = "地址: ${flowerInfo.address ?: "-"}"
            otherInfoTextView.text = "地點: ${flowerInfo.location ?: "-"}"
            picture.visibility = View.GONE
            webButton.visibility = View.INVISIBLE
        } else if (attractionsInfo != null) {
            titleTextView.text = attractionsInfo.nameChinese ?: "-"
            descriptionTextView.text = attractionsInfo.descriptionChinese ?: "-"
            addressTextView.text = "地址: ${attractionsInfo.addressChinese ?: "-"}"
            otherInfoTextView.text = "電話: ${attractionsInfo.servicePhone ?: "-"}"
            Glide.with(view.context)
                .load(attractionsInfo.photoUrl1)
                .into(picture)
            webButton.setOnClickListener {
                val action = InfoDetailFragmentDirections.actionInfoDetailFragmentToWebViewFragment(attractionsInfo.websiteUrl)
                findNavController().navigate(action)
            }
        }


    }
}