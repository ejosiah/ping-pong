package com.jebhomenye.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by jay on 27/05/2016.
 */
public class Weighted<T> {
	private List<T> list = new ArrayList<>();
	private static Random rng = new Random();

	public Weighted<T> add(T item, int weight){
		list.addAll(Collections.nCopies(weight, item));
		return this;
	}

	public Weighted<T> shuffle(){
		Collections.shuffle(list);
		return this;
	}

	public T get(){
		return list.get(rng.nextInt(list.size()));
	}

	public Weighted<T> clear(){
		list.clear();
		return this;
	}
}
