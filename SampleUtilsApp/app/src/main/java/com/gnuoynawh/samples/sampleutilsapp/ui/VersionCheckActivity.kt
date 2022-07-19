package com.gnuoynawh.samples.sampleutilsapp.ui

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.gnuoynawh.samples.sampleutilsapp.R
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

/**
 *  using library - jsoup, gson, coroutines
 */
class VersionCheckActivity: AppCompatActivity() {


    data class Version(val ios: String, val aos: String)

    private val textView: AppCompatTextView by lazy {
        findViewById(R.id.textview)
    }

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version_check)

        //
        val asyncTask = object: AsyncTask<Void, String, String>() {

            override fun doInBackground(vararg args: Void?): String {

                val url = "https://gnuoynawh.github.io/version.html"

                val doc = Jsoup.connect(url)
                    .timeout(3000)
                    .ignoreContentType(true)
                    .execute()
                    .body()

                val ver = Gson().fromJson(doc, Version::class.java)

                Log.e("TEST", "doc.toString = $doc")
                Log.e("TEST", "ios = ${ver.ios}")
                Log.e("TEST", "aos = ${ver.aos}")

                return ver.aos
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)

                textView.text = "android version is $result"
            }
        }
        asyncTask.execute(null)
    }
}