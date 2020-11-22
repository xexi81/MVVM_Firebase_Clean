package edu.uoc.android.mvvmfirebaserecyclerbinding.data.network

import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.android.mvvmfirebaserecyclerbinding.valueObject.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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

    @ExperimentalCoroutinesApi
    override suspend fun getVersionCodeFlow(): Flow<Resource<Int>> = callbackFlow {
        val eventDocument = FirebaseFirestore
            .getInstance()
            .collection("Params")
            .document("app")

        val subscription = eventDocument.addSnapshotListener{ documentSnapshot, firebaseFirestoreException ->
            if (documentSnapshot != null && documentSnapshot.exists()) {
                if (documentSnapshot.getLong("version") == null) {
                    val version = 0
                    offer(Resource.Success(version))
                } else {
                    val version = documentSnapshot.getLong("version")!!.toInt()
                    offer(Resource.Success(version))
                }
            } else channel.close(firebaseFirestoreException?.cause)
        }

        awaitClose{ subscription.remove() }

    }
}