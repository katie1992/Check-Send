package my.proj.android;

import android.R.bool;
import android.R.string;
import android.graphics.drawable.Drawable;
import android.view.View;

public class ListItem {
	public String itemContent;
	public String itemName;
	public Boolean checked;
	public ListItem(String ic, String in)
	
	{
		
		this.itemContent=ic;
		this.itemName=in;
		this.checked=false;
	}


}
