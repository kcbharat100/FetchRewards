package com.bharat.fetchrewards.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getString(resId: Int): String = context.getString(resId)
    fun getString(resId: Int, vararg formatArgs: Any): String = context.getString(resId, *formatArgs)
}
