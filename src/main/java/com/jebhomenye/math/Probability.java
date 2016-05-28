package com.jebhomenye.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by jay on 27/05/2016.
 */
public class Probability {
	private List<Boolean> list = new ArrayList<>();
	private static Random rng = new Random();
	private static final int MAX_SIZE = 100;

	public Probability(int value){
		if(value < 0 || value > MAX_SIZE){
			throw new IllegalArgumentException("chance value should be between 0 & " + MAX_SIZE);
		}
		int occur = value;
		int notOccur = MAX_SIZE - value;
		for(int i = 0; i < occur; i++){
			list.add(true);
		}
		for(int i = 0; i < notOccur; i++){
			list.add(false);
		}
		Collections.shuffle(list);
	}

	public boolean Occured(){
		return list.get(rng.nextInt(MAX_SIZE));
	}

}
