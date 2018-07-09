package com.jj.edittextex.editText;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.jj.edittextex.R;

/**
 * Created by Administrator on 2018/7/6.
 */

public class EditTextEx extends AppCompatEditText {
    private Drawable mRightIcon;//右侧图标
    private boolean mIsPwdModel;//是否密码模式
    private int mDrawablePadding = 10;
    private boolean eyeIsClick;
    public EditTextEx(Context context) {
        super(context);
        initView(null);
    }

    public EditTextEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public EditTextEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        rect.left = rect.right-58;
        if (mIsPwdModel && event.getAction() == MotionEvent.ACTION_UP && rect.contains(eventX, eventY)){
            if (eyeIsClick == true){
                setIcon(getContext().getResources().getDrawable(R.mipmap.icon_03));
                setInputType(InputType.TYPE_CLASS_TEXT);
                eyeIsClick = false;
            }else {
                this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                setIcon(getContext().getResources().getDrawable(R.mipmap.about));
                eyeIsClick = true;
            }
            setSelection(getText().length());
        }else {//清空模式
            if (event.getAction() == MotionEvent.ACTION_UP && rect.contains(eventX,eventY)){
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    private void initView(AttributeSet attrs) {
        if (attrs == null)
            return;
        /**
         * 清空密码模式
         */
        if (pwdModel(attrs))
            return;
        /**
         * 清空文本模式
         */
        clearTextModel();

    }

    private boolean pwdModel(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.EditTextS_RES);
        mIsPwdModel = typedArray.getBoolean(R.styleable.EditTextS_RES_right_is_pwd,false);
        mDrawablePadding = typedArray.getInteger(R.styleable.EditTextS_RES_right_padding,R.styleable.EditTextS_RES_right_padding);
        if (mIsPwdModel){
            mRightIcon = getContext().getResources().getDrawable(R.mipmap.about);
            setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            setCompoundDrawablesWithIntrinsicBounds(null, null, mRightIcon, null);
            setCompoundDrawablePadding(mDrawablePadding);
            return true;
        }
        return false;
    }

    private void clearTextModel() {
        mRightIcon = getContext().getResources().getDrawable(R.drawable.clear_24dp);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                initDelIcon();
            }
        });
        initDelIcon();

    }

    private void initDelIcon() {
        if (length()<1){
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }else{
            setIcon(mRightIcon);
        }
    }

    private void setIcon(Drawable mIcon) {
        setCompoundDrawablesWithIntrinsicBounds(null,null,mIcon,null);
        setCompoundDrawablePadding(mDrawablePadding);
    }
}
