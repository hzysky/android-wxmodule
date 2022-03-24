package com.hltx.wxpushdemo.animationStudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.AdaptScreenUtils;

import androidx.annotation.Nullable;

/**
 * @author leeskyyou
 * @description:
 * @date : 2022/3/23 6:00 下午
 */
public class WmprocessView extends View {

    public Paint paint;
    public Integer changeWidth;

    public WmprocessView(Context context) {
        super(context);
        initpaint();
    }

    public WmprocessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initpaint();
    }

    public WmprocessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpaint();
    }

    public WmprocessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initpaint();
    }

    public void  initpaint(){
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        changeWidth = AdaptScreenUtils.pt2Px(100F);
        Log.i("得到初始px",changeWidth+"");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Integer width =  getWidth();  //px
        Integer height = getHeight();
        Integer rd;

        Integer dp2 = AdaptScreenUtils.px2Pt(width.floatValue());

        Log.i("得到的参数值--","height--------" + height);

        //生成三个求
        if (height > changeWidth){
            rd = 9;
            canvas.drawCircle(width/2,40,rd ,paint);
            Integer left = width/2 -  (height-changeWidth);
            Integer right = width/2 +  (height-changeWidth);
            Log.i("得到的参数值--","height--------" + height + "left--->"+left + "right--->" + right);
            canvas.drawCircle(left.floatValue(),40,rd,paint);
            canvas.drawCircle(right.floatValue(),40,rd,paint);
        }else{
            rd = 1 + height/20;
            canvas.drawCircle(width/2,40,rd ,paint);
        }
    }


}
