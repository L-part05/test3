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