package com.gamerole.commom.adapter.slimadapter;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by linshuaibin on 22/12/2016.
 */
public interface IViewInjector<VI extends IViewInjector> {

    interface Action<V extends View> {
        void action(V view);
    }

    <T extends View> T findViewById(int id);

    VI tag(int id, Object object);

    VI text(int id, int res);
    int pos();

    VI text(int id, CharSequence charSequence);

    VI typeface(int id, Typeface typeface, int style);

    VI typeface(int id, Typeface typeface);

    VI textColor(int id, int color);
    VI textColor(int id, ColorStateList color);

    VI textSize(int id, int sp);

    VI alpha(int id, float alpha);
    VI hint(int id, CharSequence charSequence);

    VI image(int id, int res);
    VI imageCircle(int id, Object url);
    VI image(int id, String url);
    VI imageRound(int id, String url, int size);

    VI image(int id, Drawable drawable);

    VI background(int id, int res);
    VI backgroundColor(int id, int res);

    VI background(int id, Drawable drawable);

    VI visible(int id);

    VI invisible(int id);

    VI gone(int id);

    VI visibility(int id, int visibility);

    <V extends View> VI with(int id, Action<V> action);

    VI clicked(int id, View.OnClickListener listener);

    VI longClicked(int id, View.OnLongClickListener listener);

    VI enable(int id, boolean enable);

    VI enable(int id);

    VI disable(int id);

    VI checked(int id, boolean checked);
    VI checkChangeListener(int id, CheckBox.OnCheckedChangeListener checkedChangeListener);

    VI selected(int id, boolean selected);

    VI pressed(int id, boolean pressed);

    VI adapter(int id, RecyclerView.Adapter adapter);

    VI adapter(int id, Adapter adapter);

    VI layoutManager(int id, RecyclerView.LayoutManager layoutManager);

    ///COMMON VIEWGROUP
    VI addView(int id, View... views);

    VI addView(int id, View view, ViewGroup.LayoutParams params);

    VI removeAllViews(int id);

    VI removeView(int id, View view);
    <V extends View> VI itemView(Action<V> action);
}
