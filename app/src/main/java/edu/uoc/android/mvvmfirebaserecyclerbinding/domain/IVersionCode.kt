package edu.uoc.android.mvvmfirebaserecyclerbinding.domain

import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.flow.Flow


interface IVersionCode {

    suspend fun getVersionCode(): Resource<Int>
    suspend fun getVersionCodeFlow(): Flow<Resource<Int>>
}