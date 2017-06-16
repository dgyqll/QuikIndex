package com.etouse.quikindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/6/16.
 */

public class QuikIndexBar extends View {
    private String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "H", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int width;
    private int height;
    private int avgHeight;
    private Paint mPaint;
    private OnLetterClickListener onLetterClickListener;

    public QuikIndexBar(Context context) {
        this(context,null);
    }

    public QuikIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QuikIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        avgHeight = height / letters.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLetters(canvas);
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawLetters(Canvas canvas) {
        for (int i = 0; i < letters.length; i++) {
            canvas.drawText(letters[i], width / 2, avgHeight * i + avgHeight / 2 + getTextHeight(letters[i]) / 2, mPaint);
        }
    }

    /**
     * 获取文字的高度
     * @param letter
     * @return
     */
    private int getTextHeight(String letter) {
        Rect rect = new Rect();
        mPaint.getTextBounds(letter,0,letter.length(),rect);
        return rect.width();
    }

    /**
     * 设置快速索引的触摸事件，返回相应的字母
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int index = (int) (event.getY() / avgHeight);
                if (index >= 0 && index < letters.length) {
                    if (onLetterClickListener != null)
                        onLetterClickListener.onLetterClick(letters[index]);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    public void setOnLetterClickListener(OnLetterClickListener onLetterClickListener){
        this.onLetterClickListener = onLetterClickListener;
    }
    public interface OnLetterClickListener{
        void onLetterClick(String letter);
    }
}
