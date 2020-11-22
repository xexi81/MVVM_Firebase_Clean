package edu.uoc.android.mvvmfirebaserecyclerbinding.data.network

import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
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
}