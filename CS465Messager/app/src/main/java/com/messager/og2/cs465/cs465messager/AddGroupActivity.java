package com.messager.og2.cs465.cs465messager;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class AddGroupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

    }

    public void saveClicked(View v) {
        this.finish();
    }
}
