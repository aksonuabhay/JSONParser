package com.example.abhay_hp.jsonparser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Abhay-HP on 3/25/2015.
 */
public class DetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view);
        String amount=getIntent().getExtras().getString("amount");
        String name=getIntent().getExtras().getString("name");

        TextView t= (TextView) findViewById(R.id.sku_id);
        TextView t2= (TextView) findViewById(R.id.details);
        t.setText(name);
        t2.setText(amount);

    }
}
