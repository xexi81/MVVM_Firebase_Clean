package edu.uoc.android.mvvmfirebaserecyclerbinding.domain

import edu.uoc.android.mvvmfirebaserecyclerbinding.data.network.IRepoVersionCode
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource

class VersionCodeImpl(private val repo: IRepoVersionCode): IVersionCode {
    override suspend fun getVersionCode(): Resource<Int> = repo.getVersionCode()
}