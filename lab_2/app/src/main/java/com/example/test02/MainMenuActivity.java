package com.example.test02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 添加这行
        setContentView(R.layout.activity_main_menu);

        // 获取按钮引用 - 使用正确的ID名称
        Button btnLinearLayout = findViewById(R.id.btn_linear);
        Button btnTableLayout = findViewById(R.id.btn_table);
        Button btnCalculator = findViewById(R.id.btn_calculator);
        Button btnSpace = findViewById(R.id.btn_space);

        // 设置线性布局按钮点击事件
        btnLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 设置表格布局按钮点击事件
        btnTableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, TableLayoutActivity.class);
                startActivity(intent);
            }
        });

        // 设置计算器按钮点击事件
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, CalculatorActivity.class); // 修复类名
                startActivity(intent);
            }
        });
        btnSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SpaceActivity.class);
                startActivity(intent);
            }
        });
    } // onCreate 方法结束
} // 类结束