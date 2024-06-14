package com.jhonny.social.presenter.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserPresentation(

    @field:SerializedName("results")
    val list: List<UserItemPresentation?>? = null,

    @field:SerializedName("info")
    val info: Info? = null
) : Parcelable
@Parcelize
data class Name(

    @field:SerializedName("last")
    val last: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("first")
    val first: String? = null
) : Parcelable

@Parcelize
data class Street(

    @field:SerializedName("number")
    val number: Int? = null,

    @field:SerializedName("name")
    val name: String? = null
) : Parcelable

@Parcelize
data class Info(

    @field:SerializedName("seed")
    val seed: String? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: Int? = null,

    @field:SerializedName("version")
    val version: String? = null
) : Parcelable

@Parcelize
data class Picture(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("large")
    val large: String? = null,

    @field:SerializedName("medium")
    val medium: String? = null
) : Parcelable

@Parcelize
data class UserItemPresentation(


    @field:SerializedName("isFavorite")
    var isFavorite: Boolean = false,

    @field:SerializedName("nat")
    val nat: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("dob")
    val dob: Dob? = null,

    @field:SerializedName("name")
    val name: Name? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("cell")
    val cell: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("picture")
    val picture: Picture? = null
) : Parcelable

@Parcelize
data class Location(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("street")
    val street: Street? = null,

    @field:SerializedName("postcode")
    val postcode: String? = null,


    @field:SerializedName("state")
    val state: String? = null
) : Parcelable

@Parcelize
data class Dob(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("age")
    val age: Int? = null
) : Parcelable