package edu.uoc.android.mvvmfirebaserecyclerbinding.domain

import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource

interface IVersionCode {

    suspend fun getVersionCode(): Resource<Int>
}