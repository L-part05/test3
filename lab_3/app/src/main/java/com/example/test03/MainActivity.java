package com.example.test03;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSimpleAdapter;
    private Button btnLogin;
    private Button btnMenuTest;
    private Button btnContextMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setClickListeners();
    }

    private void initViews() {
        btnSimpleAdapter = findViewById(R.id.btn_simple_adapter);
        btnLogin = findViewById(R.id.btn_login);
        btnMenuTest = findViewById(R.id.btn_menu_test);
        btnContextMenu = findViewById(R.id.btn_context_menu);
    }

    private void setClickListeners() {
        // SimpleAdapter 按钮点击事件
        btnSimpleAdapter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        });

        // 登录页面按钮点击事件
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        // 菜单测试按钮点击事件
        btnMenuTest.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuTestActivity.class);
            startActivity(intent);
        });
        btnContextMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContextMenuActivity.class);
            startActivity(intent);
        });
    }
}