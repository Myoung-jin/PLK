package blog.example.androidui;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ViewFlowAdapter extends BaseAdapter implements TitleProvider {

	private LayoutInflater mInflater;
	
	private String[] titles = {"과제 추가", "과제 목록"};
	
	public ViewFlowAdapter(Context context){
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		return titles.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			if(position == 0){	//기본뷰
				convertView = mInflater.inflate(R.layout.default_view, null);
				
			}else{	//리스트뷰
				convertView = mInflater.inflate(R.layout.activity_list, null);
			}
		}
		
		return convertView;
	}

	@Override
	public String getTitle(int position) {
		return titles[position];
	}

}
