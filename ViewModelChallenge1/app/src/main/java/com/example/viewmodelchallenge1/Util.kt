package com.example.viewmodelchallenge1

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)