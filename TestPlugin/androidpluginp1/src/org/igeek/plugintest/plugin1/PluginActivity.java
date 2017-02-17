package org.igeek.plugintest.plugin1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class PluginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /**土司一下*/
    public void toastByPlugin(Context context){
    	Toast.makeText(context, "来自插件1的问候", 1).show();
    }
    
    public void showDialogByPlugin(Context context){
    	ProgressDialog pd=new ProgressDialog(context);
    	pd.setMessage("来自插件1的对话框");
    	pd.show();
    }

}