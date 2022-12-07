package com.example.basicmaterial3.domain


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Response dataclass
 */
class AcromineResponse : ArrayList<AcromineResponse.AcromineResponseItem>() {
    @Parcelize
    data class AcromineResponseItem(
        @SerializedName("lfs")
        val lfs: List<Lf?>?,
        @SerializedName("sf")
        val sf: String? // HMM
    ) : Parcelable {


        @Parcelize
        data class Lf(
            @SerializedName("freq")
            val freq: Int?, // 267
            @SerializedName("lf")
            val lf: String?, // heavy meromyosin
            @SerializedName("since")
            val since: Int?, // 1971
            @SerializedName("vars")
            val vars: List<Var?>?
        ) : Parcelable {


            @Parcelize
            data class Var(
                @SerializedName("freq")
                val freq: Int?, // 244
                @SerializedName("lf")
                val lf: String?, // heavy meromyosin
                @SerializedName("since")
                val since: Int? // 1971
            ) : Parcelable


        }
    }
}