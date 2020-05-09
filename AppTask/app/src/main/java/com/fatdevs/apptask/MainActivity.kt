package com.fatdevs.apptask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var data = fetchData()
        data.execute("url")
        Log.d(TAG,"onCreate")

    }

    private inner class fetchData : AsyncTask<String , Void , String>(){
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d(TAG,"onPostExecute with $result")
            print("r")
        }

        override fun doInBackground(vararg params: String?): String {
            Log.d(TAG,"doInBackground ${params[0]}")
            var rssData = getXmlData(params[0])
            if(rssData.isEmpty()){
                Log.d(TAG,"doInBackground ${params[0]} error reading !!!!")
            }
            else {
                
            }
            return rssData
        }
        fun getXmlData(urlPath:String?) : String {
            // get Url
            // establish connection
            // get response
            try {
                val url: URL = URL(urlPath)
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
                val response = urlConnection.responseCode // gets response from the website
                if(response == 404){
                    print("Page not found !!!")
                }
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))

            }catch (e:Exception){
                print(e.printStackTrace().toString())
            }
        }
    }
}
