package edu.uoc.android.mvvmfirebaserecyclerbinding.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import edu.uoc.android.mvvmfirebaserecyclerbinding.domain.IVersionCode
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(useCase:IVersionCode): ViewModel() {

    val fetchVersionCode = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            val version = useCase.getVersionCode()
            emit(version)
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("ERROR", e.message.toString())
        }
    }

}