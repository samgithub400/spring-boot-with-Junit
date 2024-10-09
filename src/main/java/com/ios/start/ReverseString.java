package com.ios.start;

public class ReverseString {

	public static void mains(String[] args) {

		String str = "Virat Kohali";
		// String str = "madam";

		// Reverse This String....
		// 1.str = "ilahok tariV"
		// 2.check that string is palindrome or not

		
		swapString(str);
		String reversedString = reverseString(str);
		System.out.println("Entered String : " + str);
		System.out.println("Reversed String : " + reversedString);

		boolean palindromeString = checkPalindromeString(str);
		if (checkPalindromeString(str))
			System.out.println("Entered String : " + str + " Is Palindrome...!");
		else
			System.out.println("Entered String : " + str + " Is Not A Palindrome...!");
		
		
		
		checkPalindromeNumber(9999);
		System.out.println(optimisedCheckPalindromeNumber(9999));

	}

	public static String reverseString(String string) {

		StringBuilder reverseString = new StringBuilder();
		for (int i = string.length() - 1; i >= 0; i--) {
			System.out.println(i);
			reverseString.append(string.charAt(i));
		}
		return reverseString.toString();
	}

	public static boolean checkPalindromeString(String string) {

		int first = 0;
		int last = string.length() - 1;

		while (first < last) {

			if (string.charAt(first) != string.charAt(last))
				return false;
			first++;
			last--;
		}
		return true;
	}

	public static String swapString(String string) {
		// Reverse This String....
		// String str = "Virat Kohali";
		// 1.str = "Kohali Virat"
		String[] stringArray = string.split(" ");

		String last = stringArray[stringArray.length - 1];
		stringArray[stringArray.length - 1] = stringArray[0];
		stringArray[0] = last;
		StringBuilder sb = new StringBuilder();

		for (String string2 : stringArray) {

			sb.append(string2);
			sb.append(" ");

		}
		String trim = sb.toString().trim();

		System.out.println(string + "=" + trim);
		System.out.println(string.length() + "=" + trim.length());

		return null;
	}

	public static void checkPalindromeNumber(int number) {
		int temp =number;
		int reverse =0;
		int digit = 0;
		while (number != 0) {
			digit = number%10;
			reverse = reverse*10+digit;
			number = number/10;
		}
		
		
		if(temp==reverse)
		System.out.println("YOU ENTERED NUMBER : "+temp +" IS PALINDROME");
		else
			System.out.println("YOU ENTERED NUMBER : "+temp +" IS NOT PALINDROME...!!");
	}
	
	public static boolean optimisedCheckPalindromeNumber(int number) {
	
		// This is the ways to convert number in to string....
		String string = Integer.toString(number);	
		
		//String string1 = String.valueOf(number);
		
		int first = 0;
		int last = string.length()-1;
		
		while(first<last) {
			if(string.charAt(first)!=string.charAt(last)) 
				return false;
			first++;
			last--;
		}
		return true;
	}
}
