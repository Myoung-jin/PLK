package com.example.hi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class List extends Activity {
	DataListView list;
	public static int yy = 5;
	public static int mm = 5;
	public static int dd = 31;
	public static int tt = 24;
	public static int pp = 5;

	/**
	 * 어댑터 객체
	 */
	IconTextListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int ttt = 0;
		Intent intent = getIntent();
		int pos = intent.getIntExtra("pos", 0);
		String d = intent.getStringExtra("d");
		int y = intent.getIntExtra("y", 0);
		int m = intent.getIntExtra("m", 0);
		String k = y + "년" + (m + 1) + "월" + d + "일";
		// 타이틀 없애기
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 리스트뷰 객체 생성
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT);
		list = new DataListView(this);

		// 어댑터 객체 생성
		adapter = new IconTextListAdapter(this);

		// 아이템 데이터 만들기

		adapter.addItem(new IconTextItem(k, "일정목록", ""));

		// 리스트뷰에 어댑터 설정
		list.setAdapter(adapter);

		// 새로 정의한 리스너로 객체를 만들어 설정
		list.setOnDataSelectionListener(new OnDataSelectionListener() {
			public void onDataSelected(AdapterView parent, View v,
					int position, long id) {
				IconTextItem curItem = (IconTextItem) adapter.getItem(position);
				String[] curData = curItem.getData();

				Toast.makeText(getApplicationContext(),
						"Selected : " + curData[0], 2000).show();
			}
		});

		// 화면을 리스트뷰 객체로 채움
		setContentView(list, params);
		for (ttt = 0; ttt < 24; ttt++) {
			adapter.addItem(new IconTextItem(ScheduleList.Sch[y - 2013][m][pos]
					.gettitle(ttt), ScheduleList.Sch[y - 2013][m][pos]
					.getmessage(ttt), (ttt + 1) + "시"));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		menu.add(0, 1, 2, "일");
		menu.add(0, 2, 2, "새로만들기");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Intent intent = new Intent(getBaseContext(), ListView.class);
			startActivity(intent);
			break;
		case 2:
			Intent intent1 = new Intent(getBaseContext(), Make.class);
			startActivity(intent1);
			break;
		}
		return true;
	}
}
