package com.nela.mvpdemo.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LetterIndexView extends View {

    public interface OnTouchingLetterChangedListener {

        void onHit(String letter);

        void onCancel();
    }

    private OnTouchingLetterChangedListener mTouchingLetterChangedListener;

    private Paint mPaint;

    private boolean mHit;

    private List<String> mLetters = new ArrayList<>();

    private final int WIDTH = 60;
    private final int TEXT_SIZE = 24;
    private final int TEXT_COLOR = Color.parseColor("#565656");
    private final int TEXT_SEL_COLOR = Color.parseColor("#3F51B5");
    private final int HIT_BACKGROUND_COLOR = Color.parseColor("#CCCCCC");
    private String mCurLetter;

    public LetterIndexView(Context context) {
        this(context, null);
    }

    public LetterIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        setVisibility(INVISIBLE);
    }

    /**
     * 设置字母集
     *
     * @param letters
     */
    public void setupLetters(List<String> letters) {
        if (letters == null || letters.size() == 0) {
            setVisibility(INVISIBLE);
        } else {
            setVisibility(VISIBLE);
            mLetters = letters;
            if (letters.size() > 0) {
                if (TextUtils.isEmpty(mCurLetter)) {
                    mCurLetter = letters.get(0);
                } else {
                    if (!mLetters.contains(mCurLetter)) {
                        mCurLetter = mLetters.get(0);
                    }
                }
            }
            invalidate();
        }
    }

    /**
     * 设置当前字母
     *
     * @param letter
     */
    public void setCurLetter(String letter) {
        mCurLetter = letter;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = TEXT_SIZE * mLetters.size() * 2 + TEXT_SIZE * 2;
        setMeasuredDimension(WIDTH, height);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHit = true;
                onHit(event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                onHit(event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mHit = false;
                if (mTouchingLetterChangedListener != null) {
                    mTouchingLetterChangedListener.onCancel();
                }
                break;
            default:
                bringToFront();
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mHit) {
            mPaint.setStrokeWidth(WIDTH);
            mPaint.setColor(HIT_BACKGROUND_COLOR);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            canvas.drawLine(WIDTH / 2, WIDTH / 2, WIDTH / 2, getHeight() - WIDTH / 2, mPaint);
        }

        mPaint.setTextSize(TEXT_SIZE);
        for (int i = 0; i < mLetters.size(); i++) {
            if (TextUtils.equals(mLetters.get(i), mCurLetter)) {
                mPaint.setColor(TEXT_SEL_COLOR);
            } else {
                mPaint.setColor(TEXT_COLOR);
            }
            canvas.drawText(mLetters.get(i), WIDTH / 2, TEXT_SIZE * i * 2 + TEXT_SIZE + TEXT_SIZE, mPaint);
        }
    }

    private void onHit(float offset) {
        if (mHit && mTouchingLetterChangedListener != null) {
            int index = (int) (offset - TEXT_SIZE) / TEXT_SIZE / 2;
            index = Math.max(index, 0);
            index = Math.min(index, mLetters.size() - 1);
            String letter = mLetters.get(index);
            if (!TextUtils.equals(letter, mCurLetter)) {
                mCurLetter = letter;
                invalidate();
            }
            mTouchingLetterChangedListener.onHit(mCurLetter);
        }
    }

    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.mTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }
}