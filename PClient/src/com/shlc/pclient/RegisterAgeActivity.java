package com.shlc.pclient;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shlc.pclient.R.id;
import com.shlc.util.ClassPathResource;
/**注册界面activity*/
public class RegisterAgeActivity extends Activity implements android.view.View.OnClickListener {

	private TextView tv_agreement,tv_region_modify,tv_region_show,tv_top_title;
	private Button btn_title_left,btn_title_right,btn_send_code;
	
	public final static String BORN_YEAR = "com.shlc.pclient.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register_age);
		initView();
	}
	
	private void initView(){
		btn_title_left = (Button) findViewById(R.id.btn_title_left);
		btn_title_left.setOnClickListener(this);
		
		TextView year0 = (TextView)findViewById(R.id.bornyearlist0);
		TextView year1 = (TextView)findViewById(R.id.bornyearlist1);
		
		year0.setOnClickListener(this);
		year1.setOnClickListener(this);
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btn_title_left:
			Intent intent = new Intent(RegisterAgeActivity.this, RegisterActivity.class);
		    startActivity(intent);
			overridePendingTransition(R.anim.out_to_right, R.anim.bottom_activity);			
			break;
		case R.id.bornyearlist0:
			Intent intent0 = new Intent(RegisterAgeActivity.this, RegisterActivity.class);
			TextView bornyear = (TextView) findViewById(R.id.bornyearlist0);
		    String message = bornyear.getText().toString();
		    intent0.putExtra(BORN_YEAR, message);
		    startActivity(intent0);
		    overridePendingTransition(R.anim.out_to_right, R.anim.bottom_activity);	
		    break;
		case R.id.tv_agreement:
			Uri uri = Uri.parse("http://163.com");
			startActivity(new Intent(Intent.ACTION_VIEW, uri));
			break;
		
		}
		
	}

}
