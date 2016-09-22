package com.example.resources.popupwin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.resources.R;

/**
 * 实现一个弹出框，可以使用任意布局的View作为其内容
 * 这个弹出框是悬浮在当前activity之上的
 *@Author Administrator
 *@Time 2016-3-14 下午11:16:05
 */
public class PopupWindowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popupwin);
	}
	
	public void showPopupWindow(View view)
	{
		//自定义一个布局
		View contentView = LayoutInflater.from(this).inflate(R.layout.pop_win, null);
		
		Button button = (Button) contentView.findViewById(R.id.btn_popup);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(PopupWindowActivity.this, "点击了按键", Toast.LENGTH_SHORT).show();
			}
		});
		
		PopupWindow popupWindow = new PopupWindow(contentView,
				400,  300, true);
		
		popupWindow.setTouchable(true);
		
		/**
		 * dispatchTouchEvent--是否分发Touch事件，
		 * 		--返回true ，则交给这个view的onTouchEvent处理
		 * 		--返回 false ，则交给这个 view 的 interceptTouchEvent 方法来决定是否要拦截这个事件
		 * onInterceptTouchEvent--是否拦截Touch事件
		 * 		--返回 true ，也就是拦截掉了，则交给它的 onTouchEvent 来处理
		 * 		--返回 false ，那么就传递给子 view
		 * onTouchEvent--处理Touch事件
		 */
		popupWindow.setTouchInterceptor(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
				// 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});
		
		// 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
		popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
//		popupWindow.showAsDropDown(view,0, 0, Gravity.CENTER);
		popupWindow.showAtLocation(view, Gravity.CENTER, 100, 0);
				popupWindow.showAsDropDown(view);
	}
}
