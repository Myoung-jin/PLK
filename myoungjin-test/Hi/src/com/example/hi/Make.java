package com.example.hi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class Make extends Activity {
	public static int yy = 10;
	public static int mm = 12;
	public static int dd = 31;
	public static int tt = 24;
	public static int pp = 42;
	private DatePicker datePicker;
	private TimePicker timePicker;
	private EditText listname;
	private EditText schedule;
	int m;
	int y;
	int pos;
	String d;
	String list;
	String sche;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make);
		Intent intent = getIntent();
		pos = intent.getIntExtra("pos", 0);
		d = intent.getStringExtra("d");
		y = intent.getIntExtra("y", 0);
		m = intent.getIntExtra("m", 0);
		Button finish = (Button) findViewById(R.id.finish);
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		timePicker = (TimePicker) findViewById(R.id.timePicker);
		listname = (EditText) findViewById(R.id.edittext1);
		schedule = (EditText) findViewById(R.id.editText4);

		timePicker.setIs24HourView(true);
		finish.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(getBaseContext(), MainActivity.class);
				Toast();
				m = datePicker.getMonth();
				y = datePicker.getYear();
				d = datePicker.getDayOfMonth() + "";
				list = listname.getText().toString();
				sche = schedule.getText().toString();
				intent.putExtra("pos", pos);
				intent.putExtra("d", d);
				intent.putExtra("y", y);
				intent.putExtra("m", m);
				intent.putExtra("list", list);
				intent.putExtra("sche", sche);
				ScheduleList.Sch[y - 2013][m][pos].Sch(list,
						timePicker.getCurrentHour(), sche);
				startActivity(intent);

			}
		});
	}

	public void Toast() {
		Toast.makeText(
				this,
				+datePicker.getYear() + "년 " + (datePicker.getMonth() + 1)
						+ "월 " + datePicker.getDayOfMonth() + " 일 "
						+ timePicker.getCurrentHour() + "시 "
						+ timePicker.getCurrentMinute() + "분의 일정을 만들었습니다.",
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make, menu);
		return true;
	}
}
