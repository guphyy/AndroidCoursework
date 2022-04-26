package com.example.justloginregistertest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword2;
    private RadioButton mPositionChoose_worker;
    private RadioButton mPositionChoose_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);

    }

    private void initView(){
        Button mBtRegisteractivityRegister = findViewById(R.id.bt_registeractivity_register);
        ImageView mIvRegisteractivityBack = findViewById(R.id.iv_registeractivity_back);
        mEtRegisteractivityUsername = findViewById(R.id.et_registeractivity_username);
        mEtRegisteractivityPassword2 = findViewById(R.id.et_registeractivity_password2);

        mPositionChoose_worker = findViewById(R.id.radioButton_worker);
        mPositionChoose_manager = findViewById(R.id.radioButton_manager);
        mIvRegisteractivityBack.setOnClickListener(this);
        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_registeractivity_back: //back to login page
                Intent intent1 = new Intent(this, loginActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.bt_registeractivity_register:    //register btn
                //get info
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password = mEtRegisteractivityPassword2.getText().toString().trim();

                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    if (mPositionChoose_manager.isChecked()) {
                        //sent info to database
                        mDBOpenHelper.add(username, password, "manager");
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "Verification passed, registration successful", Toast.LENGTH_SHORT).show();
                    }else if (mPositionChoose_worker.isChecked()) {
                        mDBOpenHelper.add(username, password, "worker");
                        Intent intent2 = new Intent(this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        Toast.makeText(this,  "Verification passed, registration successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Not perfect information, registration failed", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Not perfect information, registration failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

