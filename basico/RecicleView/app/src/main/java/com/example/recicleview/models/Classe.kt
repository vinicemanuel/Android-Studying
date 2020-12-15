package com.example.recicleview.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Classe(var name: String, var ano: Int, val classeID: String = UUID.randomUUID().toString()) : Parcelable {

}