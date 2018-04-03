package com.example.zyl.firtapp;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ScrollNestActivity extends AppCompatActivity {

    RecyclerView rv;
    private String[] ImgUrls = {"https://raw.githubusercontent.com/zylgg/StickyTabLayout/master/app/src/main/res/drawable/bg.jpg",
            "https://raw.githubusercontent.com/zylgg/StickyTabLayout/master/app/src/main/res/drawable/bg2.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_nest);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(true);
        rv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = getLayoutInflater().inflate(R.layout.item_layout, null);
            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final MyHolder myHolder= (MyHolder) holder;
            myHolder.vp.setAdapter(new MyVpAdapter());
            myHolder.vp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
                @Override
                public void onPageSelected(int position) {
                    myHolder.gv.setAdapter(new ArrayAdapter<String>(ScrollNestActivity.this,android.R.layout.simple_list_item_1,new String[]{"a"+position,"b"+position}));
                }
            });
            myHolder.gv.setAdapter(new ArrayAdapter<String>(ScrollNestActivity.this,android.R.layout.simple_list_item_1,new String[]{"a","b"}));
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        class MyHolder extends RecyclerView.ViewHolder {
            ViewPager vp;
            GridView gv;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                vp= (ViewPager) itemView.findViewById(R.id.vp);
                gv= (GridView) itemView.findViewById(R.id.gv);
            }
        }
    }
    private List<ImageView> titleIvs = new ArrayList<>();
    class MyVpAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            if (titleIvs.size() == 0) {
                imageView = new ImageView(ScrollNestActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = titleIvs.remove(titleIvs.size() - 1);
            }
            String imgUrl = ImgUrls[position];
            Picasso.with(ScrollNestActivity.this).load(imgUrl).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
            titleIvs.add((ImageView) object);
        }
    }
}
