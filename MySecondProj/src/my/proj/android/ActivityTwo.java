package my.proj.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityTwo extends Activity {
	
	@Override
	protected void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.two);
		
		Intent intent = getIntent();
		String action = intent.getAction();
		
	}

}
