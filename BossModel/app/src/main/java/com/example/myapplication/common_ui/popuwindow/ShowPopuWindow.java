package com.example.myapplication.common_ui.popuwindow;

import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;

public class ShowPopuWindow extends PopupWindow {

    public ShowPopuWindow(View contentView, int width, int height, boolean focusable){
        super(contentView,width,height,focusable);
    }
    @Override
    public void showAsDropDown(View anchor) {
        if(Build.VERSION.SDK_INT >= 24){
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom+85;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    public void initListenter(){
        this.setTouchable(true);

        this.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });
    }
}
