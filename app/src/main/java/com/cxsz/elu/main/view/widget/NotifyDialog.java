package com.cxsz.elu.main.view.widget;


import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cxsz.elu.R;
import com.cxsz.framework.base.BaseDialogFragment;
import com.cxsz.framework.tool.LoggerUtil;

public class NotifyDialog extends BaseDialogFragment {
    private final String tag = "NotifyDialog";
    private TextView mTitle;
    private TextView mContent;
    private ProgressBar mProgressBar;
    private TextView mNegativeButtonText;
    private TextView mConfirmButtonText;
    private TextView mPositiveButtonText;
    private View mDividerView;
    private CheckBox mCheckBox;
    private final static String KEY_CHECK_BOX = "check_box";
    private int mProgressBarStyle = 0;
    public final static int PB_STYLE_ROUND = 1;
    public final static int PB_STYLE_HORIZONTAL = 2;

    private Bundle bundle;
    private boolean isLeftGravity = false;

    private OnConfirmClickListener mOnConfirmClickListener;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(boolean isChecked);
    }

    public interface OnConfirmClickListener {
        void onClick();
    }

    private OnNegativeClickListener mOnNegativeClickListener;

    public interface OnNegativeClickListener {
        void onClick();
    }

    private OnPositiveClickListener mOnPositiveClickListener;

    public interface OnPositiveClickListener {
        void onClick();
    }

    private OnDismissDialogListener mOnDismissDialogListener;

    public interface OnDismissDialogListener{
        void onDismiss();
    }

    public NotifyDialog() {

    }


    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    private int mTitleId = 0, mContentId = 0, mNegativeButtonId = 0, mConfirmButtonId = 0, mPositiveButtonId = 0;
    private String title = "", content = "";
    private boolean isShowCheckBox = false;

    public static NotifyDialog newInstance(int titleId, int progressBarStyle, int contentId) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        args.putInt("mProgressBarStyle", progressBarStyle);
        args.putInt("mContentId", contentId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.setArguments(args);
        return fragment;
    }
    public static NotifyDialog newInstance(int titleId, boolean showProgressBar, int contentId) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        if (showProgressBar)
            args.putInt("mProgressBarStyle", PB_STYLE_ROUND);
        else
            args.putInt("mProgressBarStyle", 0);
        args.putInt("mContentId", contentId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.setArguments(args);
        return fragment;
    }
    public static NotifyDialog newInstance(int titleId, boolean showProgressBar) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        if (showProgressBar)
            args.putInt("mProgressBarStyle", PB_STYLE_ROUND);
        else
            args.putInt("mProgressBarStyle", 0);
        NotifyDialog fragment = new NotifyDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(String title, boolean showProgressBar, String content) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", -1);
        args.putString("title", title);
        if (showProgressBar)
            args.putInt("mProgressBarStyle", PB_STYLE_ROUND);
        else
            args.putInt("mProgressBarStyle", 0);
        args.putString("content", content);
        args.putInt("mContentId", -1);
        NotifyDialog fragment = new NotifyDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(int titleId, boolean showProgressBar, int contentId, int confirmTextId, OnConfirmClickListener listener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        if (showProgressBar)
            args.putInt("mProgressBarStyle", PB_STYLE_ROUND);
        else
            args.putInt("mProgressBarStyle", 0);
        args.putInt("mContentId", contentId);
        args.putInt("mConfirmButtonId", confirmTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnConfirmClickListener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(int titleId, int contentId, int confirmTextId, OnConfirmClickListener listener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        args.putInt("mContentId", contentId);
        args.putInt("mConfirmButtonId", confirmTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnConfirmClickListener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(String title, String content, int confirmTextId, OnConfirmClickListener listener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", -1);
        args.putString("title", title);
        args.putInt("mContentId", -1);
        args.putString("content", content);
        args.putInt("mConfirmButtonId", confirmTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnConfirmClickListener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(int titleId, int contentId, int negativeTextId, int positiveTextId,
                                           OnNegativeClickListener negativeListener, OnPositiveClickListener positiveListener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        args.putInt("mContentId", contentId);
        args.putInt("mNegativeButtonId", negativeTextId);
        args.putInt("mPositiveButtonId", positiveTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnNegativeClickListener = negativeListener;
        fragment.mOnPositiveClickListener = positiveListener;
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(String title, String content, int negativeTextId, int positiveTextId,
                                           OnNegativeClickListener negativeListener, OnPositiveClickListener positiveListener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", -1);
        args.putString("title", title);
        args.putInt("mContentId", -1);
        args.putString("content", content);
        args.putInt("mNegativeButtonId", negativeTextId);
        args.putInt("mPositiveButtonId", positiveTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnNegativeClickListener = negativeListener;
        fragment.mOnPositiveClickListener = positiveListener;
        fragment.setArguments(args);
        return fragment;
    }

    public static NotifyDialog newInstance(int titleId, int contentId, boolean isShowCheckBox, int negativeTextId, int positiveTextId,
                                           OnNegativeClickListener negativeListener, OnPositiveClickListener positiveListener) {
        Bundle args = new Bundle();
        args.putInt("mTitleId", titleId);
        args.putBoolean(KEY_CHECK_BOX, isShowCheckBox);
        args.putInt("mContentId", contentId);
        args.putInt("mNegativeButtonId", negativeTextId);
        args.putInt("mPositiveButtonId", positiveTextId);
        NotifyDialog fragment = new NotifyDialog();
        fragment.mOnNegativeClickListener = negativeListener;
        fragment.mOnPositiveClickListener = positiveListener;
        fragment.setArguments(args);
        return fragment;
    }

    public void setContentTextLeft(boolean isLeftGravity) {
        this.isLeftGravity = isLeftGravity;
    }

    public void setContent(int contentId) {
        mContentId = contentId;
        if (mContent != null)
            mContent.setText(mContentId);
    }

    public void setContent(String content) {
        mContentId = -1;
        this.content = content;
        if (mContent != null)
            mContent.setText(content);
    }

    public void setProgressBarVisibility(int visibility) {
        if (mProgressBar != null)
            mProgressBar.setVisibility(visibility);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
        setCancelable(false);

        Bundle args = getArguments();
        if (args != null) {
            mProgressBarStyle = args.getInt("mProgressBarStyle", 0);
            isShowCheckBox = args.getBoolean(KEY_CHECK_BOX, false);
            mContentId = args.getInt("mContentId");
            content = args.getString("content");
            mConfirmButtonId = args.getInt("mConfirmButtonId");
            mTitleId = args.getInt("mTitleId");
            title = args.getString("title");
            mNegativeButtonId = args.getInt("mNegativeButtonId");
            mPositiveButtonId = args.getInt("mPositiveButtonId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Dbug.d(tag, "onCreateView:..........");
        View view = inflater.inflate(R.layout.notify_dialog, container, false);
        if (getDialog() != null) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        mTitle = (TextView) view.findViewById(R.id.tv_title);
        mContent = (TextView) view.findViewById(R.id.tv_content);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mNegativeButtonText = (TextView) view.findViewById(R.id.tv_left);
        mConfirmButtonText = (TextView) view.findViewById(R.id.tv_middle);
        mPositiveButtonText = (TextView) view.findViewById(R.id.tv_right);
        mDividerView = view.findViewById(R.id.divider_id);
        mCheckBox = (CheckBox) view.findViewById(R.id.checkbox);
        mCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        View line = view.findViewById(R.id.line_id);
        mContent.setMovementMethod(ScrollingMovementMethod.getInstance());

        if (mTitleId != 0) {
//            mProgressBar.setVisibility(View.GONE);
            if (mTitleId == -1) {
                mTitle.setText(title);
            } else {
                mTitle.setText(getResources().getString(mTitleId));
            }
        }

        if (isShowCheckBox) {
            mCheckBox.setVisibility(View.VISIBLE);
        } else {
            mCheckBox.setVisibility(View.GONE);
        }
        if (mProgressBarStyle > 0) {
            if (mProgressBarStyle == PB_STYLE_ROUND) {
                mProgressBar.setVisibility(View.VISIBLE);
            } else if (mProgressBarStyle == PB_STYLE_HORIZONTAL) {
                mProgressBar.setVisibility(View.GONE);
            }
        } else {
            mProgressBar.setVisibility(View.GONE);
        }

        if (mContentId != 0) {
            mContent.setVisibility(View.VISIBLE);
            if (mContentId == -1) {
                mContent.setText(content);
            } else {
                mContent.setText(getResources().getString(mContentId));
            }
            if (isLeftGravity) {
                mContent.setGravity(Gravity.LEFT);
            } else {
                mContent.setGravity(Gravity.CENTER);
            }
        } else {
            mContent.setVisibility(View.GONE);
        }

        if (mNegativeButtonId != 0) {
            mNegativeButtonText.setVisibility(View.VISIBLE);
            mNegativeButtonText.setText(getResources().getString(mNegativeButtonId));
        } else {
            mNegativeButtonText.setVisibility(View.GONE);
        }
        if (mConfirmButtonId != 0) {
            line.setVisibility(View.VISIBLE);
            mDividerView.setVisibility(View.GONE);
            mConfirmButtonText.setVisibility(View.VISIBLE);
            mConfirmButtonText.setText(getResources().getString(mConfirmButtonId));
        } else {
            mConfirmButtonText.setVisibility(View.GONE);

            if (mProgressBarStyle > 0) {
                line.setVisibility(View.GONE);
                mDividerView.setVisibility(View.GONE);
            } else {
                line.setVisibility(View.VISIBLE);
                mDividerView.setVisibility(View.VISIBLE);
            }
        }

        if (mPositiveButtonId != 0) {
            mPositiveButtonText.setVisibility(View.VISIBLE);
            mPositiveButtonText.setText(getResources().getString(mPositiveButtonId));
        } else {
            mPositiveButtonText.setVisibility(View.GONE);
        }

        mConfirmButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnConfirmClickListener != null) {
                    mOnConfirmClickListener.onClick();
                }
            }
        });

        mNegativeButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnNegativeClickListener != null) {
                    mOnNegativeClickListener.onClick();
                }
            }
        });

        mPositiveButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPositiveClickListener != null) {
                    mOnPositiveClickListener.onClick();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoggerUtil.i(tag, "onActivityCreated.............:");
        if (getDialog() == null || getDialog().getWindow() == null) return;
        final WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();

        params.width = 100;
        params.height = 50;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params.width = displayMetrics.heightPixels * 4 / 5;
            params.height = displayMetrics.heightPixels * 3 / 4;
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            params.width = displayMetrics.widthPixels * 4 / 5;
            params.height = displayMetrics.widthPixels * 3 / 4;
        }
        params.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(params);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (isShowCheckBox)
            mCheckBox.setChecked(false);
        if(mOnDismissDialogListener != null){
            mOnDismissDialogListener.onDismiss();
        }
    }

    public void setOnDismissDialogListener(OnDismissDialogListener listener){
        mOnDismissDialogListener = listener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged(isChecked);
            }
        }
    };
}
