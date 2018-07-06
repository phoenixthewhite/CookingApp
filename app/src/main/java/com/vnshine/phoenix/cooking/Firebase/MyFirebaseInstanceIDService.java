package com.vnshine.phoenix.cooking.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.vnshine.phoenix.cooking.Ultity.DataUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by canh7antt on 5/23/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
        DataUtil dataUtil=new DataUtil(getApplicationContext());
        dataUtil.writeKey(refreshedToken);


    }

    private void sendRegistrationToServer(String refreshedToken) {
    }
}