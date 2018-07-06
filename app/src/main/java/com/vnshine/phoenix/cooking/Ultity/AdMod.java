package com.vnshine.phoenix.cooking.Ultity;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.vnshine.phoenix.cooking.R;

/**
 * Created by canh7antt on 8/28/2017.
 */

public class AdMod {
    private InterstitialAd mInterstitialAd;

    public AdMod(Context context) {
        buildAdFullScreen(context);
    }
    public void buildAdFullScreen(Context context){
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial_ad_unit_id));
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice("53479B960110C543D1B57CD88E65065B").build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("53479B960110C543D1B57CD88E65065B").build());
            }

        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
                System.out.println("aaaaaaa");
            }
        });
    }


}
