package com.example.resources.text;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resources.MainActivity;
import com.example.resources.R;
import com.example.resources.text.SpanUtils.SpanClickListener;

/**
 *@Author Administrator
 *@Time 2016-9-22 上午10:46:55
 */
public class TextViewLinkActivity extends Activity {

	private static final String TAG = "TextViewLinkActivity";
	private TextView mSpannableTextView;
	private TextView tvColoredKeywd;
	private TextView tvTopic;
	private TextView tvTestAt;
	private TextView tvExpression;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_spannable);
		mSpannableTextView = (TextView) findViewById(R.id.spannableTextView);
		
		SpannableString ss = new SpannableString(
				"字体测试" +
				"字体大小一半两倍" +
				"前景色背景色" +
				"正常粗体斜体粗斜体" +
				"下划线删除线x1x2" +
				"电话邮件网站短信彩信地图" +
				"X轴综合/bot");
		
		//设置字体
		ss.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//大小
		ss.setSpan(new AbsoluteSizeSpan(20), 4, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new AbsoluteSizeSpan(20,true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		ss.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new RelativeSizeSpan(2.0f), 10, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
		//设置前景色
		ss.setSpan(new ForegroundColorSpan(Color.YELLOW), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		//设置背景色
		ss.setSpan(new BackgroundColorSpan(Color.RED), 15, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//字体
		ss.setSpan(new StyleSpan(Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new StyleSpan(Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new StyleSpan(Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//下划线
		ss.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//删除线
		ss.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
		//上下标
		ss.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//超链接
		ss.setSpan(new URLSpan("tel:15820201347"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new URLSpan("mailto:yellowcgdut@163.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new URLSpan("http://baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new URLSpan("sms:10086"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new URLSpan("mms:10086"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		ss.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//X放大缩小
		ss.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//图片
		Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		ss.setSpan(new ImageSpan(drawable), 53, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//设置项目符号
		ss.setSpan(new BulletSpan(BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		mSpannableTextView.setText(ss);
		
		initViews();

		testColoredKeywd();
		testTopic();
		testAtUsers();
		testExpression();
	}
	
	/**
	 * 
	 */
	private void testExpression() {
		String exStr = "今天天气很好啊[呲牙],是不是应该做点什么[色]";
		SpannableString span = null;
		try {
			span = SpanUtils.getExpression(this, exStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.i(TAG, "testExpression spanstr = " + span);
		tvExpression.setText(span);
	}

	/**
	 * 
	 */
	private void testAtUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1, "好友1"));
		users.add(new User(2, "好友2"));
		StringBuilder sb = new StringBuilder("快来看看啊");
		for (User u : users) {
			sb.append("@").append(u.getName());
		}
		sb.append("\n");
		Log.i(TAG, "testAtUsers sb = " + sb.toString());
		SpannableString topicText = null;
		topicText = SpanUtils.getUserSpan(Color.RED, sb.toString(), true,
				new SpanClickListener<User>() {

					@Override
					public void onSpanClick(User t) {
						Toast.makeText(TextViewLinkActivity.this, "点击了：" + t.getName(),
								Toast.LENGTH_SHORT).show();
					}
				}, users);
		Log.i(TAG, "testAtUsers spanStr = " + topicText);
		tvTestAt.setText(topicText);
		tvTestAt.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/**
	 * 
	 */
	private void testTopic() {
		String topic = "#舌尖上的大连#四种金牌烤芝士吃法爱吃芝士的盆友不要错过了~L秒拍视频\n";
		SpannableString topicText = null;

		topicText = SpanUtils.getTopicSpan(Color.GREEN, topic, true,
				new SpanClickListener<Topic>() {

					@Override
					public void onSpanClick(Topic t) {
						Toast.makeText(TextViewLinkActivity.this,
								"点击了：" + t.getTitle(), Toast.LENGTH_SHORT)
								.show();
					}
				}, new Topic(1, "舌尖上的大连"));

		tvTopic.setText(topicText);
		tvTopic.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/**
	 * 关键字
	 */
	private void testColoredKeywd() {
		String string = "Android一词的本义指“机器人”，同时也是Google于2007年11月5日,Android logo相关图片,Android logo相关图片(36张)\n";
		SpannableString cardText = null;
		cardText = SpanUtils.getKeyWordSpan(Color.BLUE, string, "Android");
		tvColoredKeywd.setText(cardText);
	}

	private void initViews() {
		tvColoredKeywd = (TextView) findViewById(R.id.tv_keyword_colored);
		tvTopic = (TextView) findViewById(R.id.tv_topic);
		tvTestAt = (TextView) findViewById(R.id.tv_test_at);
		tvExpression = (TextView) findViewById(R.id.tv_text_expression);
	}
	
}
