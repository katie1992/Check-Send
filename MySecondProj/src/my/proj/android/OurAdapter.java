package my.proj.android;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OurAdapter extends BaseAdapter  {
	Context ctx;
	ArrayList<ListItem> arr;
	LayoutInflater in;
	OnClickListener next1;
	 OnClickListener next;
	public OurAdapter(Context c, ArrayList<ListItem> al)
	{
		ctx=c;
		in=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		arr=al;
	/**	  next1 = new OnClickListener() {
      		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent senEmailIntent = new Intent(Intent.ACTION_SEND);
				senEmailIntent.putExtra(Intent.EXTRA_EMAIL, ((TextView)v.findViewById(R.id.itemContent)).getText());  
				senEmailIntent.setType("text/plain");
    		    ctx.startActivity(senEmailIntent);  
			}
                 }; 
                 next = new OnClickListener() {

              		@Override
              		public void onClick( View v) {
              			String num=(String) ((TextView)v.findViewById(R.id.itemContent)).getText();
              			Intent callIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+num ));
                		    ctx.startActivity(callIntent);
              			
              		}

                         }; */
                 
	}
	@Override
	public int getCount() {
		
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	public Boolean check (int position)
	{
		arr.get(position).checked=!arr.get(position).checked;
		return arr.get(position).checked;
	}
	@Override
	public View getView(int position, View usedView, ViewGroup parentView) {
		View v=usedView;
		if(v==null)
			v=in.inflate(R.layout.list_item, parentView,false);
		ListItem cur=arr.get(position);
		TextView tv=(TextView) v.findViewById(R.id.itemContent);
		tv.setText(cur.itemContent);
		tv=(TextView) v.findViewById(R.id.itemName);
		tv.setText(cur.itemName);
		
		if(arr.get(position).checked)
		{
			v.setBackgroundColor(Color.CYAN);
		}
		else
		{
			v.setBackgroundColor(Color.BLACK);
		}
	/**	if(cur.itemName=="email")
		{
			v.setOnClickListener(next1);
		}
		else if(cur.itemName=="phone")
		{
			v.setOnClickListener(next);
		}*/
		return v;
	}
	

 

 

}
