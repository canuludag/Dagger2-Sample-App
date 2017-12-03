package com.uludag.can.dagger2_sample_app.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.uludag.can.dagger2_sample_app.R;
import com.uludag.can.dagger2_sample_app.root.NewsApplication;
import com.uludag.can.dagger2_sample_app.ui.newslist.NewsListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    // Dagger Injection
    @Inject
    SharedPreferences mSharedPreferences;
    @Inject
    SharedPreferences.Editor mPrefsEditor;
    @Inject
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Initialize injection
        ((NewsApplication) getApplication()).getNewsApplicationComponent().inject(this);

        // Check if the user logged in before or not
        if (mSharedPreferences.contains("username")
                && mSharedPreferences.contains("password")) {
            startNewsListActivity();
        }

    }

    @OnClick(R.id.btn_login)
    public void onLoginButtonClick() {
        if (etUsername.getText().toString().length() == 0
                || etPassword.getText().toString().length() == 0) {

            Snackbar.make(container, "Please enter username and password.", Snackbar.LENGTH_SHORT).show();

        } else {

            saveToSharedPrefs();
            startNewsListActivity();

        }
    }

    private void saveToSharedPrefs() {

        // Save login credentials to shared prefs
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        mPrefsEditor.putString("username", username);
        mPrefsEditor.putString("password", password);
        mPrefsEditor.apply();

    }

    private void startNewsListActivity() {
        Intent iNewsListActivity = new Intent(mContext, NewsListActivity.class);
        startActivity(iNewsListActivity);
        finish();
    }

}
