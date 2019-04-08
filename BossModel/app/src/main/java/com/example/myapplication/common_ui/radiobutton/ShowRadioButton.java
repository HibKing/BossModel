package com.example.myapplication.common_ui.radiobutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class ShowRadioButton extends android.support.v7.widget.AppCompatRadioButton {


    public ShowRadioButton(Context context) {
        super(context);
    }

    public ShowRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup) getParent()).clearCheck();
        }
    }
}
