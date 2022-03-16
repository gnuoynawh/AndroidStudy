package com.gnuoynawh.exam.versionchecker

import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import org.jsoup.Jsoup
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VersionChecker().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null)

    }

    class VersionChecker : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg str: String?): String {
            var newVersion = ""

            try {
                val url = "https://gnuoynawh.github.io/version.html"
                val doc = Jsoup.connect(url)
                    .timeout(3000)
                    .ignoreContentType(true)
                    .execute()
                    .body()

                val ver = Gson().fromJson(doc, Version::class.java)
                newVersion = ver.aos

                Log.e("TEST", "doc.toString = $doc")
                Log.e("TEST", "ios = ${ver.ios}")
                Log.e("TEST", "aos = ${ver.aos}")

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return newVersion
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            Log.e("TEST", "result = $result")
        }
    }
}