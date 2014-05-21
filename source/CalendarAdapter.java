package com.example.hi;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {
	private ArrayList<Day> DayList;
	private Context Cont;
	private int Resource;
	private LayoutInflater LiInflater;

	public CalendarAdapter(Context context, int textResource,
			ArrayList<Day> dayList) {
		this.Cont = context;
		this.DayList = dayList;
		this.Resource = textResource;
		this.LiInflater = (LayoutInflater) Cont
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return DayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return DayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Day day = DayList.get(position);

		DayViewHolde ViewHolder;

		if (convertView == null) {
			convertView = LiInflater.inflate(Resource, null);

			if (position % 7 == 6) {
				convertView.setLayoutParams(new GridView.LayoutParams(
						getCellWidthDP() + getRestCellWidthDP(),
						getCellHeightDP()));
			} else {
				convertView.setLayoutParams(new GridView.LayoutParams(
						getCellWidthDP(), getCellHeightDP()));
			}

			ViewHolder = new DayViewHolde();

			ViewHolder.llBackground = (LinearLayout) convertView
					.findViewById(R.id.day_cell_background);
			ViewHolder.tvDay = (TextView) convertView
					.findViewById(R.id.day_cell);

			convertView.setTag(ViewHolder);
		} else {
			ViewHolder = (DayViewHolde) convertView.getTag();
		}

		if (day != null) {
			ViewHolder.tvDay.setText(day.getDay());

			if (day.isInMonth()) {
				if (position % 7 == 0) {
					ViewHolder.tvDay.setTextColor(Color.RED);
				} else if (position % 7 == 6) {
					ViewHolder.tvDay.setTextColor(Color.BLUE);
				} else {
					ViewHolder.tvDay.setTextColor(Color.BLACK);
				}
			} else {
				ViewHolder.tvDay.setTextColor(Color.GRAY);
			}

		}

		return convertView;
	}

	public class DayViewHolde {
		public LinearLayout llBackground;
		public TextView tvDay;

	}

	private int getCellWidthDP() {

		int cellWidth = 480 / 7;

		return cellWidth;
	}

	private int getRestCellWidthDP() {

		int cellWidth = 480 % 7;

		return cellWidth;
	}

	private int getCellHeightDP() {
		int cellHeight = 480 / 6;

		return cellHeight;
	}

}