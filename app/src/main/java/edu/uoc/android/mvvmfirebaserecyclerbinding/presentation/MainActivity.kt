package edu.uoc.android.mvvmfirebaserecyclerbinding.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.uoc.android.mvvmfirebaserecyclerbinding.base.BaseActivity
import edu.uoc.android.mvvmfirebaserecyclerbinding.BuildConfig
import edu.uoc.android.mvvmfirebaserecyclerbinding.data.network.RepoImplementVersionCode
import edu.uoc.android.mvvmfirebaserecyclerbinding.databinding.ActivityMainBinding
import edu.uoc.android.mvvmfirebaserecyclerbinding.domain.VersionCodeImpl
import edu.uoc.android.mvvmfirebaserecyclerbinding.extensions.view.hideView
import edu.uoc.android.mvvmfirebaserecyclerbinding.extensions.view.showView
import edu.uoc.android.mvvmfirebaserecyclerbinding.presentation.viewmodel.MainViewModel
import edu.uoc.android.mvvmfirebaserecyclerbinding.presentation.viewmodel.MainViewModelFactory
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource

class MainActivity : BaseActivity() {
    private val viewModel by lazy { ViewModelProvider(this, MainViewModelFactory(VersionCodeImpl(RepoImplementVersionCode()))).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData(binding)
    }


    private fun observeData(binding: ActivityMainBinding) {

        viewModel.fetchVersionCode.observe(this, Observer {result ->

            when(result) {
                is Resource.Loading -> {
                    binding.progressBar.showView()
                    binding.txtVersionCode.hideView()
                }
                is Resource.Success -> {
                    binding.txtVersionCode.text = result.data.toString()
                    binding.progressBar.hideView()
                    binding.txtVersionCode.showView()

                    val actualVersion: Int = result.data as Int
                    if (appIsOutDated(actualVersion)) showVersionDialog()
                }
                is Resource.Failure -> {
                    binding.txtVersionCode.text = "Error version request"
                    binding.progressBar.hideView()
                    binding.txtVersionCode.showView()
                    Log.w("Sergio", result.exception.message.toString())
                }
            }
        })
    }


    fun appIsOutDated(actualVerion: Int): Boolean {
        return BuildConfig.VERSION_CODE < actualVerion
    }
}


