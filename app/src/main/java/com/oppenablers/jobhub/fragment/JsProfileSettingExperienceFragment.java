package com.oppenablers.jobhub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.oppenablers.jobhub.api.JobHubClient;
import com.oppenablers.jobhub.databinding.FragmentJsProfileSettingExperienceBinding;
import com.oppenablers.jobhub.databinding.FragmentJsProfileSettingPersonalBinding;
import com.oppenablers.jobhub.model.JobSeeker;


public class JsProfileSettingExperienceFragment extends Fragment {

    FragmentJsProfileSettingExperienceBinding binding;

    public JsProfileSettingExperienceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJsProfileSettingExperienceBinding
                .inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        JobHubClient.getAccountInfoJobSeeker(new JobHubClient.JobHubCallback<JobSeeker>() {
            @Override
            public void onFailure() {

            }

            @Override
            public void onSuccess(JobSeeker result) {
                EditText editText = binding.experienceTextInput.getEditText();
                if (editText == null) return;

                editText.setText(result.getWorkExperience());

                binding.updateButton.setOnClickListener(v -> {

                    result.setWorkExperience(editText.getText().toString());
                    JobHubClient.updateAccountInfoJobSeeker(result, new JobHubClient.JobHubCallbackVoid() {
                        @Override
                        public void onFailure() {

                        }

                        @Override
                        public void onSuccess() {
                            FragmentActivity activity = getActivity();
                            if (activity != null) activity.finish();
                        }
                    });
                });
            }
        });
    }
}