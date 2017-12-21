package com.chiachen.debugapppractices.utils.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chiachen.debugapppractices.R;

/**
 * Created by jianjiacheng on 21/12/2017.
 */

public class PreferenceView extends FrameLayout {
    private static final int DEFAULT_LAYOUT = R.layout.item_preference_view;

    public static class Builder
    {
        private final Context mContext;
        private boolean 		mCheckMode;
        private CharSequence 	mTitle;
        private CharSequence 	mValue;
        private boolean			mSelected;

        private OnClickListener mOnClickListener;

        @LayoutRes
        private  int mRes = DEFAULT_LAYOUT;

        @DrawableRes
        private int mIconRes;

        private CharSequence getString(@StringRes int textId) {
            return mContext.getResources().getString(textId);
        }

        public Builder(Context context) {
            mContext = context ;
        }

        public Builder setCheckMode(boolean bCheck) {
            mCheckMode = bCheck;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setTitle(@StringRes int title) {
            mTitle = getString( title );
            return this;
        }

        public Builder setValue(String value) {
            mValue = value;
            return this;
        }

        public Builder setValue(@StringRes int value) {
            mValue = getString( value );
            return this;
        }

        public Builder setSelect(boolean select) {
            mSelected = select;
            return this;
        }

        public Builder setOnClickListener(OnClickListener listener) {
            mOnClickListener = listener;
            return this;
        }

        public Builder setLayout(@LayoutRes int layoutId) {
            mRes = layoutId;
            return this;
        }

        public Builder setIcon(@DrawableRes int icon) {
            mIconRes = icon;
            return this;
        }


        public PreferenceView build() {
            return new PreferenceView( this ) ;
        }
    }

    public PreferenceView(Context context) {
        super(context);
    }

    public PreferenceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public PreferenceView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PreferenceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public PreferenceView(@NonNull Builder builder) {
        super(builder.mContext);

        LayoutInflater mInflater = (LayoutInflater) builder.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(builder.mRes, this, true);

        if (builder.mTitle != null) {
            setVisibility(View.VISIBLE);
            TextView titleView = (TextView) findViewById(R.id.item_goto_left_text);
            titleView.setText(builder.mTitle);
        } else {
            setVisibility(View.GONE);
        }

        if (builder.mCheckMode) {
            View check = findViewById(R.id.item_check_image);
            if (check != null) {
                check.setVisibility(View.VISIBLE);
            }
        }

        if( builder.mIconRes != 0 )
            updateIconImage( builder.mIconRes ) ;

        if (builder.mOnClickListener != null)
            setOnClickListener(builder.mOnClickListener);

        if( builder.mValue != null )
            setValue( builder.mValue ) ;



        setSelected( builder.mSelected );
    }

    private void updateIconImage( int IconRes ) {
        ImageView iconView = (ImageView) findViewById(R.id.item_icon_image);
        if (iconView != null) {
            if (IconRes != 0) {
                iconView.setImageResource(IconRes);
                iconView.setVisibility(View.VISIBLE);
            } else {
                iconView.setVisibility(View.GONE);
            }
        }
    }

    public final void setValue(CharSequence text) {
        TextView textview = (TextView) findViewById(R.id.item_goto_right_text);
        if (textview != null)
            textview.setText(text);
    }

    public final CharSequence getValue() {
        TextView textview = (TextView) findViewById(R.id.item_goto_right_text);
        if (textview != null)
            return textview.getText();
        else
            return "";
    }

    public void setValueFromHtml(String text) {
        TextView textview = (TextView) findViewById(R.id.item_goto_right_text);
        if (textview != null)
            textview.setText(Html.fromHtml(text));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        View maskView = findViewById(R.id.item_disable_mask);
        if (maskView != null) {
            maskView.setVisibility(enabled ? View.GONE : View.VISIBLE);
        }
    }
}
