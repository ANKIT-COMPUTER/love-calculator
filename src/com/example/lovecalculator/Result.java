package com.example.lovecalculator;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity {
	String s1, s2;
	TextView t1, t2, t3, t4;
	Button b2;
	ProgressBar progressBar;
	String url = "https://jsonkeeper.com/b/63OH";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		Intent intent = getIntent();

		s1 = intent.getStringExtra("player1");
		s2 = intent.getStringExtra("player2");
		toastMessage(s1 + " " + s2);
		b2 = (Button) findViewById(R.id.button2);
		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		t3 = (TextView) findViewById(R.id.textView3);
		t4 = (TextView) findViewById(R.id.textView4);
		progressBar = (ProgressBar) findViewById(R.id.pbar);

		progressBar.getIndeterminateDrawable().setColorFilter(0xFFFF0000,
				android.graphics.PorterDuff.Mode.MULTIPLY);
		b2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent i1 = new Intent(Result.this, MainActivity.class);

				startActivity(i1);
			}

		});
		loadMeme();
	}

	public void loadMeme() {

		String MEME_URL = "https://love-calculator.p.rapidapi.com/getPercentage?sname="+s2+"&fname="+s1;

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.GET, MEME_URL,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {

							progressBar.setVisibility(View.GONE);
							t1.setText(response.getString("fname"));
							t3.setText(response.getString("sname"));
							t2.setText(response.getString("percentage")+"%");
							t4.setText(response.getString("result"));
							toastMessage("Found");

						} catch (JSONException e) {

							Toast.makeText(Result.this,
									"Something Went Wrong While Fetching Data",
									Toast.LENGTH_SHORT).show();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						toastMessage("Network slow");

					}
				}){
			 
	        @Override
	        public Map<String, String> getHeaders() throws AuthFailureError
	        {
	            HashMap<String, String> headers = new HashMap<String, String>();
	            headers.put("X-RapidAPI-Host", "love-calculator.p.rapidapi.com");
	            headers.put("X-RapidAPI-Key", "5b305eb02dmsh73cb95a9cf2f9d2p1237a7jsneaf8f835d8e8");
	            return headers;
	        }
	 
	    };

		MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

	}

	private void toastMessage(String s) {
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
	}
}
