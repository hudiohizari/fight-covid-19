package id.rumahawan.fightcovid19.navigation.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.rumahawan.fightcovid19.R
import id.rumahawan.fightcovid19.databinding.ActivityWebViewBinding
import id.rumahawan.fightcovid19.navigation.bridge.InterfaceWebView
import id.rumahawan.fightcovid19.navigation.viewmodel.ViewModelWebView
import id.rumahawan.fightcovid19.utils.BaseActivity
import id.rumahawan.fightcovid19.utils.Constant.KEY_TITLE
import id.rumahawan.fightcovid19.utils.Constant.KEY_URL

class ActivityWebView:
    BaseActivity(),
    InterfaceWebView
{
    private lateinit var binding: ActivityWebViewBinding
    private lateinit var viewModel: ViewModelWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        viewModel = ViewModelProvider(this).get(ViewModelWebView::class.java)
        viewModel.bridge = this
        viewModel.title.value = intent.getStringExtra(KEY_TITLE)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initView()
    }

    private fun initView(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.wvMain.settings.safeBrowsingEnabled = true
        }
        binding.wvMain.setInitialScale(1)
        binding.wvMain.settings.loadsImagesAutomatically = true
        binding.wvMain.settings.javaScriptEnabled = true
        binding.wvMain.settings.javaScriptCanOpenWindowsAutomatically = true
        binding.wvMain.settings.loadWithOverviewMode = true
        binding.wvMain.settings.useWideViewPort = true
        binding.wvMain.settings.builtInZoomControls = false
        binding.wvMain.settings.displayZoomControls = false
        binding.wvMain.settings.domStorageEnabled = true
        binding.wvMain.settings.allowFileAccess = true
        binding.wvMain.loadUrl(intent.getStringExtra(KEY_URL))
        binding.wvMain.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                binding.pbMain.also {
                    if (it.visibility != View.VISIBLE) it.visibility = View.VISIBLE
                }
                view.loadUrl(url)
                return true
            }
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                binding.pbMain.also {
                    if (it.visibility != View.GONE) it.visibility = View.GONE
                }
            }
        }
    }

    override fun onBackButton() {
        onBackPressed()
    }
}
