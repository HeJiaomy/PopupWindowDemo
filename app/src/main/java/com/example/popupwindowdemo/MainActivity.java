package com.example.popupwindowdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText et_Input;
    ListView listView;
    List<String> datas;
    PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        popupWindow= new PopupWindow(listView,et_Input.getWidth(),500);
        //显示在指定控件下
        popupWindow.showAsDropDown(et_Input,0,-5);
        popupWindow.setFocusable(true);
        //设置点击外部区域，自动隐藏
//         popupWindow.setOutsideTouchable(true);
        //设置空的背景
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.update(); //必须popupWindow.setFocusable(true)和popupWindow.update()同时设置点击外部区域popupWindow才能自动隐藏
    }

    private void initListView() {
        listView= new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.mipmap.listview_background);
        datas= new ArrayList<>();
        for (int i=0; i<30; i++){
            datas.add(1000+i+"");
        }
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        et_Input.setText(datas.get(position));
        popupWindow.dismiss();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas== null?0:datas.size();
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
            if (convertView== null){
                view= View.inflate(parent.getContext(),R.layout.item_number,null);
            }else {
                view= convertView;
            }
            TextView tv_Number= view.findViewById(R.id.tv_number);
            tv_Number.setText(datas.get(position));
            view.findViewById(R.id.img_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                    if (datas.size()== 0){
                        popupWindow.dismiss();
                    }
                }
            });
            return view;
        }
    }
}
