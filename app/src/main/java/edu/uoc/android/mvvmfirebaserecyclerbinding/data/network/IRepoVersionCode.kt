package edu.uoc.android.mvvmfirebaserecyclerbinding.data.network

import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.flow.Flow

interface IRepoVersionCode {
    suspend fun getVersionCode(): Resource<Int>
    suspend fun getVersionCodeFlow(): Flow<Resource<Int>>
}