package com.example.abhay_hp.jsonparser;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {
    private Context context;
    private static String url = "http://quiet-stone-2094.herokuapp.com/transactions.json";
    private static final String SKU = "sku";
    private static final String AMOUNT = "amount";
    private static final String CURRENCY = "currency";
    ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ProgressTask(MainActivity.this).execute();

    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private ListActivity activity; // private List<Message> messages;

        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        private Context context;

        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ListAdapter adapter = new SimpleAdapter(context, jsonlist, R.layout.list_item, new String[]{SKU}, new int[]{R.id.sku});
            setListAdapter(adapter); // select single ListView item
            lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(MainActivity.this, DetailsActivity.class);
                    i.putExtra("name", jsonlist.get(position).get(SKU));
                    i.putExtra("amount", jsonlist.get(position).get(AMOUNT));
                    startActivity(i);
                }
            });
        }

        protected Boolean doInBackground(final String... args) {
            JSONParser jParser = new JSONParser(); // get JSON data from URL
            JSONArray json = jParser.getJSONFromUrl(url);
            for (int i = 0; i < json.length(); i++) {
                try {
                    JSONObject c = json.getJSONObject(i);
                    String sku = c.getString(SKU);
                    String amount = c.getString(AMOUNT) + " " + c.getString(CURRENCY);
                    int index = checkArrayList(sku);
                    if (index >= 0) {
                        amount = jsonlist.get(index).get(AMOUNT) + " , " + amount;
                        jsonlist.get(index).put(AMOUNT, amount);
                    } else {
                        HashMap<String, String> map = new HashMap<String, String>(); // Add child node to HashMap key & value
                        map.put(SKU, sku);
                        map.put(AMOUNT, amount);
                        jsonlist.add(map);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected int checkArrayList(String a) {
            int i = jsonlist.size();
            for (int j = 0; j < i; j++) {
                if (jsonlist.get(j).get(SKU).equals(a)) {
                    return j;
                }
            }
            return -1;
        }
    }
}
