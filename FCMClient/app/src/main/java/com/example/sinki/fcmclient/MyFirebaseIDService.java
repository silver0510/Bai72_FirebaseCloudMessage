package com.example.sinki.fcmclient;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sinki on 9/14/2017.
 */

public class MyFirebaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        luuTokenVaoCSDL(token);
    }

    private void luuTokenVaoCSDL(String token) {
        new FireBaseIDTask().execute(token);
    }

}
