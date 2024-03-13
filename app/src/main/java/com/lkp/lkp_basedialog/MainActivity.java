package com.lkp.lkp_basedialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lkp.dialog.BaseDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void dialog(View view) {
        new MessageDialog.Builder(this)
                .setTitle("测试标题")
                .setMessage("什么牛马")
                .setConfirm("冲啊")
                .setCancel("取消")
                .setCancelable(true)
                .setListener(new MessageDialog.OnListener() {
                    @Override
                    public void onConfirm(BaseDialog dialog) {
                        Toast.makeText(MainActivity.this, "点击了确认", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(BaseDialog dialog) {
                        Toast.makeText(MainActivity.this, "点击了取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}