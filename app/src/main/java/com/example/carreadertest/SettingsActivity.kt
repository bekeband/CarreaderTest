package com.example.carreadertest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import android.view.View
import android.util.Log
import java.io.File
import android.content.Intent

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
    /** Called when the user touches the directory select button */
    fun selectdirectory(view: View) {


        val i = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        i.addCategory(Intent.CATEGORY_DEFAULT)
        startActivityForResult(Intent.createChooser(i, "File Chooser"), 9999)
     //   val chooserIntent = Intent.createChooser(i, "File Chooser")

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val fileName = "data.txt"
        if (requestCode == 9999) {

            val uri = data!!.getData()
            Log.i("Test", "RAW URI " + uri)
            val pathName = uri!!.getPath()
            Log.i("Test", "PathName " + pathName)
  //          val v = pathName + '/' + fileName
            val v = "test.txt"
            Log.i("Test", "Get total filename " + v)

            val file = File(v)

             // create a new file
                val isNewFileCreated: Boolean = file.createNewFile()

                if (isNewFileCreated) {
                    println("$fileName is created successfully.")
                } else {
                    println("$fileName already exists.")
                }

        }
        when (requestCode) {
            9999 -> Log.i("Test", "Result URI " + data!!.data!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}