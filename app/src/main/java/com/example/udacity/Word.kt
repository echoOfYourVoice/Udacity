package com.example.udacity

private const val NO_IMAGE_PROVIDED = -1

class Word(internal val mDefaultTranslation: String, internal val mTranslation: String, val mResourceImageId: Int = NO_IMAGE_PROVIDED, val audioResourceID: Int = 1) {

    fun hasImage() = mResourceImageId != NO_IMAGE_PROVIDED
}