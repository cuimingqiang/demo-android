package cmq.learn.demo.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cmq.learn.demo.R;
import cmq.learn.demo.utils.ScreenUtils;

/**
 * Created by cuimingqiang on 15/12/24.
 */
public class CustomActionBar extends RelativeLayout{
    private TextView left,title,right;
    public CustomActionBar(Context context) {
        super(context);
        initView();
    }

    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView(){

        setBackgroundResource(R.drawable.border_white_shape);
        addTitle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    private void ifNeedAddLeft(){
        if(left!=null)return;
        left = new TextView(getContext());
        left.setGravity(Gravity.CENTER);
        left.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin = ScreenUtils.dp2px(10);
        addView(left, params);
    }
    private void addTitle(){
        title = new TextView(getContext());
        title.setGravity(Gravity.CENTER);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        title.setTextColor(0xdd000000);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
        addView(title, params);
    }
    private void ifNeedAddRight(){
        if(right!=null)return;
        right = new TextView(getContext());
        right.setGravity(Gravity.CENTER);
        right.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.rightMargin = ScreenUtils.dp2px(10);
        addView(right,params);
    }

    public CustomActionBar setLeftDefault(){
        setLeftText("取消");
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
        return this;
    }
    public CustomActionBar setLeftText(CharSequence text){
        ifNeedAddLeft();
        left.setText(text);
        return this;
    }
    public CustomActionBar setLeftIcon(Drawable drawable){
        ifNeedAddLeft();
        left.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }
    public CustomActionBar setLeftIcon(int resId){
        ifNeedAddLeft();
        left.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        return this;
    }
    public CustomActionBar setLeftTextColor(int color){
        ifNeedAddLeft();
        left.setTextColor(color);
        return this;
    }
    public CustomActionBar setLeftTextColorRes(int resId){
        ifNeedAddLeft();
        ColorStateList list = getResources().getColorStateList(resId);
        left.setTextColor(list);
        return this;
    }

    public CustomActionBar setTitleText(CharSequence text){
        title.setText(text);
        return this;
    }
    public CustomActionBar setTitleTextColor(int color){
        title.setTextColor(color);
        return this;
    }
    public CustomActionBar setRightText(CharSequence text){
        ifNeedAddRight();
        right.setText(text);
        return this;
    }
    public CustomActionBar setRightTextColor(int color){
        ifNeedAddRight();
        right.setTextColor(color);
        return this;
    }
    public CustomActionBar setRightTextColorRes(int resId){
        ifNeedAddRight();
        ColorStateList color = getResources().getColorStateList(resId);
        right.setTextColor(color);
        return this;
    }
    public CustomActionBar setRightIcon(Drawable drawable){
        ifNeedAddRight();
        right.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }
    public CustomActionBar setRightIcon(int resId){
        ifNeedAddRight();
        right.setCompoundDrawablesWithIntrinsicBounds(0,0,resId,0);
        return this;
    }
}
