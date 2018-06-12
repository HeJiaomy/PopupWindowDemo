package com.example.popupwindowdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popupwindowdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText et_Input;
    ListView listView;
    List<String> datas;
    PopupWindow popupWindow;
    @BindView(R.id.goBlog_btn)
    Button goBlogBtn;
    @BindView(R.id.go_qq)
    Button goQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        et_Input = findViewById(R.id.et_input);
        findViewById(R.id.ib_dropdown).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showPopupWindow();
    }

    private void showPopupWindow() {
        initListView();
        //显示下拉框
        popupWindow = new PopupWindow(listView, et_Input.getWidth(), 500);
        //显示在指定控件下
        popupWindow.showAsDropDown(et_Input, 0, -5);
        popupWindow.setFocusable(true);
        //设置点击外部区域，自动隐藏----此方法无效
//         popupWindow.setOutsideTouchable(true);
        //设置空的背景
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update(); //必须popupWindow.setFocusable(true)和popupWindow.update()同时设置点击外部区域popupWindow才能自动隐藏
    }

    private void initListView() {
        listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.mipmap.listview_background);
        datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add(1000 + i + "");
        }
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        et_Input.setText(datas.get(position));
        popupWindow.dismiss();
    }


    @OnClick({R.id.goBlog_btn, R.id.go_qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goBlog_btn:
                Intent intent = new Intent(this, BlogActivity.class);
                startActivity(intent);
                break;
            case R.id.go_qq:
                if (isQQClientAvailable(this)) {
                    String url = "mqqwpa://im/chat?chat_type=wpa&uin=123456";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }else {
                    Toast.makeText(this,"没有找到QQ客户端",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 判断手机内是否装了手机QQ
     */
    public boolean isQQClientAvailable(Context context){
        PackageManager packageManager= context.getPackageManager();
        try {
            packageManager.getPackageInfo("com.tencent.mobileqq",0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(parent.getContext(), R.layout.item_number, null);
            } else {
                view = convertView;
            }
            TextView tv_Number = view.findViewById(R.id.tv_number);
            tv_Number.setText(datas.get(position));
            view.findViewById(R.id.img_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                    if (datas.size() == 0) {
                        popupWindow.dismiss();
                    }
                }
            });
            return view;
        }
    }
}
