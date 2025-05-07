package com.joe.taichungapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joe.taichungapp.R
import com.joe.taichungapp.model.AttractionsInfo
import java.util.Locale

class AttractionsAdapter(
    private val list: List<AttractionsInfo>,
    private val onItemClick: (AttractionsInfo) -> Unit
) : RecyclerView.Adapter<AttractionsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.flowerName)
        private val image: ImageView = view.findViewById(R.id.flowerImage)
        private val currentLanguage = Locale.getDefault().language.toString()
        fun bind(item: AttractionsInfo) {
            name.text = if (currentLanguage == "zh-rTW") item.nameChinese else item.nameEnglish
            // 使用 Glide 載入圖片
            Glide.with(itemView.context)
                .load(item.photoUrl1)
                .into(image)
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flower, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}