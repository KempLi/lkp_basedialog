package com.lkp.lkp_basedialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.lkp.dialog.BaseDialog;
import com.lkp.dialog.CommonDialog;
import com.lkp.lkp_basedialog.databinding.MessageDialogBinding;


/**
 * @Author KempLi
 * @DateTime 2023/12/5 13:15
 * @Descripte 消息对话框
 */
public final class MessageDialog {

    public static final class Builder
            extends CommonDialog.Builder<Builder> {

        @Nullable
        private OnListener mListener;
        private MessageDialogBinding binding;

        public Builder(Context context) {
            super(context);
            binding = MessageDialogBinding.inflate(LayoutInflater.from(context));
            setCustomView(binding.getRoot());
        }

        public Builder setMessage(@StringRes int id) {
            return setMessage(getString(id));
        }

        public Builder setMessage(CharSequence text) {
            binding.tvMessageMessage.setText(text);
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        @Override
        public BaseDialog create() {
            // 如果内容为空就抛出异常
            if ("".equals(binding.tvMessageMessage.getText().toString())) {
                throw new IllegalArgumentException("Dialog message not null");
            }
            return super.create();
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