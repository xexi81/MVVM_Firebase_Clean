package edu.uoc.android.mvvmfirebaserecyclerbinding.domain

import edu.uoc.android.mvvmfirebaserecyclerbinding.data.network.IRepoVersionCode
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.flow.Flow

class VersionCodeImpl(private val repo: IRepoVersionCode): IVersionCode {
    override suspend fun getVersionCode(): Resource<Int> = repo.getVersionCode()
    override suspend fun getVersionCodeFlow(): Flow<Resource<Int>> = repo.getVersionCodeFlow()
}