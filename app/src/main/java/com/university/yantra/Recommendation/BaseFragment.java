package com.university.yantra.Recommendation;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.university.yantra.viewactions.BaseViewAction;


/**
 * Created by Rishabh on 2/4/2018.
 */

public abstract class BaseFragment extends Fragment implements BaseViewAction {

    private View mRoot;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(getLayoutResId(),container,false);
        final Typeface mFont = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Roboto-Medium.ttf");
        final ViewGroup mContainer = (ViewGroup) mRoot.getRootView();


        this.setAppFont(mContainer, mFont);


        inOnCreateView(mRoot,container,savedInstanceState);

        return mRoot;
    }
    public static final void setAppFont(ViewGroup mContainer, Typeface mFont)
    {
        if (mContainer == null || mFont == null) return;

        final int mCount = mContainer.getChildCount();

        // Loop through all of the children.
        for (int i = 0; i < mCount; ++i)
        {
            final View mChild = mContainer.getChildAt(i);
            if (mChild instanceof TextView)
            {
                // Set the font if it is a TextView.
                ((TextView) mChild).setTypeface(mFont);
            }
            else if (mChild instanceof ViewGroup)
            {
                // Recursively attempt another ViewGroup.
                setAppFont((ViewGroup) mChild, mFont);
            }
        }
    }
    @LayoutRes
    public abstract int getLayoutResId();
    public void inOnCreateView(View root,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}