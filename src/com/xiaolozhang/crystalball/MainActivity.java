package com.xiaolozhang.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	// member variable(s)
	private CrystalBall mCrystalBall = new CrystalBall();
	private TextView mAnswerLabel;
	private Button mGetAnswerButton;
	private ImageView mCrystalBallImage;
	// method(s)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Declare our new variable
		mAnswerLabel = (TextView) findViewById(R.id.textView1);
		mGetAnswerButton = (Button) findViewById(R.id.button1);
		mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
		
		mGetAnswerButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				String answer = mCrystalBall.getAnAnswer();
				animateCrystalBall();
				animateAnswer();
				playSound();
				// Set variable answer to answerLabel
				mAnswerLabel.setText(answer);
			}
		});

	}

	// Crystal Ball animation
	private void animateCrystalBall() {
		// Set the image resource to use xml file
		mCrystalBallImage.setImageResource(R.drawable.ball_animation);
		// Create a animationDrawable object that can be used to run
		AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage
				.getDrawable();
		// Check to see if the animation is running, if it's running then stop
		// the animation
		if (ballAnimation.isRunning()) {
			ballAnimation.stop();
		}
		// Start the animation
		ballAnimation.start();
	}

	// Animation of the answer text field
	private void animateAnswer() {
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setDuration(1500);
		fadeInAnimation.setFillAfter(true);
		mAnswerLabel.setAnimation(fadeInAnimation);
	}

	// User MediaPlayer to play sound use static method MediaPlayer.create(this, R.raw.crystal_ball)
	private void playSound(){
		MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
		player.start();
		player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
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
