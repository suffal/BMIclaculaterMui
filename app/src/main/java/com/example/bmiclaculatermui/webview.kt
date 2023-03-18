package com.example.bmiclaculatermui


import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isGone
import com.example.bmiclaculatermui.databinding.ActivityWebviewBinding


class webview : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)



        setContentView(binding.root)



        binding.webview.webViewClient = MyWebViewClient()
        binding.webview.loadUrl("https://en.wikipedia.org/wiki/Body_mass_index")

    }





    inner class MyWebViewClient : WebViewClient() {
     val progress= progressdialog()


        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)




            //     binding.progressBar.visibility = View.GONE




        }


    }

  private fun progressdialog(){
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Go to BMI Application")
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

}






