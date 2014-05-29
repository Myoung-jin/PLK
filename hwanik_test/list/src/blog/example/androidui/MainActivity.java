package blog.example.androidui;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;
import com.markupartist.android.widget.PullToRefreshListView;
import com.markupartist.android.widget.PullToRefreshListView.OnRefreshListener;

public class MainActivity extends Activity {

	private ViewFlow viewFlow;

	
	//listview 包访
	private PullToRefreshListView list = null;
	
	private String[] mStrings = {
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam",
            "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis",
            "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};
	private LinkedList<String> mListItems;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        actionBar.setTitle("Home");
        
        final Action shareAction = new IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default);
        actionBar.addAction(shareAction);
        
        /*Intent intent = new Intent(this, ListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        final Action otherAction = new IntentAction(this, intent, R.drawable.ic_title_export_default);
        actionBar.addAction(otherAction);*/
        
        /* list activity 角青促绰 action 林籍 贸府. 
        Intent intent = new Intent(this, ListViewActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        final Action otherAction = new IntentAction(this, intent, R.drawable.ic_title_export_default);
        actionBar.addAction(otherAction);  */
        
        
        viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        ViewFlowAdapter adapter = new ViewFlowAdapter(this);
        viewFlow.setAdapter(adapter);
        
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
        
		
        Spinner sp1 = (Spinner)viewFlow.findViewById(R.id.androidui_sp_1);
        Spinner sp2 = (Spinner)viewFlow.findViewById(R.id.androidui_sp_2);
        
        Vector<String> data1 = new Vector<String>();
        Vector<String> data2 = new Vector<String>();
        
        data1.add("2011-12-25");
        data2.add("8:00 am");
        
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item,
				data1);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter1);
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_spinner_item,
				data2);

		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp2.setAdapter(adapter2);
        
		
		
		list = (PullToRefreshListView)viewFlow.findViewById(android.R.id.list);
		
		mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings));
        
        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);

        list.setAdapter(listadapter);
		
		
		list.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                // Do work to refresh the list here.
                new GetDataTask().execute();
            }
        });
    }

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                ;
            }
            return mStrings;
        }

        protected void onPostExecute(String[] result) {
            mListItems.addFirst("Added after refresh...");

            // Call onRefreshComplete when the list has been refreshed.
            if(list != null) list.onRefreshComplete();

            super.onPostExecute(result);
        }
    }
	
	
	/* If your min SDK version is < 8 you need to trigger the onConfigurationChanged in ViewFlow manually, like this */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if(viewFlow != null) viewFlow.onConfigurationChanged(newConfig);	
		super.onConfigurationChanged(newConfig);
	}
	
    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }
    
    private Intent createShareIntent() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
        return Intent.createChooser(intent, "Share");
    }
}
