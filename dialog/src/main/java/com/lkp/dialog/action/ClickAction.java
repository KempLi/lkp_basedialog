package com.lkp.dialog.action;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

/**
 * @Author: KempLi
 * @Date: 2023/12/1 15:53
 * @Description: 点击事件意图
 */
public interface ClickAction extends View.OnClickListener {

    /*就是想使用viewbing*/

//    <V extends View> V findViewById(@IdRes int id);

//    default void setOnClickListener(@IdRes int... ids) {
//        setOnClickListener(this, ids);
//    }
//
//    default void setOnClickListener(@Nullable View.OnClickListener listener, @IdRes int... ids) {
//        for (int id : ids) {
//            findViewById(id).setOnClickListener(listener);
//        }
//    }

    default void setOnClickListener(View... views) {
       setOnClickListener(this, views);
    }

    default void setOnClickListener(@Nullable View.OnClickListener listener, View... views) {
        for (View view : views) {
            view.setOnClickListener(listener);
        }
    }

    @Override
    default void onClick(View view) {
        // 默认不实现，让子类实现
    }
}