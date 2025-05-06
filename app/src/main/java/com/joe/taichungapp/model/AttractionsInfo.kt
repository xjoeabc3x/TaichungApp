package com.joe.taichungapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AttractionsInfo(
    @SerializedName("景點中文名稱") val nameChinese: String?,
    @SerializedName("景點英文名稱") val nameEnglish: String?,
    @SerializedName("景點特色簡述(中文)") val descriptionChinese: String?,
    @SerializedName("景點特色簡述(英文)") val descriptionEnglish: String?,
    @SerializedName("海域活動圖式編號") val seaActivityIconId: String?,
    @SerializedName("設施圖示編號") val facilityIconId: String?,
    @SerializedName("經度") val longitude: String?,
    @SerializedName("緯度") val latitude: String?,
    @SerializedName("景點服務電話") val servicePhone: String?,
    @SerializedName("景點中文地址") val addressChinese: String?,
    @SerializedName("景點英文地址") val addressEnglish: String?,
    @SerializedName("開放時間") val openingHours: String?,
    @SerializedName("開放時間備註") val openingHoursNoteChinese: String?,
    @SerializedName("開放時間英文備註") val openingHoursNoteEnglish: String?,
    @SerializedName("照片連結網址1") val photoUrl1: String?,
    @SerializedName("照片中文說明1") val photoDescriptionChinese1: String?,
    @SerializedName("網址") val websiteUrl: String?,
    @SerializedName("(選填)地圖服務連結") val mapServiceLink: String?
) : Serializable
