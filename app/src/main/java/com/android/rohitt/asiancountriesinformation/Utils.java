package com.android.rohitt.asiancountriesinformation;

import android.content.Context;
import android.widget.ImageView;

import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils{
    private static OkHttpClient okHttpClient;

    public static void fetchSVG(Context context, String url, final ImageView flagView){
        if(okHttpClient == null){
            okHttpClient = new OkHttpClient.Builder().cache(new Cache(context.getCacheDir(), 5*1024*1014)).build();
        }

        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = response.body().byteStream();
                Sharp.loadInputStream(is).into(flagView);
                is.close();

            }
        });
    }

}
