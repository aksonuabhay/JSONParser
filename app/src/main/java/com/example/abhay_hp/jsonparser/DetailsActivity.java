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
        TextView sku_id= (TextView) findViewById(R.id.sku_id);
        TextView sku_amount= (TextView) findViewById(R.id.details);
        sku_id.setText(name);
        sku_amount.setText(amount);
    }
}
