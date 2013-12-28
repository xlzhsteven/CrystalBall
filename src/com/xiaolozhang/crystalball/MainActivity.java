package com.xiaolozhang.crystalball;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	// member variable(s)
	private CrystalBall mCrystalBall = new CrystalBall();

	// method(s)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Declare our new variable
		final TextView answerLabel = (TextView) findViewById(R.id.textView1);
		Button getAnswerButton = (Button) findViewById(R.id.button1);
		getAnswerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String answer = mCrystalBall.getAnAnswer();

				// Set variable answer to answerLabel
				answerLabel.setText(answer);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
