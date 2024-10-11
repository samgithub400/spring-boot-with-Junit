package com.ios.start;

public class DefaultMethodTest implements I {

	public static void mains(String[] args) {
		System.out.println("Main Start....");

		DefaultMethodTest obj = new DefaultMethodTest();

		obj.m2();

		Parent parent = new Parent(); // reference is parent class and object is also parent class....

		parent.get(); // parent class get method will call...
		// Parent.get(); // this is the way to call static method..ClassName.methodName

		Parent paren1 = new Child();

		paren1.get(); // parent class get method will call...

		 int number = parent.x;

		int number1 = paren1.x;

		System.out.println(number + "  " + number1);
		System.out.println("Main End....");

	}

	@Override
	public void m1() {
		System.out.println("m1 method...");
	}

	@Override
	public void m2() {
		System.out.println("over-ridden m2() method...");

	}

}

interface I {
	void m1();

	default void m2() {
		System.out.println("Default method..I...");
	}

	default void m2(int s) {
		System.out.println("default... Integer...");
	}
}

class Parent {
	public int x = 10;

	public static void get() {
		System.out.println("Parent class method...");
	}

}

class Child extends Parent {
	public int x = 20;

	public static void get() {
		System.out.println("child class method....");
	}
}
