package com.lkp.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.lkp.dialog.databinding.UiDialogBinding;


/**
 * @Author KempLi
 * @DateTime 2023/12/5 13:25
 * @Descipte 项目通用 Dialog 布局封装
 */
public final class CommonDialog {

    @SuppressWarnings("unchecked")
    public static class Builder<B extends Builder<?>>
            extends BaseDialog.Builder<B> {

        @Nullable
        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private UiDialogBinding binding;

        public Builder(Context context) {
            super(context);
            binding = UiDialogBinding.inflate(LayoutInflater.from(context), new FrameLayout(context), false);
            setContentView(binding.getRoot());
            setAnimStyle(BaseDialog.ANIM_IOS);
            setGravity(Gravity.CENTER);

            setOnClickListener(binding.tvUiCancel, binding.tvUiConfirm);
        }

        public B setCustomView(View view) {
            binding.llUiContainer.addView(view, 1);
            return (B) this;
        }

        public B setTitle(@StringRes int id) {
            return setTitle(getString(id));
        }

        public B setTitle(CharSequence text) {
            binding.tvUiTitle.setText(text);
            return (B) this;
        }

        public B setCancel(@StringRes int id) {
            return setCancel(getString(id));
        }

        public B setCancel(CharSequence text) {
            binding.tvUiCancel.setText(text);
            binding.vUiLine.setVisibility((text == null || "".equals(text.toString())) ? View.GONE : View.VISIBLE);
            return (B) this;
        }

        public B setConfirm(@StringRes int id) {
            return setConfirm(getString(id));
        }

        public B setConfirm(CharSequence text) {
            binding.tvUiConfirm.setText(text);
            return (B) this;
        }

        public B setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return (B) this;
        }

        public void autoDismiss() {
            if (mAutoDismiss) {
                dismiss();
            }
        }

        public void setCommonListener(OnListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            if (viewId == R.id.tv_ui_confirm) {
                autoDismiss();
                if (mListener == null) {
                    return;
                }
                mListener.onConfirm(getDialog());
            } else if (viewId == R.id.tv_ui_cancel) {
                autoDismiss();
                if (mListener == null) {
                    return;
                }
                mListener.onCancel(getDialog());
            }
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog);

        /**
         * 点击取消时回调
         */
        default void onCancel(BaseDialog dialog) {
        }
    }
}