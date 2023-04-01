package complexmatrix;

import java.util.Objects;

public class Complex implements Comparable<Complex>{
	private double real;
	private double imaginary;
	
	public Complex() {
		this(0,0);
	}
	
	public Complex(double a) {
		this(a,0);
	}
	
	public Complex(double a,double b) {
		this.real=a;
		this.imaginary=b;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double a) {
		this.real = a;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double b) {
		this.imaginary = b;
	}
	
	public String toString() {
		if(this.imaginary>=0) {
			return real+" + i"+imaginary;
		}else {
			return real+" - i"+(-1)*imaginary;
		}
	}
	
	public String toOutputfileFormat() { 
		if(this.imaginary>=0) {
			return real+"_"+imaginary;
		}else {
			return real+"_-"+(-1)*imaginary;
		}
	}
	
	public double getMagnitude() {
		double inner=Math.pow(this.real,2)+Math.pow(this.imaginary,2);
		return Math.pow(inner,0.5);
	}
	
	public Complex add(Complex c) {
		Complex addition=new Complex(this.real+c.real,this.imaginary+c.imaginary);
		return addition;
	}
	
	public Complex subtract(Complex c) {
		Complex subtraction=new Complex(this.real-c.real,this.imaginary-c.imaginary);
		return subtraction;
	}
	
	public Complex multiply(Complex c) {
		Complex mult=new Complex();
		double term1=this.real*c.real-this.imaginary*c.imaginary;
		double term2=this.real*c.imaginary+this.imaginary*c.real;
		mult.setReal(term1);
		mult.setImaginary(term2);
		return mult;
	}
	
	public Complex divide(Complex c) {
		Complex divi=new Complex();
		double top1=this.real*c.real+this.imaginary*c.imaginary;
		double bottom=Math.pow(c.real,2)+Math.pow(c.imaginary,2);
		double top2=this.imaginary*c.real-this.real*c.imaginary;
		divi.setReal(top1/bottom);
		divi.setImaginary(top2/bottom);
		return divi;
	}

	public int compareTo(Complex c) {
		return Double.compare(this.imaginary,c.imaginary);
	}

}
