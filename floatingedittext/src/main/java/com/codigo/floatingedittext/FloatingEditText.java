package com.codigo.floatingedittext;

/**
 * Created by Taufiq on 17/3/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author The Great 16 year old intern
 *         I have no clue how this works but oh well
 */

public class FloatingEditText extends LinearLayout {
    public boolean hasError = false;
    String bufferhint;

    public String mEditTextHint, mEditTextFont, mFloatingHintFont, mFloatingHintText;

    /**
     * @param et_mainTextEdit is the edittext label
     * @param tv_floatingHint is the floating hint above et_mainTextEdit
     **/
    public EditText et_mainTextEdit;
    public TextView tv_floatingHint;
    public LinearLayout ll_background;


    public float mEditTextSize, mFloatingHintSize;
    public boolean mHintIsAllCaps, mEditTextHasBackground, mIsPassword, mCondenseHint;
    public int mFloatingHintColor, mEditTextColor, mEditTextHintColor;
    public int mEditTextDrawable;

    public FloatingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FloatingTextLayout, 0, 0);
        try {
            mEditTextHint = a.getString(R.styleable.FloatingTextLayout_editTextHint);
            mHintIsAllCaps = a.getBoolean(R.styleable.FloatingTextLayout_floatingHintAllCaps, false);
            mEditTextSize = a.getFloat(R.styleable.FloatingTextLayout_editTextSize, 10.0f);
            mFloatingHintSize = a.getFloat(R.styleable.FloatingTextLayout_floatingHintSize, 12.0f);
            mEditTextHasBackground = a.getBoolean(R.styleable.FloatingTextLayout_editTextHasBackground, false);
            mEditTextDrawable = a.getResourceId(R.styleable.FloatingTextLayout_editTextBackgroundDrawable, 0);
            mFloatingHintColor = a.getColor(R.styleable.FloatingTextLayout_floatingHintColor, getResources().getColor(android.R.color.darker_gray));
            mEditTextColor = a.getColor(R.styleable.FloatingTextLayout_editTextTextColor, getResources().getColor(android.R.color.black));
            mEditTextHintColor = a.getColor(R.styleable.FloatingTextLayout_editTextHintColor, getResources().getColor(android.R.color.black));
            mEditTextFont = a.getString(R.styleable.FloatingTextLayout_editTextFont);
            mFloatingHintFont = a.getString(R.styleable.FloatingTextLayout_floatingHintFont);
            mIsPassword = a.getBoolean(R.styleable.FloatingTextLayout_isPassword, false);
            mFloatingHintText = a.getString(R.styleable.FloatingTextLayout_floatingHintText);
            mCondenseHint = a.getBoolean(R.styleable.FloatingTextLayout_isCondenseHint, false);
        } finally {
            a.recycle();
        }
        init();
    }

    public String getEditText() {
        return et_mainTextEdit.getText().toString();
    }

    public void setEditTextHint(String hint) {
        et_mainTextEdit.setHint(hint);
    }

    public String getmFloatingHintText() {
        return tv_floatingHint.getText().toString();
    }

    public void setmFloatingHintSize(float size) {
        tv_floatingHint.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * @param error      error to be shown
     * @param withPrefix if true, add current hint before error
     */
    public void setFloatingHintError(String error, boolean withPrefix) {
        tv_floatingHint.setText((withPrefix) ? Html.fromHtml(((mHintIsAllCaps) ? bufferhint.toUpperCase() : bufferhint) + " " + error) : Html.fromHtml(error));
        tv_floatingHint.setVisibility(VISIBLE);
        this.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
        hasError = true;
    }

    public void setEditTextFont(String path) {
        if (path != null)
            et_mainTextEdit.setTypeface(Typeface.createFromAsset(getContext().getAssets(), path));
    }

    public void setFloatingHintFont(String path) {
        if (path != null)
            tv_floatingHint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), path));
    }

    public void setEditTextBackground(int background) {
        et_mainTextEdit.setBackgroundResource(background);
    }
    public void stupidSetTextdoesntcallTextWatcher() {
        tv_floatingHint.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.move_up_and_fade_in));
        tv_floatingHint.setVisibility(VISIBLE);

        resetHint();
    }

    public void setPasswordTransformation(TransformationMethod method){
        et_mainTextEdit.setTransformationMethod(method);
    }


    private void init() {
        inflate(getContext(), R.layout.textlayout, this);

        et_mainTextEdit = (EditText) findViewById(R.id.EDIT_TEXT);
        tv_floatingHint = (TextView) findViewById(R.id.FLOATING_HINT);
        ll_background = (LinearLayout) findViewById(R.id.ll_background);


        if (mIsPassword) et_mainTextEdit.setTransformationMethod(new AsterisksPasswordTransformationMethod());

        setmFloatingHintSize(mFloatingHintSize);
        tv_floatingHint.setTextColor(mFloatingHintColor);
        et_mainTextEdit.setTextColor(mEditTextColor);
        et_mainTextEdit.setHintTextColor(mEditTextHintColor);
        if(mEditTextDrawable != 0)ll_background.setBackgroundResource(mEditTextDrawable);


        setFloatingHintFont(mFloatingHintFont);
        setEditTextFont(mEditTextFont);


        if(mEditTextHint != null){
            et_mainTextEdit.setHint(mEditTextHint);
        }else{
            et_mainTextEdit.setHint("Hint");
        }

        resetHint();

        tv_floatingHint.setVisibility(INVISIBLE);


        if(!mCondenseHint) {
            et_mainTextEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        if (et_mainTextEdit.getText().toString().isEmpty()) {
                            if (!hasError)
                                tv_floatingHint.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.move_up_and_fade_in));
                            tv_floatingHint.setVisibility(VISIBLE);
                        }
                    } else {
                        if (et_mainTextEdit.getText().toString().isEmpty()) {
                            tv_floatingHint.clearAnimation();
                            if (!hasError) tv_floatingHint.setVisibility(INVISIBLE);
                        }
                    }
                }
            });
        } else {
            tv_floatingHint.setVisibility(GONE);
            et_mainTextEdit.setSingleLine(false);
            et_mainTextEdit.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        tv_floatingHint.setVisibility(VISIBLE);
                    } else {
                        if(et_mainTextEdit.getText().toString().isEmpty()) {
                            if (!hasError) tv_floatingHint.setVisibility(GONE);
                        }
                    }
                }
            });
        }

        et_mainTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mCondenseHint) if (et_mainTextEdit.getText().toString().isEmpty()) {
                    tv_floatingHint.setVisibility(GONE);
                } else {
                    tv_floatingHint.setVisibility(VISIBLE);
                }
                resetHint();
                hasError = false;
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void resetHint() {
        bufferhint = (mFloatingHintText != null) ? mFloatingHintText : et_mainTextEdit.getHint().toString();
        tv_floatingHint.setText((mHintIsAllCaps) ? bufferhint.toUpperCase() : bufferhint);

    }


}