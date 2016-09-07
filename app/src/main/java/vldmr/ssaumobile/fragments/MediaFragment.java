package vldmr.ssaumobile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import vldmr.ssaumobile.R;

/**
 * Created by Vladimir on 03.04.2016.
 */
public class MediaFragment extends Fragment {
    static final int LAYOUT= R.layout.media_youtube_fragment;
    static final int LAYOUT2= R.layout.media_inst_fragment;
    View view;

    public static MediaFragment getInstance(int i){
        MediaFragment mediaFragment =new MediaFragment();
        Bundle b=new Bundle();b.putInt("tab",i);
        mediaFragment.setArguments(b);

        return mediaFragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        switch (getArguments().getInt("tab")){
            case 0:{view=inflater.inflate(LAYOUT,container,false);
                WebView webView= (WebView) view.findViewById(R.id.youtube);
                webView.setWebViewClient(new MyWebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.instagram.com/samara_university/");


                break;}
            case 1:{view=inflater.inflate(LAYOUT2,container,false);
                WebView webView= (WebView) view.findViewById(R.id.inst);
                webView.setWebViewClient(new MyWebViewClient());
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl("https://www.youtube.com/user/ssaunews/featured");
                break;}

        }

        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
