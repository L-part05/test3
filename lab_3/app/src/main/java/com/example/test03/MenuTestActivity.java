package com.example.test03;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuTestActivity extends AppCompatActivity {

    private TextView tvTestContent;

    // 当前字体大小和颜色状态
    private int currentTextSize = 16; // 默认中等大小
    private int currentTextColor = Color.BLACK; // 默认黑色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);

        // 设置 Toolbar
        setupToolbar();

        initViews();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 可选：添加返回按钮
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        tvTestContent = findViewById(R.id.tv_test_content);

        // 设置初始状态
        updateTextAppearance();
    }

    /**
     * 创建选项菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    /**
     * 准备显示菜单（设置默认选中状态）
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // 设置字体大小的默认选中状态
        switch (currentTextSize) {
            case 10:
                menu.findItem(R.id.font_small).setChecked(true);
                break;
            case 16:
                menu.findItem(R.id.font_medium).setChecked(true);
                break;
            case 20:
                menu.findItem(R.id.font_large).setChecked(true);
                break;
        }

        // 设置字体颜色的默认选中状态
        switch (currentTextColor) {
            case Color.RED:
                menu.findItem(R.id.font_red).setChecked(true);
                break;
            case Color.BLACK:
                menu.findItem(R.id.font_black).setChecked(true);
                break;
        }

        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * 处理菜单项点击事件
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        // 处理返回按钮
        if (itemId == android.R.id.home) {
            finish();
            return true;
        }

        if (itemId == R.id.menu_normal) {
            // 普通菜单项 - 显示Toast提示
            showToast("普通菜单项被点击");
            return true;
        }
        // 字体大小选项
        else if (itemId == R.id.font_small) {
            setTextSize(10);
            item.setChecked(true);
            showToast("字体大小设置为：小 (10sp)");
            return true;
        } else if (itemId == R.id.font_medium) {
            setTextSize(16);
            item.setChecked(true);
            showToast("字体大小设置为：中 (16sp)");
            return true;
        } else if (itemId == R.id.font_large) {
            setTextSize(20);
            item.setChecked(true);
            showToast("字体大小设置为：大 (20sp)");
            return true;
        }
        // 字体颜色选项
        else if (itemId == R.id.font_red) {
            setTextColor(Color.RED);
            item.setChecked(true);
            showToast("字体颜色设置为：红色");
            return true;
        } else if (itemId == R.id.font_black) {
            setTextColor(Color.BLACK);
            item.setChecked(true);
            showToast("字体颜色设置为：黑色");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置字体大小
     */
    private void setTextSize(int size) {
        currentTextSize = size;
        updateTextAppearance();
    }

    /**
     * 设置字体颜色
     */
    private void setTextColor(int color) {
        currentTextColor = color;
        updateTextAppearance();
    }

    /**
     * 更新文本外观
     */
    private void updateTextAppearance() {
        tvTestContent.setTextSize(currentTextSize);
        tvTestContent.setTextColor(currentTextColor);
    }

    /**
     * 显示Toast消息
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}