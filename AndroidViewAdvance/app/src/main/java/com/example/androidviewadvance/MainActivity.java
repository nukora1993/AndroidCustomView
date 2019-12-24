package com.example.androidviewadvance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="ViewTest";
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mainLayout=findViewById(R.id.main_layout);
//        LayoutInflater layoutInflater=LayoutInflater.from(this);
        //注意，如果只是addView，那么xml中设置的属性无效,设置的属性必须在一个layout中才有效
        //所以必须给其嵌套一个layout,嵌套的方法既可以是xml中嵌套，也可以是在如下的代码，注意只是嵌套，并没有将嵌套的布局给add进来
        //或者说只是为了得到宽高
//        View buttonLayout=layoutInflater.inflate(R.layout.button_layout,mainLayout,false);
//        mainLayout.addView(buttonLayout);



//        Log.d(TAG,"main layout child num "+mainLayout.getChildCount());


        //取得contentview的layout,是ContentFrameLayout
//        ViewParent viewParent=mainLayout.getParent();
//        Log.d(TAG,"main layout parent: "+viewParent);
//        Log.d(TAG,"content view layout"+findViewById(Window.ID_ANDROID_CONTENT));



    }
}
