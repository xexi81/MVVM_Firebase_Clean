package edu.uoc.android.mvvmfirebaserecyclerbinding.data.network

import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RepoImplementVersionCode: IRepoVersionCode {
    override suspend fun getVersionCode(): Resource<Int> {
        // Firebase
        val resultData = FirebaseFirestore.getInstance()
            .collection("Params")
            .document("app")
            .get()
            .await()

        val versionCode = resultData.getLong("version")

        if (versionCode == null)
            return Resource.Success(0)
        else
            return Resource.Success(versionCode.toInt())
    }

    override suspend fun getVersionCodeFlow(): Flow<Resource<Int>> = flow {
        emit(Resource.Success(2))
        kotlinx.coroutines.delay(3000)
        emit(Resource.Success(3))
        kotlinx.coroutines.delay(3000)
        emit(Resource.Success(4))
    }
}