package com.example.hi;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

public class MainActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	public int pos;
	public int y;
	public int m;
	public String d;
	public static int p1 = -1;
	public static int y1;
	public static int m1;
	public static String d1;
	public static int SUNDAY = 1;
	public static int MONDAT = 2;
	public static int TUESDAY = 3;
	public static int WEDNSETAY = 4;
	public static int THURSDAY = 5;
	public static int FRIDAY = 6;
	public static int SATURDAY = 7;

	private TextView CalendarTitle;
	private GridView GridViewCalendar;

	private ArrayList<Day> DayList;
	private CalendarAdapter CalendarAdapter;
	Calendar ThisMonth;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent3 = getIntent();
		p1 = intent3.getIntExtra("pos", -1);
		d1 = intent3.getStringExtra("d");
		y1 = intent3.getIntExtra("y", 0);
		m1 = intent3.getIntExtra("m", 0);

		if (p1 == -1) {
			ScheduleList.Load();
		}

		pos = p1;
		d = d1;
		y = y1;
		m = m1;

		Button bLastMonth = (Button) findViewById(R.id.b_last);
		Button bNextMonth = (Button) findViewById(R.id.b_next);

		CalendarTitle = (TextView) findViewById(R.id.tv_title);
		CalendarTitle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), List.class);
				intent.putExtra("pos", pos);
				intent.putExtra("d", d);
				intent.putExtra("y", y);
				intent.putExtra("m", m);
				startActivity(intent);
			}

		});
		GridViewCalendar = (GridView) findViewById(R.id.gv_calendar);

		bLastMonth.setOnClickListener(this);
		bNextMonth.setOnClickListener(this);
		GridViewCalendar.setOnItemClickListener(this);
		DayList = new ArrayList<Day>();
	}

	protected void onResume() {
		super.onResume();

		ThisMonth = Calendar.getInstance();
		ThisMonth.set(Calendar.DAY_OF_MONTH, 1);
		getCalendar(ThisMonth);
	}

	private void getCalendar(Calendar calendar) {
		int lastMonthStartDay;
		int dayOfMonth;
		int thisMonthLastDay;

		DayList.clear();

		dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, -1);
		Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH) + "");

		lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.MONTH, 1);
		Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH) + "");

		if (dayOfMonth == SUNDAY) {
			dayOfMonth += 7;
		}

		lastMonthStartDay -= (dayOfMonth - 1) - 1;

		CalendarTitle.setText(ThisMonth.get(Calendar.YEAR) + "년 "
				+ (ThisMonth.get(Calendar.MONTH) + 1) + "월");

		Day day;

		Log.e("DayOfMOnth", dayOfMonth + "");

		for (int i = 0; i < dayOfMonth - 1; i++) {
			int date = lastMonthStartDay + i;
			day = new Day();
			day.setDay(Integer.toString(date));
			day.setInMonth(false);

			DayList.add(day);
		}
		for (int i = 1; i <= thisMonthLastDay; i++) {
			day = new Day();
			day.setDay(Integer.toString(i));
			day.setInMonth(true);

			DayList.add(day);
		}
		for (int i = 1; i < 42 - (thisMonthLastDay + dayOfMonth - 1) + 1; i++) {
			day = new Day();
			day.setDay(Integer.toString(i));
			day.setInMonth(false);
			DayList.add(day);
		}

		initCalendarAdapter();
	}

	private Calendar getLastMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, -1);
		CalendarTitle.setText(ThisMonth.get(Calendar.YEAR) + "년 "
				+ (ThisMonth.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	private Calendar getNextMonth(Calendar calendar) {
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				1);
		calendar.add(Calendar.MONTH, +1);
		CalendarTitle.setText(ThisMonth.get(Calendar.YEAR) + "년 "
				+ (ThisMonth.get(Calendar.MONTH) + 1) + "월");
		return calendar;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_last:
			ThisMonth = getLastMonth(ThisMonth);
			getCalendar(ThisMonth);
			break;
		case R.id.b_next:
			ThisMonth = getNextMonth(ThisMonth);
			getCalendar(ThisMonth);
			break;
		}
	}

	private void initCalendarAdapter() {
		CalendarAdapter = new CalendarAdapter(this, R.layout.day, DayList);
		GridViewCalendar.setAdapter(CalendarAdapter);
	}

	public void onItemClick(AdapterView<?> parent, View v, int position,
			long arg3) {
		Day day = DayList.get(position);
		String j = day.getDay();

		Toast.makeText(
				this,
				ThisMonth.get(Calendar.YEAR) + "년"
						+ (ThisMonth.get(Calendar.MONTH) + 1) + "월" + j + "일"
						+ position, Toast.LENGTH_LONG).show();
		pos = position;
		d = day.getDay();
		y = ThisMonth.get(Calendar.YEAR);
		m = ThisMonth.get(Calendar.MONTH);

	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(this, List.class);
			intent.putExtra("pos", pos);
			intent.putExtra("d", d);
			intent.putExtra("y", y);
			intent.putExtra("m", m);
			startActivity(intent);
			break;
		case 2:
			Intent intent1 = new Intent(this, Make.class);
			intent1.putExtra("pos", pos);
			intent1.putExtra("d", d);
			intent1.putExtra("y", y);
			intent1.putExtra("m", m);
			startActivity(intent1);
			break;
		}
		return true;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(0, 1, 2, "일");
		menu.add(0, 2, 2, "새로만들기");

		return true;
	}

}
