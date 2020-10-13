package com.example.udacity

private const val NO_IMAGE_PROVIDED = -1

class Word(internal val mDefaultTranslation: String, internal val mRusTranslation: String, val mResourceImageId: Int = NO_IMAGE_PROVIDED) {

    fun hasImage() = mResourceImageId != NO_IMAGE_PROVIDED
}