/**
 * 
 */
package com.example.resources.drawpic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.view.View;

/**
 * @author YC
 * @time 2016-3-17 下午2:54:55
 */
public class CustomView extends View{

	private Paint paint ;
//	设置XMode为SRC_IN
	private PorterDuffXfermode mPorterDuffXfermode = new PorterDuffXfermode(Mode.SRC_IN);
	
	public CustomView(Context context) {
		super(context);
		//无锯齿画笔
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.GREEN);
	}


	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setXfermode(mPorterDuffXfermode);
		paint.setColor(Color.RED);
		RectF rectF = new RectF(100, 100, 500, 500);
		canvas.drawRect(rectF, paint);
		paint.setColor(Color.GREEN);
		//三点钟方向为0度
		canvas.drawArc(rectF, 180, 180, false, paint);
		canvas.drawCircle(100, 100, 50, paint);
		canvas.drawCircle(100, 100, 45, paint);
//		canvas.drawRect(rectF, null);
		paint.setXfermode(null);
	}
}
