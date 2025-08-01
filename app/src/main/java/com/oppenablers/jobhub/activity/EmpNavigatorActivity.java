package com.oppenablers.jobhub.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.oppenablers.jobhub.R;
import com.oppenablers.jobhub.databinding.ActivityEmpNavigatorBinding;
import com.oppenablers.jobhub.fragment.EmpJsFragment;
import com.oppenablers.jobhub.fragment.EmpMessagesFragment;
import com.oppenablers.jobhub.fragment.EmpVacanciesFragment;

public class EmpNavigatorActivity extends AppCompatActivity {

    ActivityEmpNavigatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmpNavigatorBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.statusBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        replaceFragment(new EmpVacanciesFragment(), "post");

        binding.bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.post) {
                replaceFragment(new EmpVacanciesFragment(), "post");
            } else if (itemId == R.id.match) {
                replaceFragment(new EmpJsFragment(), "match");
            } else if (itemId == R.id.emp_messages) {
                replaceFragment(new EmpMessagesFragment(), "message");
            } else {
                return false;
            }

            return true;
        });

        binding.topAppBar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.notifications) {
                Intent intent = new Intent(this, EmpNotificationsActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.settings) {
                Intent intent = new Intent(this, JsSettingsActivity.class);
                startActivity(intent);
            } else {
                return false;
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment, String selected) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}