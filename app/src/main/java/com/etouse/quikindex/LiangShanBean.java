package com.etouse.quikindex;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/6/16.
 */

public class LiangShanBean implements Comparable<LiangShanBean>{
    private String letter;
    private String name;

    public LiangShanBean(String letter, String name) {
        this.letter = letter;
        this.name = name;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {

        return letter;
    }

    public String getName() {
        return name;
    }


    @Override
    public int compareTo(@NonNull LiangShanBean liangShanBean) {
        return letter.compareTo(liangShanBean.getLetter());
    }
}

