package com.example.popupwindowdemo.binder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.popupwindowdemo.bean.BlogBean;
import com.example.popupwindowdemo.BlogItemListener;
import com.example.popupwindowdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.multitype.ItemViewBinder;

public class BlogViewBinder extends ItemViewBinder<BlogBean,BlogViewBinder.ViewHolder> {

    private BlogItemListener listener;
    public BlogViewBinder(BlogItemListener listener){
        this.listener= listener;
    }
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull BlogBean blogBean) {
        Context context=holder.circleImg.getContext();
        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527670339696&di=814f2d97facb082497ff3e2dbcd57e2f&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fb8014a90f603738d9c31f733b01bb051f819ecac.jpg")
                .apply(new RequestOptions().placeholder(R.mipmap.ic_head))
                .into(holder.circleImg);
        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528273433&di=8c2ab6ed998a3aa61074bea269bcd21b&imgtype=jpg&er=1&src=http%3A%2F%2Fimgstore.cdn.sogou.com%2Fapp%2Fa%2F100540002%2F654340.jpg")
                .apply(new RequestOptions().placeholder(R.mipmap.ic_one))
                .into(holder.imageContent1);
        Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527678867386&di=bea1de0355b5acd2e19e9798ac6671f2&imgtype=0&src=http%3A%2F%2Fimg.duoziwang.com%2F2017%2F09%2F1523354623996.jpg")
                .apply(new RequestOptions().placeholder(R.mipmap.ic_one))
                .into(holder.imageContent2);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circle_img)
        CircleImageView circleImg;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.image_content1)
        ImageView imageContent1;
        @BindView(R.id.image_content2)
        ImageView imageContent2;
        @BindView(R.id.ib_forward)
        ImageButton ibForward;
        @BindView(R.id.tv_forward)
        TextView tvForward;
        @BindView(R.id.ib_message)
        ImageButton ibMessage;
        @BindView(R.id.tv_message)
        TextView tvMessage;
        @BindView(R.id.ib_ok)
        ImageButton ibOk;
        @BindView(R.id.tv_ok)
        TextView tvOk;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
