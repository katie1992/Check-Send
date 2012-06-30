package my.proj.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MySecondProjActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	private static final int PICK_CONTACT = 1001;
	String phoneNumber;
	String emailAddress;
	String name;
	ArrayList<ListItem> arr;
	Button button2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        
        arr=new ArrayList<ListItem>();
        Intent intentContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI); 
        startActivityForResult(intentContact, PICK_CONTACT);
  
      
    }//onCreate
    
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) 
    {

      if (requestCode == PICK_CONTACT)
      {         
        getContactInfo(intent);         
        // Your class variables now have the data, so do something with it. 
      }
    }//onActivityResult
    

       
    
    protected void getContactInfo(Intent intent)
    {

       Cursor cursor =  managedQuery(intent.getData(), null, null, null, null);      
       while (cursor.moveToNext()) 
       {           
           String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));           
           String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 
           TextView text = (TextView) findViewById(R.id.textView1);
           text.setText(name);
          
          //arr.add(new ListItem(name,"name"));
           
           
           String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
           
           if ( hasPhone.equalsIgnoreCase("1"))
               hasPhone = "true";
           else
               hasPhone = "false" ;
           if (Boolean.parseBoolean(hasPhone)) 
           {
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,null, null);
            while (phones.moveToNext()) 
            {
            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            arr.add(new ListItem(phoneNumber,"phone"));
            }   
                   
            phones.close();
           }
           
           
                    
         

       
           
        // Find Email Addresses
           Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,null, null);
           while (emails.moveToNext()) 
           {
           String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
           arr.add(new ListItem(emailAddress,"email"));
           }

           emails.close();
           
          

        Cursor address = getContentResolver().query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = " + contactId, null, null);
        while (address.moveToNext()) 
        { 
          // These are all private class variables
         String poBox      = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
         arr.add(new ListItem(poBox,"poBox"));
         String street     = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
         arr.add(new ListItem(street,"street"));
         String city       = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
         arr.add(new ListItem(city,"city"));
         String state      = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
         arr.add(new ListItem(state,"state"));
         String postalCode = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
         arr.add(new ListItem(postalCode,"postalCode"));
         String country    = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
         arr.add(new ListItem(country,"country"));
         String type       = address.getString(address.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
         arr.add(new ListItem(type,"type"));
        }  //address.moveToNext()   
      }  //while (cursor.moveToNext())        
       cursor.close();
       
       OnItemClickListener next1 = new OnItemClickListener() {
     		@Override
     		public void onItemClick(AdapterView parent, View v, int position,
     				long id) {
     			Boolean newValue=((OurAdapter)parent.getAdapter()).check(position);
     			if(newValue)
     			{
     				v.setBackgroundColor(Color.GRAY);
     			}
     			else
     			{
     				v.setBackgroundColor(Color.BLACK);
     			}
     			
     				
     		}
                }; 
               
       OurAdapter adapter=new OurAdapter(this,arr);
       ListView lv=(ListView)findViewById(R.id.list);
       lv.setAdapter(adapter);
       lv.setOnItemClickListener(next1);
       

           
    }//getContactInfo


	@Override
	public void onClick(View v) {

		/**Intent intentSecondActivity = new Intent(this, ActivityTwo.class);
		intentSecondActivity.putExtra("infomation about contact", );
		startActivity(intentSecondActivity);*/
		switch (v.getId()) {
		case R.id.button2:
			Intent intent = new Intent(this, ActivityTwo.class);
			startActivity(intent);
			break;
		}
		
	}


           
    
}