package com.example.carreadertest

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import android.view.View
import android.util.Log
import java.io.File
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



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

            val newdir = getDir("@string/program_dir_name", Context.MODE_APPEND)
            Log.i("Test", "Getting the newdir : " + newdir)

            val resFile = File(newdir, "output.txt")
            Log.i("Test", "Create file: " + resFile.toString())
            if (resFile.createNewFile() == true)
            {
                Log.i("Test", "Succesfully created: " + resFile.toString())
            } else {
                Log.i("Test", "Create failed: " + resFile.toString())
            }

            val uri = data!!.getData()
            Log.i("Test", "RAW URI " + uri)
            val pathName = uri!!.getPath()
            Log.i("Test", "PathName " + pathName)


            var file = File(fileName)

            // create a new file
            val isNewFileCreated :Boolean = file.createNewFile()

            if(isNewFileCreated){
                println("$fileName is created successfully.")
            } else{
                println("$fileName already exists.")
            }

            // try creating a file that already exists
            val isFileCreated :Boolean = file.createNewFile()

            if(isFileCreated){
                println("$fileName is created successfully.")
            } else{
                println("$fileName already exists.")
            }



        }
        when (requestCode) {
            9999 -> Log.i("Test", "Result URI " + data!!.data!!)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}