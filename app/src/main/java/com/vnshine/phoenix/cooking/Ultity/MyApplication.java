package com.vnshine.phoenix.cooking.Ultity;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vnshine.phoenix.cooking.R;

/**
 * Created by lamnd on 7/30/2017.
 */

public class MyApplication extends Application {
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sAnalytics = GoogleAnalytics.getInstance(this);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevice");
    }
    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }
        return sTracker;
    }
}