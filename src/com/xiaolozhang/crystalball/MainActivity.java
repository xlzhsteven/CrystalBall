package com.xiaolozhang.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaolozhang.crystalball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	public static final String TAG = MainActivity.class.getSimpleName();
	
	// member variable(s)
	private CrystalBall mCrystalBall = new CrystalBall();
	private TextView mAnswerLabel;
	// private Button mGetAnswerButton;
	private ImageView mCrystalBallImage;

	// member variable for shake motion detection
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;

	// method(s)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Declare our new variable
		mAnswerLabel = (TextView) findViewById(R.id.textView1);
		// mGetAnswerButton = (Button) findViewById(R.id.button1);
		mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);

		// setup onShake action
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector(new OnShakeListener() {

			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer();
			}
		});

		// Adding toast message notification
		// Toast.makeText(this, "Yah! Our activity is created!",
		// Toast.LENGTH_LONG).show();
		Log.d(TAG, "This is logging from onCreate method!");
	}

	@Override
	public void onResume() {
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	public void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);
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

	// User MediaPlayer to play sound use static method MediaPlayer.create(this,
	// R.raw.crystal_ball)
	private void playSound() {
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

	private void handleNewAnswer() {
		String answer = mCrystalBall.getAnAnswer();
		animateCrystalBall();
		animateAnswer();
		playSound();
		// Set variable answer to answerLabel
		mAnswerLabel.setText(answer);
	}

}
