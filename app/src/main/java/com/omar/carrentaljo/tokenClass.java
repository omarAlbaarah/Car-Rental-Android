package com.omar.carrentaljo;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;



public class tokenClass extends FirebaseInstanceIdService {
    private static final String reg_token="reg_token";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(reg_token,refreshedToken);






        //sendRegistrationToServer(refreshedToken);
    }
}



