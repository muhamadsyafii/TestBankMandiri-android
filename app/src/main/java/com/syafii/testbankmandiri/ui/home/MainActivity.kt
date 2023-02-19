package com.syafii.testbankmandiri.ui.home
/*
 * Created by Muhamad Syafii
 * Sunday, 19/02/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.syafii.testbankmandiri.databinding.ActivityMainBinding
import com.syafii.testbankmandiri.domain.model.GenreModel
import com.syafii.testbankmandiri.ui.home.adapter.GenreAdapter
import com.syafii.testbankmandiri.utils.StateUi
import com.syafii.testbankmandiri.utils.hideKeyboard
import com.syafii.testbankmandiri.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy {
        GenreAdapter().apply {
            onItemClick = { data ->
                handleOnItemClick(data)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        subscribeToLiveData()
        initView()
    }

    private fun initData() {
        viewModel.getListGenre()
    }

    private fun subscribeToLiveData() {
        viewModel.genreResult.observe(this) { data ->
            when (data) {
                is StateUi.Loading -> {
                    showLoading(true)
                }
                is StateUi.Failed -> {
                    showLoading(false)
                    showEmptyLayout(true)
                    showToast(data.message)
                }
                is StateUi.Success -> {
                    showLoading(false)
                    setData(data.data.genre)
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    adapter.searchGenre(text ?: "")
                    return true
                }
            })

            layoutEmpty.btnReload.setOnClickListener { initData() }
        }


    }

    private fun showEmptyLayout(isEmpty: Boolean) {
        binding.layoutEmpty.root.isVisible = isEmpty
    }

    private fun setData(genres: List<GenreModel.GenreItemModel>) {
        showEmptyLayout(genres.isEmpty())
        adapter.setData(genres)
//        adapter.onClick = {
//            val dataExtra = Gson().toJson(it)
//            val intent = Intent(this, MovieActivity::class.java)
//            intent.putExtra(MovieActivity.DATA_EXTRA_GENRE, dataExtra)
//            startActivity(intent)
//        }
        binding.rvGenre.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        binding.run {
            progressBar.isVisible = isLoading
        }
    }


    private fun handleOnItemClick(data: GenreModel.GenreItemModel) {

    }


}