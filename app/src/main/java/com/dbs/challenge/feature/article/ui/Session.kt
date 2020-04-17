package com.dbs.challenge.feature.article.ui

import android.app.Activity
import java.util.*

data class Session(var id: String = UUID.randomUUID().toString())
data class SessionActivity(val activity: Activity, var id: String = UUID.randomUUID().toString())
