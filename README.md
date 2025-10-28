实验3：Android界面组件实验 实验信息 课程名称：移动应用开发

实验编号：实验3

实验名称：Android界面组件实践

学号：121052023045

姓名：林健

实验日期：2025.10.28

实验目标 掌握Android ListView信息发送用法、自定义布局的AlertDialog用法、XML定义菜单和上下文操作模式(ActionMode)的上下文菜单的使用方法

实验环境 环境组件 版本信息 开发工具 Android Studio Narwhal 3,Gradel 8.13 目标API API 21 开发语言 Java 项目包名 com.example.test02

注意:实验三的相关代码在该仓库下的lab_3文件夹中。

一、项目设计思路
核心目标
开发一个综合展示Android高级UI组件的应用，重点演示ListView、自定义对话框、菜单系统和上下文操作模式等核心组件的使用方法，帮助开发者掌握复杂界面交互的实现技术。

主要功能模块
主菜单导航：集成所有功能模块的统一入口

ListView与通知：展示列表数据绑定和通知系统集成

自定义登录对话框：实现自定义布局的AlertDialog

选项菜单系统：XML定义菜单和动态菜单处理

上下文操作模式：多选列表项的上下文菜单操作

用户需求分析
开发者需要了解Android高级UI组件的实际应用场景

学习者希望通过完整示例掌握复杂交互的实现方法

用户期望直观体验不同UI组件的功能和效果

应用应提供清晰的代码示例和学习参考

技术选型与实现
数据适配器：ArrayAdapter、SimpleAdapter

菜单系统：OptionsMenu、ContextMenu、ActionMode

对话框：自定义布局AlertDialog

通知系统：NotificationCompat、NotificationChannel

事件处理：OnClickListener、OnItemClickListener、MultiChoiceModeListener

二、项目功能实现
1. 主菜单导航系统
功能描述：作为应用的总控制中心，提供四个高级UI组件演示模块的导航入口。

实现思路：

使用MainActivity作为启动Activity

通过按钮点击触发Intent跳转到对应功能模块

统一管理所有功能模块的入口

核心代码：

java
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

        // 上下文菜单按钮点击事件
        btnContextMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ContextMenuActivity.class);
            startActivity(intent);
        });
    }
}package com.example.test03;



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

效果展示：
主界面清晰展示四个功能入口按钮，分别对应ListView、登录对话框、菜单测试和上下文菜单功能模块。
<img width="528" height="1209" alt="d77f0c0e60a9292fbe4cca67ee322f0f" src="https://github.com/user-attachments/assets/47216a2d-c8a2-44e2-9f06-efc7e142e34d" />


2. ListView与通知功能集成
功能描述：展示动物列表，点击列表项同时触发Toast提示和系统通知，演示ListView数据绑定与通知系统的集成使用。

实现思路：

使用SimpleAdapter绑定图片和文本数据到ListView

实现OnItemClickListener处理列表项点击事件

集成NotificationCompat构建丰富的通知内容

创建通知渠道兼容Android O及以上版本

核心代码：

package com.example.test03;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "animal_channel";
    private static final int NOTIFICATION_ID = 1;

    // 动物数据
    private final String[] animalNames = {"Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"};
    private final int[] animalImages = {
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.monkey,
            R.drawable.dog,
            R.drawable.cat,
            R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // 创建通知渠道
        createNotificationChannel();

        // 准备数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", animalImages[i]);
            map.put("text", animalNames[i]);
            data.add(map);
        }

        // 创建适配器
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"image", "text"},
                new int[]{R.id.iv_animal, R.id.tv_animal}
        );

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // 设置列表项点击事件
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedAnimal = animalNames[position];

            // 显示Toast
            showToast(selectedAnimal);

            // 发送通知
            sendNotification(selectedAnimal);
        });
    }

    private void showToast(String animalName) {
        String message = "您选择了: " + animalName;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(String animalName) {
        // 创建点击通知后的Intent
        Intent intent = new Intent(this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 构建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher) // 应用图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setContentTitle(animalName) // 列表项内容作为标题
                .setContentText("这是关于" + animalName + "的详细信息") // 自定义内容
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("这是关于" + animalName + "的详细描述。该动物具有独特的特征和习性，是自然界中重要的物种之一。"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true); // 点击后自动消失

        // 显示通知
     /*   NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());*/
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "动物通知";
            String description = "显示动物选择信息的通知";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}

布局代码:
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="动物列表"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="16dp"
        android:background="#2196F3"
        android:textColor="#FFFFFF" />

    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>

效果展示：
列表显示动物图片和名称，点击项显示Toast提示并发送包含详细描述的系统通知。
<img width="528" height="1158" alt="b61d65940cb23d54be107d6298fbba24" src="https://github.com/user-attachments/assets/4c0b0e85-f85f-458e-9dbd-b65934082c31" />


3. 自定义登录对话框
功能描述：实现自定义布局的AlertDialog，演示对话框的个性化定制和表单验证逻辑。

实现思路：

使用自定义布局文件作为对话框内容

实现用户名和密码的输入验证

模拟登录逻辑处理

提供取消和登录两种操作选项

核心代码：

java
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

布局文件关键部分：

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp"
    android:background="@drawable/dialog_background">

    <!-- 标题 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="ANDROID APP"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:textColor="#2196F3"
        android:layout_marginBottom="24dp" />

    <!-- 用户名输入框 -->
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp"
        android:hint="Username">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_username"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="text"
            android:maxLines="1"
            android:textColorHint="#666666" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- 密码输入框 -->
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:hint="Password">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_password"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:maxLines="1"
            android:textColorHint="#666666" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- 按钮布局 -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="end">

        <!-- 取消按钮 -->
        <Button
            android:id="@+id/btn_cancel"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Cancel"
            android:textColor="#666666"
            android:background="?attr/selectableItemBackground"
            android:paddingStart="16dp"
            android:paddingEnd="16dp"
            android:paddingTop="8dp"
            android:paddingBottom="8dp"
            android:layout_marginEnd="16dp" />

        <!-- 登录按钮 -->
        <Button
            android:id="@+id/btn_sign_in"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sign in"
            android:textColor="#2196F3"
            android:background="?attr/selectableItemBackground"
            android:paddingStart="16dp"
            android:paddingEnd="16dp"
            android:paddingTop="8dp"
            android:paddingBottom="8dp" />

    </LinearLayout>

</LinearLayout>

效果展示：
显示自定义登录对话框，包含用户名密码输入框和操作按钮，支持输入验证和登录状态反馈。
<img width="534" height="1281" alt="898ce915efa5b4a4f3a7a162d2fa3e00" src="https://github.com/user-attachments/assets/0be45f13-4dfa-49be-9939-6b00635c48b4" />


4. XML定义选项菜单系统
功能描述：通过XML定义选项菜单，实现字体大小和颜色的动态切换，展示菜单系统的完整使用流程。

实现思路：

使用Toolbar作为ActionBar替代品

通过XML文件定义菜单结构和分组

实现菜单项选中状态管理

动态更新文本样式

核心代码：

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

布局代码:
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#f5f5f5">

    <!-- 添加Toolbar -->
    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
        android:elevation="4dp" />

    <!-- 原来的内容 -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp"
        android:gravity="center">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="MenuTest"
            android:textSize="24sp"
            android:textStyle="bold"
            android:gravity="center"
            android:layout_marginBottom="32dp"
            android:textColor="#333333" />

        <TextView
            android:id="@+id/tv_test_content"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="用于测试的内容"
            android:textSize="16sp"
            android:textColor="#000000"
            android:gravity="center"
            android:padding="20dp"
            android:background="@drawable/text_background"
            android:layout_marginBottom="32dp" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="请点击右上角菜单按钮进行测试"
            android:textSize="14sp"
            android:textColor="#666666"
            android:gravity="center"
            android:padding="16dp" />

    </LinearLayout>

</LinearLayout>

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_font_size"
        android:title="字体大小">
        <menu>
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/font_small"
                    android:title="小" />
                <item
                    android:id="@+id/font_medium"
                    android:title="中" />
                <item
                    android:id="@+id/font_large"
                    android:title="大" />
            </group>
        </menu>
    </item>

    <item
        android:id="@+id/menu_normal"
        android:title="普通菜单项" />

    <item
        android:id="@+id/menu_font_color"
        android:title="字体颜色">
        <menu>
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/font_red"
                    android:title="红色" />
                <item
                    android:id="@+id/font_black"
                    android:title="黑色" />
            </group>
        </menu>
    </item>
</menu>

效果展示：
Toolbar显示菜单选项，点击可动态改变文本的字体大小和颜色，菜单项支持单选分组和选中状态显示。
<img width="531" height="1157" alt="ccb82b39a2665a8cdf166c974dd21507" src="https://github.com/user-attachments/assets/2e038088-2c64-4d34-9f50-66b7dda14300" />


5. 上下文操作模式 (ActionMode)
功能描述：实现多选列表项的上下文操作模式，支持选择、删除、分享等批量操作，展示高级交互模式的应用。

实现思路：

使用ListView的多选模式 (CHOICE_MODE_MULTIPLE_MODAL)

实现MultiChoiceModeListener处理上下文操作生命周期

动态显示/隐藏选择框

实现选中计数和批量操作功能

核心代码：

package com.example.test03;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ContextMenuActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvSelectionCount;
    private ArrayAdapter<String> adapter;
    private List<String> dataList;

    // ActionMode 引用
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        initViews();
        setupListView();
    }

    private void initViews() {
        listView = findViewById(android.R.id.list);
        tvSelectionCount = findViewById(R.id.tv_selection_count);
    }

    private void setupListView() {
        // 初始化数据
        dataList = new ArrayList<>();
        dataList.add("One");
        dataList.add("Two");
        dataList.add("Three");
        dataList.add("Four");
        dataList.add("Five");

        // 创建适配器
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_context, android.R.id.text1, dataList) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                // 获取选择框
                android.widget.CheckBox checkBox = view.findViewById(R.id.cb_selection);

                if (actionMode != null) {
                    // ActionMode 激活时显示选择框
                    checkBox.setVisibility(View.VISIBLE);
                    checkBox.setChecked(listView.isItemChecked(position));
                } else {
                    // ActionMode 未激活时隐藏选择框
                    checkBox.setVisibility(View.GONE);
                }

                return view;
            }
        };

        listView.setAdapter(adapter);

        // 设置多选模式
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        // 设置多选模式监听器
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 创建 ActionMode
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                actionMode = mode;

                // 显示选中计数
                tvSelectionCount.setVisibility(View.VISIBLE);
                updateSelectionCount();

                // 刷新列表以显示选择框
                adapter.notifyDataSetChanged();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_delete) {
                    // 删除选中的项目
                    deleteSelectedItems();
                    mode.finish();
                    return true;
                } else if (itemId == R.id.menu_share) {
                    // 分享选中的项目
                    shareSelectedItems();
                    return true;
                } else if (itemId == R.id.menu_more) {
                    // 更多操作
                    showMoreOptions();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // 销毁 ActionMode
                actionMode = null;
                tvSelectionCount.setVisibility(View.GONE);

                // 清除所有选择
                listView.clearChoices();

                // 刷新列表以隐藏选择框
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                // 选中状态改变时更新计数
                updateSelectionCount();
            }
        });

        // 设置项目点击监听器（普通点击）
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode == null) {
                    // 普通点击时显示项目内容
                    String item = dataList.get(position);
                    Toast.makeText(ContextMenuActivity.this,
                            "Clicked: " + item, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 更新选中计数
     */
    private void updateSelectionCount() {
        int count = listView.getCheckedItemCount();
        tvSelectionCount.setText(count + " selected");

        if (actionMode != null) {
            actionMode.setTitle(count + " selected");
        }
    }

    /**
     * 删除选中的项目
     */
    private void deleteSelectedItems() {
        // 获取选中的位置（从后往前删除，避免索引变化）
        for (int i = dataList.size() - 1; i >= 0; i--) {
            if (listView.isItemChecked(i)) {
                String removedItem = dataList.remove(i);
                Toast.makeText(this, "Deleted: " + removedItem, Toast.LENGTH_SHORT).show();
            }
        }

        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Items deleted", Toast.LENGTH_SHORT).show();
    }

    /**
     * 分享选中的项目
     */
    private void shareSelectedItems() {
        StringBuilder selectedItems = new StringBuilder();

        for (int i = 0; i < dataList.size(); i++) {
            if (listView.isItemChecked(i)) {
                if (selectedItems.length() > 0) {
                    selectedItems.append(", ");
                }
                selectedItems.append(dataList.get(i));
            }
        }

        if (selectedItems.length() > 0) {
            Toast.makeText(this, "Sharing: " + selectedItems.toString(), Toast.LENGTH_SHORT).show();

            // 实际应用中这里会调用分享 Intent
            /*
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, selectedItems.toString());
            startActivity(Intent.createChooser(shareIntent, "Share items"));
            */
        } else {
            Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示更多选项
     */
    private void showMoreOptions() {
        int count = listView.getCheckedItemCount();
        Toast.makeText(this, count + " items selected for more operations", Toast.LENGTH_SHORT).show();
    }
}

布局代码:
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:background="#f5f5f5">

    <!-- 标题栏 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Context Menu Demo"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center"
        android:padding="16dp"
        android:background="#2196F3"
        android:textColor="#FFFFFF" />

    <!-- 选中计数显示 -->
    <TextView
        android:id="@+id/tv_selection_count"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="0 selected"
        android:textSize="16sp"
        android:gravity="center"
        android:padding="8dp"
        android:background="#E3F2FD"
        android:textColor="#1976D2"
        android:visibility="gone" />

    <!-- 列表 -->
    <ListView
        android:id="@android:id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="#E0E0E0"
        android:dividerHeight="1dp"
        android:choiceMode="multipleChoice" />

</LinearLayout>

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <!-- 删除操作 -->
    <item
        android:id="@+id/menu_delete"
        android:icon="@android:drawable/ic_menu_delete"
        android:title="删除"
        app:showAsAction="ifRoom" />

    <!-- 分享操作 -->
    <item
        android:id="@+id/menu_share"
        android:icon="@android:drawable/ic_menu_share"
        android:title="分享"
        app:showAsAction="ifRoom" />

    <!-- 更多操作 -->
    <item
        android:id="@+id/menu_more"
        android:icon="@android:drawable/ic_menu_more"
        android:title="更多"
        app:showAsAction="ifRoom" />

</menu>

效果展示：
长按列表项进入多选模式，显示选择框和上下文操作栏，支持多选、删除、分享等操作，实时显示选中计数。
<img width="522" height="1294" alt="da47cbaf8d3bb0ad120917c0c598b898" src="https://github.com/user-attachments/assets/ceed2d41-90d9-48ce-bff4-d54816ee6d90" />


三、关键技术细节
1. 应用配置管理
AndroidManifest.xml配置：

xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Test03">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".ListActivity"
            android:exported="false" />
        <activity
            android:name=".LoginActivity"
            android:exported="false" />
        <activity
            android:name=".MenuTestActivity"
            android:exported="false" />
        <activity
            android:name=".ContextMenuActivity"
            android:exported="false" />
    </application>

</manifest>
</application>
2. 数据适配技术
SimpleAdapter：用于复杂数据结构的列表绑定

ArrayAdapter：用于简单数据列表，支持自定义布局

数据绑定：Map数据结构与布局控件的映射关系

3. 菜单系统架构
选项菜单：XML定义，分组管理，单选支持

上下文菜单：ActionMode模式，多选操作

菜单生命周期：onCreateOptionsMenu、onPrepareOptionsMenu、onOptionsItemSelected

4. 通知系统集成
通知渠道：Android O+兼容性处理

通知构建：NotificationCompat.Builder链式调用

PendingIntent：通知点击行为定义

四、项目亮点与扩展方向
1. 项目亮点
技术覆盖全面：涵盖了Android开发中最重要的高级UI组件

代码示范性强：每个功能模块都提供完整的实现代码

交互体验优秀：丰富的用户反馈和直观的操作流程

架构设计清晰：模块化设计，便于理解和扩展

2. 后续优化方向
数据持久化：集成Room数据库保存用户偏好和操作记录

网络集成：添加真实的登录API调用和数据同步

动画效果：增加页面切换和操作反馈动画

主题系统：支持暗黑模式和自定义主题

权限管理：添加通知权限等系统权限处理

3. 学习价值
本项目作为Android高级UI组件学习案例，具有重要教育意义：

完整的学习路径：从基础组件到高级交互的渐进式学习

实际应用场景：每个组件都有明确的使用场景和实现方案

最佳实践示范：展示了Android开发的标准模式和最佳实践

可扩展的基础：代码结构清晰，便于在此基础上进行功能扩展

4. 技术深度
组件生命周期管理：深入理解Activity、菜单、ActionMode的生命周期

事件处理机制：掌握多种事件监听器的使用场景和区别

UI线程优化：学习在UI线程中处理复杂操作的最佳实践

兼容性处理：了解不同Android版本的适配策略

代码原址:https://github.com/L-part05/test3


