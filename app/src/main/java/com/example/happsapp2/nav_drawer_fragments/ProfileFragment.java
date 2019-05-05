package com.example.happsapp2.nav_drawer_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.happsapp2.R;

public class ProfileFragment extends Fragment {
    private TextView textViewUserName;
    private TextView textViewFirstName;
    private TextView textViewLastName;

    public ProfileFragment(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.user_profile_activity,container,false  );
        textViewFirstName = v.findViewById(R.id.text_view_first_name);
        textViewLastName = v.findViewById(R.id.text_view_last_name);
        textViewUserName = v.findViewById(R.id.text_view_user_name);

        textViewFirstName.setText(getArguments().getString("current_fName"));
        textViewLastName.setText(getArguments().getString("current_lName"));
        textViewUserName.setText(getArguments().getString("current_uName"));
        return v;
    }
}
