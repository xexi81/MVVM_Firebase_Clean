package edu.uoc.android.mvvmfirebaserecyclerbinding.data.network

import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource

interface IRepoVersionCode {
    suspend fun getVersionCode(): Resource<Int>
}