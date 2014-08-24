package com.shlc.pclient;

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

import com.shlc.util.ClassPathResource;
/**注册界面activity*/
public class RegisterActivity extends Activity implements android.view.View.OnClickListener, OnTouchListener, OnDateSetListener {
	public static final int REGION_SELECT = 1;
	public static final int DATE_DIALOG_ID = 2;
	private TextView tv_agreement,tv_region_modify,tv_region_show,tv_top_title;
	private Button btn_title_left,btn_title_right,btn_send_code;
	private CheckBox chk_agree;
	private EditText et_mobileNo;
	private TextView et_birthday_modify, et_birthday_show;
	private int mYear;  
    private int mMonth;  
    private int mDay; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		initView();
	}
	
	private void initView(){
		tv_top_title = (TextView) findViewById(R.id.tv_top_title);
		tv_top_title.setText("注册");
		
		btn_title_right = (Button) findViewById(R.id.btn_title_right);
		btn_title_right.setVisibility(View.GONE);
		
		btn_title_left = (Button) findViewById(R.id.btn_title_left);
		btn_title_left.setOnClickListener(this);
		
		et_birthday_show = (TextView) findViewById(R.id.rg_birthday_show);
		et_birthday_modify =  (TextView) findViewById(R.id.rg_birthday_modify);
		et_birthday_modify.setOnTouchListener(this);
		
		//获得当前的日期：  
        final Calendar currentDate = Calendar.getInstance();  
        mYear = currentDate.get(Calendar.YEAR);  
        mMonth = currentDate.get(Calendar.MONTH);  
        mDay = currentDate.get(Calendar.DAY_OF_MONTH);  
		
//		btn_send_code = (Button) findViewById(R.id.btn_send_code);
//		btn_send_code.setOnClickListener(this);
//		
		tv_agreement = (TextView) findViewById(R.id.tv_agreement);
		tv_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv_agreement.setOnClickListener(this);
//		
//		tv_region_show = (TextView) findViewById(R.id.tv_region_show);
//		
//		tv_region_modify = (TextView) findViewById(R.id.tv_region_modify);
//		tv_region_modify.setOnClickListener(this);
//		
		chk_agree = (CheckBox) findViewById(R.id.chk_agree);
		et_mobileNo = (EditText) findViewById(R.id.et_mobileNo);
	}
	
	/**
	 * 重写了onCreateDialog方法来创建一个列表对话框
	 */
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		switch(id){
		case REGION_SELECT:
			final Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("请选择所在地");
			builder.setSingleChoiceItems(//第一个参数是要显示的列表，用数组展示；第二个参数是默认选中的项的位置；
					//第三个参数是一个事件点击监听器
					new String[]{"+86中国大陆","+853中国澳门","+852中国香港","+886中国台湾"},
					0, 
					new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							switch(which){
							case 0:
								tv_region_show.setText("+86中国大陆");
								
								break;
							case 1:
								tv_region_show.setText("+853中国澳门");
								break;
							case 2:
								tv_region_show.setText("+852中国香港");
								break;
							case 3:
								tv_region_show.setText("+886中国台湾");
								break;
							}
							dismissDialog(REGION_SELECT);//想对话框关闭
						}
					});
			return builder.create();
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, this, mYear, mMonth, mDay);
		}
		return null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_region_modify:
			showDialog(REGION_SELECT);//显示列表对话框的方法
			break;
		case R.id.btn_title_left:
			RegisterActivity.this.finish();
			break;
		case R.id.tv_agreement:
			Uri uri = Uri.parse("http://163.com");
			startActivity(new Intent(Intent.ACTION_VIEW, uri));
			break;
		case R.id.btn_send_code:
			String mobiles = et_mobileNo.getText().toString();
			if(chk_agree.isChecked()== false)//若没勾选checkbox无法后续操作
				Toast.makeText(this, "请确认是否已经阅读《腾讯QQ服务条款》", Toast.LENGTH_LONG).show();
			if(ClassPathResource.isMobileNO(mobiles)==false)//对手机号码严格验证，参见工具类中的正则表达式
				Toast.makeText(this, "正确填写手机号，我们将向您发送一条验证码短信", Toast.LENGTH_LONG).show();
			if(ClassPathResource.isMobileNO(mobiles)==true&&chk_agree.isChecked()==true){
				//当勾选中且号码正确，点击进行下一步操作
				Toast.makeText(this, "已经向您手机发送验证码，请查看", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RegisterActivity.this, RegisterConfirmActivity.class);
				startActivity(intent);
			}
		}
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
//	      if (event.getAction() == MotionEvent.ACTION_DOWN ) {  
		showDialog(DATE_DIALOG_ID);
		return true;
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		 mYear = year;  
         mMonth = monthOfYear;  
         mDay = dayOfMonth;  
         //设置文本的内容：  
         et_birthday_show.setText(new StringBuilder()  
                     .append(mYear).append("年")  
                     .append(mMonth + 1).append("月")//得到的月份+1，因为从0开始  
                     .append(mDay).append("日"));  
		
	}

	

}
