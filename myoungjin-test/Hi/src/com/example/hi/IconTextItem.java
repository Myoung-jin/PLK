package com.example.hi;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IconTextItem extends Fragment {

	private Drawable mIcon;

	private String[] mData;

	private boolean mSelectable = true;



	// 탭을 선택할 객체들을 만들어 준다.
	
	public IconTextItem(Drawable icon, String[] obj) {
		mIcon = icon;
		mData = obj;
	}

	private ActionBar getActionBar() {
		// TODO Auto-generated method stub
		return null;
	}

	public IconTextItem(String obj01, String obj02, String obj03) {

		mData = new String[3];
		mData[0] = obj01;
		mData[1] = obj02;
		mData[2] = obj03;
	}

	public boolean isSelectable() {
		return mSelectable;
	}

	public void setSelectable(boolean selectable) {
		mSelectable = selectable;
	}

	public String[] getData() {
		return mData;
	}

	public String getData(int index) {
		if (mData == null || index >= mData.length) {
			return null;
		}

		return mData[index];
	}

	public void setData(String[] obj) {
		mData = obj;
	}

	public void setIcon(Drawable icon) {
		mIcon = icon;
	}

	public Drawable getIcon() {
		return mIcon;
	}

	public int compareTo(IconTextItem other) {
		if (mData != null) {
			String[] otherData = other.getData();
			if (mData.length == otherData.length) {
				for (int i = 0; i < mData.length; i++) {
					if (!mData[i].equals(otherData[i])) {
						return -1;
					}
				}
			} else {
				return -1;
			}
		} else {
			throw new IllegalArgumentException();
		}

		return 0;
	}
}