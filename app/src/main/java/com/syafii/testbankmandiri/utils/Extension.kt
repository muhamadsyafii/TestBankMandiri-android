package com.syafii.testbankmandiri.utils
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun <T> Fragment.openActivity(destination: Class<T>, bundleKey: String, id: String) {
    val intent = Intent(requireContext(), destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}

fun <T> Fragment.openActivity(destination: Class<T>) {
    val intent = Intent(requireContext(), destination)
    startActivity(intent)
}

fun <T> Activity.openActivity(destination: Class<T>, bundleKey: String, id: String) {
    val intent = Intent(this, destination)
    intent.putExtra(bundleKey, id)
    startActivity(intent)
}

fun <T> Activity.openActivity(destination: Class<T>) {
    val intent = Intent(this, destination)
    startActivity(intent)
}

fun Activity.closeActivity(){
    hideKeyboard()
    finish()
}

fun View.visible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) this.visibility =
        View.VISIBLE
}

fun View.gone() {
    if (this.visibility == View.VISIBLE) this.visibility = View.GONE
}

fun View.invisible() {
    if (this.visibility == View.VISIBLE) this.visibility = View.INVISIBLE
}

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    if (view != null) {
        inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}