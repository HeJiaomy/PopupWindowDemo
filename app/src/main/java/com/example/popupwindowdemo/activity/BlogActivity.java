package com.example.popupwindowdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.popupwindowdemo.BlogItemListener;
import com.example.popupwindowdemo.R;
import com.example.popupwindowdemo.bean.BlogBean;
import com.example.popupwindowdemo.binder.BlogViewBinder;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class BlogActivity extends AppCompatActivity implements BlogItemListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Items items;
    private MultiTypeAdapter multiTypeAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);
        ButterKnife.bind(this);
        items= new Items();
        for (int i=0; i<20; i++) {
            items.add(new BlogBean());
        }
        multiTypeAdapter= new MultiTypeAdapter(items);
        multiTypeAdapter.register(BlogBean.class,new BlogViewBinder(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new SpaceItemDecoration(1,5,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(multiTypeAdapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(BlogActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void clickItem(Object object) {

    }
}
