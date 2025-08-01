package com.oppenablers.jobhub.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.oppenablers.jobhub.R;

public class EmpMessagesFragment extends Fragment {

    public EmpMessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_emp_messages, container, false);
        LinearLayout message1 = view.findViewById(R.id.message_card_1);

        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.oppenablers.jobhub.activity.EmpDirectMessageActivity.class);
                intent.putExtra("userId", "xCOv7zBFK0eYoKZZ2gdXEHSUzN83");
                intent.putExtra("userName", "Mutsuki");
                startActivity(intent);
            }
        });

        return view;
    }
}