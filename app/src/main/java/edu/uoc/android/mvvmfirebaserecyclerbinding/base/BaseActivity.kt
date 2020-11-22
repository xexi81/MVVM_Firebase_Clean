package edu.uoc.android.mvvmfirebaserecyclerbinding.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    fun showVersionDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder
            .setMessage("Existe nueva versión, Actualice app")
            .setCancelable(false)
            .setPositiveButton("Actualizar ahora") { dialog, which ->
                openAppInPlaystore("com.mobile.legends")
            }
            .setNegativeButton("Ahora no") {dialog, which -> {
                dialog.dismiss()
            } }

        val alert = dialogBuilder.create()
        alert.setTitle("Actualización disponible")
        alert.show()
    }

    private fun openAppInPlaystore(appPackageName: String) {
        try{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
        }
    }
}