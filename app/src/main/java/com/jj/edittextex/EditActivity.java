package com.jj.edittextex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jj.edittextex.editText.EditTextEx;

public class EditActivity extends AppCompatActivity {
    private EditTextEx editTextName;
    private EditTextEx editTextPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editTextName = (EditTextEx) findViewById(R.id.edit_name);
        editTextPwd = (EditTextEx) findViewById(R.id.edit_pwd);
    }
}
