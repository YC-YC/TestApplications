/**
 * 
 */
package com.example.resources.text;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

/**
 * @author Administrator
 * @time 2016-9-27 下午5:33:00
 * TODO:
 */
public class SpanUtils {
	
	private interface PatternString{
		/**话题*/
		String TOPIC_PATTERN = "#[^#]+#";
		/**表情*/
		String Expression_PATTERN = "\\[[^\\]] + \\]";
		
		String URL_PATTERN = "";
	}
	
	public interface SpanClickListener<T>{
        void onSpanClick(T t);
    }
	
	public static SpannableString getKeyWordSpan(int color, String str, String patternStr){
		SpannableString spannableString = new SpannableString(str);
		Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
		dealPatternColor(color, spannableString, pattern, 0);
		return spannableString;
	}

	public static SpannableString getExpression(Context context, String str) throws Exception{
		return ExpressionConvertUtil.getInstace().getExpressionString(context, str);
	}
	
	public static SpannableString getTopicSpan(int color, String str, boolean bClick, SpanClickListener clickListener, Topic topic){
		SpannableString spannableString = new SpannableString(str);
		Pattern pattern = Pattern.compile(PatternString.TOPIC_PATTERN, Pattern.CASE_INSENSITIVE);
		if (bClick){
			dealPatternClick(spannableString, pattern, 0, clickListener, topic);
		}
		dealPatternColor(color, spannableString, pattern, 0);
		return spannableString;
	}
	
	public static SpannableString getUserSpan(int color, String str, boolean bClick, final SpanClickListener clickListener, List<User> users){
		SpannableString spannableString = new SpannableString(str);
		Pattern pattern;
		for (User u: users){
			pattern = Pattern.compile("@"+ u.getName(), Pattern.CASE_INSENSITIVE);
			if (bClick){
				dealPatternClick(spannableString, pattern, 0, clickListener, u);
			}
			dealPatternColor(color, spannableString, pattern, 0);
		}
		return spannableString;	
	}

	/**
	 * @param color进行正则判断，如果符合要求，则将内容变色
	 * @param spannableString
	 * @param pattern
	 * @param i
	 */
	private static void dealPatternColor(int color, SpannableString spannableString,
			Pattern pattern, int start) {
		Matcher matcher = pattern.matcher(spannableString);
		while(matcher.find()){
			String key = matcher.group();
			if (matcher.start() < start){
				continue;
			}
			int end = matcher.start() + key.length();
			spannableString.setSpan(new ForegroundColorSpan(color), matcher.start(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			if (end < spannableString.length()){
				dealPatternColor(color, spannableString, pattern, end);
			}
			break;
		}
		
	}
	
	private static void dealPatternClick(SpannableString spannableString, Pattern pattern, int start, final SpanClickListener spanClickListener, final Object bean){
		Matcher matcher = pattern.matcher(spannableString);
		while (matcher.find()){
			String key = matcher.group();
			if (matcher.start() < start){
				continue;
			}
			int end = matcher.start() + key.length();
			spannableString.setSpan(new ClickableSpan() {
				
				@Override
				public void onClick(View widget) {
					spanClickListener.onSpanClick(bean);
				}
				
				@Override
				public void updateDrawState(TextPaint ds) {
					super.updateDrawState(ds);
					//默认有下划线
					ds.setUnderlineText(false);
				}
			}, matcher.start(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			if (end < spannableString.length()){
				dealPatternClick(spannableString, pattern, end, spanClickListener, bean);
			}
			break;
		}
	}

}
