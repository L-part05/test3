package com.example.test03;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnCancel;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login); // 直接使用现有的对话框布局

        initViews();
        setClickListeners();
    }

    private void initViews() {
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSignIn = findViewById(R.id.btn_sign_in);
    }

    private void setClickListeners() {
        // 取消按钮点击事件
        btnCancel.setOnClickListener(v -> {
            Toast.makeText(LoginActivity.this, "取消登录", Toast.LENGTH_SHORT).show();
            finish(); // 关闭当前页面，返回主菜单
        });

        // 登录按钮点击事件
        btnSignIn.setOnClickListener(v -> {
            performLogin();
        });
    }

    private void performLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // 输入验证
        if (username.isEmpty()) {
            etUsername.setError("请输入用户名");
            etUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("请输入密码");
            etPassword.requestFocus();
            return;
        }

        // 模拟登录验证
        if (validateLogin(username, password)) {
            // 登录成功
            Toast.makeText(LoginActivity.this,
                    "登录成功！欢迎 " + username, Toast.LENGTH_SHORT).show();

            // 可以在这里跳转到其他页面，或者返回主菜单
            finish(); // 关闭登录页面，返回主菜单
        } else {
            // 登录失败
            Toast.makeText(LoginActivity.this,
                    "登录失败！用户名或密码错误", Toast.LENGTH_SHORT).show();
            etPassword.setText(""); // 清空密码
            etPassword.requestFocus();
        }
    }

    /**
     * 登录验证逻辑
     * @param username 用户名
     * @param password 密码
     * @return 验证是否通过
     */
    private boolean validateLogin(String username, String password) {
        // 这里应该是真实的登录逻辑
        // 现在只是简单模拟：用户名为 "admin"，密码为 "123456"
        return "admin".equals(username) && "123456".equals(password);
    }


}