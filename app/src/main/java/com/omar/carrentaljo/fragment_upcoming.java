package com.omar.carrentaljo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DELL on 14/03/2018.
 */

public class fragment_upcoming extends Fragment {

    View v;
    public fragment_upcoming() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.upcomin_rents,container,false);
        return v;
    }
}
