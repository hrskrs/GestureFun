package com.hrskrs.gesturefunlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hrskrs
 */
public class DeleteOnSwipeEditText extends LinearLayoutCompat implements View.OnTouchListener {
    private static final int INVALID = -1;
    private static final String SUPER_STATE = "super.state";
    private static final String EDIT_TEXT_VALUE = "edit.text";
    private static final int NONE = 0;
    private static final int SWIPE = 1;

    private int mode = NONE;

    private float startX = 0;
    private float stopX = 0;
    //Threshold in pixels
    private int DEFAULT_THRESHOLD = 200;
    private int MIN_THRESHOLD = DEFAULT_THRESHOLD;
    //Single/Double swipe modes
    private int SINGLE_FINGER_SWIPE = 10;
    private int DOUBLE_FINGER_SWIPE = 20;
    private int DEFAULT_SWIPE = DOUBLE_FINGER_SWIPE;
    //When threshold is equal to width of EditText itself we add 100px padding
    private int THRESHOLD_PADDING = 100;

    private AppCompatEditText editText;

    public DeleteOnSwipeEditText(Context context) {
        this(context, null);
    }

    public DeleteOnSwipeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.layout_delete_on_swipe_edit_text, this);
        editText = (AppCompatEditText) getChildAt(0);
        //Threshold cannot be bigger than EditText itself
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DeleteOnSwipeEditText);
            setHint(ta.getString(R.styleable.DeleteOnSwipeEditText_android_hint));
            int threshold = ta.getInt(R.styleable.DeleteOnSwipeEditText_hrskrs_threshold, INVALID);
            if (threshold > MIN_THRESHOLD) {
                DEFAULT_THRESHOLD = threshold;
            }
            int swipeMode = ta.getInt(R.styleable.DeleteOnSwipeEditText_hrskrs_swipe_mode, INVALID);
            if (swipeMode == 1) {
                DEFAULT_SWIPE = SINGLE_FINGER_SWIPE;
            }
            ta.recycle();
        }
        //EditText touch listener
        editText.setOnTouchListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (DEFAULT_THRESHOLD > getWidth()) {
            DEFAULT_THRESHOLD = getWidth() - THRESHOLD_PADDING;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        Bundle state = new Bundle();
        state.putParcelable(SUPER_STATE, superState);
        state.putString(EDIT_TEXT_VALUE, editText.getText().toString());
        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle savedState = (Bundle) state;
        super.onRestoreInstanceState(savedState.getParcelable(SUPER_STATE));
        String savedString = savedState.getString(EDIT_TEXT_VALUE);
        if (TextUtils.isEmpty(savedString)) {
            editText.setText(savedState.getString(EDIT_TEXT_VALUE));
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray container) {
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray container) {
        super.dispatchThawSelfOnly(container);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //No of touches
        int pointerCount = event.getPointerCount();
        //If two finger touch
        if (pointerCount == 2 && DEFAULT_SWIPE == DOUBLE_FINGER_SWIPE) {
            int action = event.getActionMasked();
            switch (action) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    //Second finger touch
                    mode = NONE;
                    startX = event.getX();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    //Second finger released
                    if (Math.abs(stopX - startX) > DEFAULT_THRESHOLD && mode == SWIPE) {
                        deleteAllText();
                    }
                    mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = event.getX();
                    if (Math.abs(stopX - startX) > DEFAULT_THRESHOLD) {
                        mode = SWIPE;
                    }
                    break;
                default:
                    break;
            }
        } else if (pointerCount == 1 && DEFAULT_SWIPE == SINGLE_FINGER_SWIPE) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    //Single finger touch
                    mode = NONE;
                    startX = event.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    //Finger released
                    if (Math.abs(stopX - startX) >= DEFAULT_THRESHOLD && mode == SWIPE) {
                        deleteAllText();
                    }
                    mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    stopX = event.getX();
                    if (Math.abs(stopX - startX) >= DEFAULT_THRESHOLD) {
                        mode = SWIPE;
                    }
                    break;
                default:
                    break;
            }
        }

        return super.onTouchEvent(event);
    }

    public AppCompatEditText getEditText() {
        return editText;
    }

    private void deleteAllText() {
        setText("");
    }

    public void setHint(String hint) {
        editText.setHint(hint);
    }

    public CharSequence getHint() {
        return editText.getHint();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public CharSequence getText() {
        return editText.getText();
    }

    public void setError(CharSequence error) {
        editText.setError(error);
    }

    public CharSequence getError() {
        return editText.getError();
    }

    public void setBackgroundColor(int color) {
        editText.setBackgroundColor(color);
    }

    public void setBackgroundResource(int resId) {
        editText.setBackgroundResource(resId);
    }

    public void setTextColor(int color) {
        editText.setTextColor(color);
    }

    public void setLayoutParams(LayoutParams params) {
        editText.setLayoutParams(params);
    }

    public ViewGroup.LayoutParams getLayputParams() {
        return editText.getLayoutParams();
    }

    public void setSwipeMode(int swipeMode) {
        if (swipeMode == 1) {
            DEFAULT_SWIPE = SINGLE_FINGER_SWIPE;
        } else {
            DEFAULT_SWIPE = DOUBLE_FINGER_SWIPE;
        }
    }

    public int getSwipeMode() {
        if (DEFAULT_SWIPE == SINGLE_FINGER_SWIPE) {
            return 1;
        }
        return 2;
    }

    public void setThreshold(int threshold) {
        if (threshold > MIN_THRESHOLD) {
            DEFAULT_THRESHOLD = threshold;
        }
    }

    public int getThreshold() {
        return DEFAULT_THRESHOLD;
    }
}
