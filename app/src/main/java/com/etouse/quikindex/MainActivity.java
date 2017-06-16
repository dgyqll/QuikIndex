package com.etouse.quikindex;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements QuikIndexBar.OnLetterClickListener {

    private QuikIndexBar quikIndexBar;
    private ListView listView;
    private List<LiangShanBean> mDatas = new ArrayList<>();
    private QuikAdapter adapter;
    private TextView tvHintLetter;
    private boolean isStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        tvHintLetter = (TextView) findViewById(R.id.tv_hint_letter);
        quikIndexBar = (QuikIndexBar) findViewById(R.id.quikIndexBar);
        quikIndexBar.setOnLetterClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new QuikAdapter(mDatas,MainActivity.this);
        listView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String[] array = getResources().getStringArray(R.array.liangshan_man);
        for (int i = 0; i < array.length; i++) {
            String pinYin = PinYinUtils.getPinYinString(array[i]);
            LiangShanBean liangShanBean = new LiangShanBean(pinYin.charAt(0) + "", array[i]);
            mDatas.add(liangShanBean);
        }
        //排序
        Collections.sort(mDatas);
        //刷新数据
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLetterClick(String letter) {
        //设置提示字母
        tvHintLetter.setText(letter);

        //添加缩放动画，放大
        if (isStart == false) {
            ViewCompat.animate(tvHintLetter).scaleX(1).scaleY(1).setDuration(200).setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {
                    isStart = true;
                }

                @Override
                public void onAnimationEnd(View view) {
                    isStart = false;
                }

                @Override
                public void onAnimationCancel(View view) {

                }
            }).start();
        }


        //定时执行缩放动画，缩放为0
        tvHintLetter.removeCallbacks(runnable);
        tvHintLetter.postDelayed(runnable, 2000);


        //遍历集合，滚动到指定索引处
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getLetter().equals(letter)) {
                listView.setSelection(i);
                return;
            }
        }
    }

    //缩放任务
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ViewCompat.animate(tvHintLetter).scaleX(0).scaleY(0).setDuration(200).start();
        }
    };
}
