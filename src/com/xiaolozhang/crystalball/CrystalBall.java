package com.xiaolozhang.crystalball;

import java.util.Random;

public class CrystalBall {
	// member variable(s)
	public String[] mAnswers = { "It is certain", "It is decidedly so",
			"All signs say YES", "The stars are not aligned", "My reply is no",
			"It is doubtful", "Better not tell you now",
			"Concentrate and ask again", "Unable to answer now" };

	// method(s)
	public String getAnAnswer() {

		// Create variable answer to store empty string
		String answer = "";

		// Random generator
		Random randomGenerator = new Random();

		// Generate integer from 0 to the length of the answers array
		int randomNumber = randomGenerator.nextInt(mAnswers.length);
		answer = mAnswers[randomNumber];
		return answer;
	}
}
