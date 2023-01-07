package com.example.lovecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
EditText e1,e2;
Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
	
	
	
	  b1.setOnClickListener(new View.OnClickListener() {
      	public void onClick(View view) {
      		if(e1.getText().toString().length() ==0 ||e2.getText().toString().length()== 0){
              toastMessage("Blacked not allowed");  
          	}
      		else{
      	Intent i1=new Intent(MainActivity.this,Result.class);
      	i1.putExtra("player1", e1.getText().toString());
    	i1.putExtra("player2", e2.getText().toString());
      	 startActivity(i1);
      		}
    	}
      });
	}
private void toastMessage(String s){
	Toast.makeText(this,s, 
			   Toast.LENGTH_SHORT).show();
}
	
}
