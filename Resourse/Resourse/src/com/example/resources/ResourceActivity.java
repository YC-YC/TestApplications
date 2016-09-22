package com.example.resources;

import com.example.resources.drawable.ButtonState;
import com.example.resources.drawable.Clip;
import com.example.resources.drawable.CrossFade;
import com.example.resources.drawable.Inset;
import com.example.resources.drawable.Layer;
import com.example.resources.drawable.Level;
import com.example.resources.drawable.Shape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author YC
 * @time 2016-3-10 下午12:03:44
 */
public class ResourceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resource_main);
	}
	
	public void onClick_Layer(View view)
    {
    	Intent intent = new Intent(this, Layer.class);
    	startActivity(intent);
    	
    }
    public void onClick_ButtonState(View view)
    {
    	Intent intent = new Intent(this, ButtonState.class);
    	startActivity(intent);
    	
    }
    public void onClick_Level(View view)
    {
    	Intent intent = new Intent(this, Level.class);
    	startActivity(intent);
    	
    }
    public void onClick_CrossFade(View view)
    {
    	Intent intent = new Intent(this, CrossFade.class);
    	startActivity(intent);
    	
    }
    public void onClick_Inset(View view)
    {
    	Intent intent = new Intent(this, Inset.class);
    	startActivity(intent);
    	
    }
    public void onClick_Clip(View view)
    {
    	Intent intent = new Intent(this, Clip.class);
    	startActivity(intent);
    	
    }
    public void onClick_Shape(View view)
    {
    	Intent intent = new Intent(this, Shape.class);
    	startActivity(intent);
    	
    }
}
