package com.jebhomenye.math;
import com.jebhomenye.NotThreadSafe;

import static java.lang.Math.*;

@NotThreadSafe
public class Vector2D implements Comparable<Vector2D>{
	private float i;
	private float j;

	private static Vector2D temp = new Vector2D();

	public Vector2D(Vector2D other){
		this(other.i, other.j);
	}

	public Vector2D(){
	}

	public Vector2D(float x){
		this(x, x);
	}

	public Vector2D(float i, float j){
		this.i = i;
		this.j = j;
	}

	public float mag(){
		return (float)sqrt((i * i) + (j * j));
	}

	public Vector2D compAdd(Vector2D other){
		this.i += other.i;
		this.j += other.j;
		return this;
	}

	public Vector2D add(float x){
		this.i += x;
		this.j += x;
		return this;
	}

	public Vector2D compSubtract(Vector2D other){
		this.i -= other.i;
		this.j -= other.j;
		return this;
	}

	public Vector2D multiplyBy(float x){
		this.j *= x;
		this.i *= x;
		return this;
	}

	public Vector2D compMultiply(Vector2D other){
		this.i *= other.i;
		this.j *= other.j;
		return this;
	}

	public Vector2D compDivide(Vector2D other){
		this.i /= other.i;
		this.j /= other.j;
		return this;
	}

	public Vector2D copy(Vector2D other){
		this.i = other.i();
		this.j = other.j();
		return this;
	}

	public float i(){
		return i;
	}

	public float j(){
		return j;
	}

	public float x(){
		return i;
	}

	public float y(){
		return j;
	}

	public void i(float i){
		this.i = i;
	}

	public void j(float j){
		this.j = j;
	}

	public void x(float x){
		i(x);
	}


	public void y(float y){
		j(y);
	}

	public Vector2D set(float i, float j){
		i(i);
		j(j);
		return this;
	}

	@Override
	public String toString(){
		return String.format("(%s, %s)", i, j);
	}

	public Vector2D divide(float x) {
		if(x == 0){
			return this;
		}
		i /= x;
		j /= x;
		return this;
	}

	public Vector2D subtract(float x) {
		i -= x;
		j -= x;
		return this;
	}

	public static Vector2D direction(Vector2D v){
		return v.compDivide(abs(new Vector2D(v)));
	}

	public static Vector2D abs(Vector2D v){
		return v.set(Math.abs(v.i), Math.abs(v.j));
	}

	public static float distance(Vector2D a, Vector2D b){
		return (float)sqrt(pow(a.x() - b.x(), 2) +  pow(a.y() - b.y(), 2));
	}

	public static void main(String[] args) {
		Vector2D v = new Vector2D(0.005f);
		Vector2D ballDirection = new Vector2D();
		Vector2D temp = new Vector2D();

		Vector2D ballVelocity = new Vector2D(-0.2f, 0.2f);
		ballDirection = Vector2D.direction(ballDirection.copy(ballVelocity));
		temp = Vector2D.abs(temp.copy(ballVelocity)).compAdd(v).compMultiply(ballDirection);

		ballVelocity.set(temp.x(), temp.y());

		System.out.println(ballVelocity);
		System.out.println(Math.round(0.35000002));
	}

	@Override
	public int compareTo(Vector2D o) {
		if(i > o.i && j > o.j){
			return 1;
		}else if(i == o.i && j == o.j){
			return 0;
		}else if(i < o.i && j < o.j){
			return -1;
		}else if(i < o.i && j > o.j){
			return -1;
		}else {
			return 1;
		}
	}
}
