package edu.uoc.android.mvvmfirebaserecyclerbinding.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.uoc.android.mvvmfirebaserecyclerbinding.domain.IVersionCode

class MainViewModelFactory(val useCase: IVersionCode): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IVersionCode::class.java).newInstance(useCase)
    }
}