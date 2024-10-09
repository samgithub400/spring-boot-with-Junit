package com.ios.start;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestString {

	public static void mains(String[] args) {
		findDuplicateCharacterInString("AABCDEEFGG");
		findDuplicateCharacterUsingStream("AABCDEEFGGGKKKKKOPPPPPPPPPPP");
		findDuplicateCharacterUsingStream1("AABCDEEFGGGKKKKKOPPPPPPPPPPP");
		findNonDuplicateCharacter("AABCDEEFGGGKKKKKOPPPPPPPPPPP");
		reverseString("AABCDEEFGGGKKKKKOPPPPPPPPPPP");
		checkPalindromeString("MADAM");
	}

	public static void findDuplicateCharacterInString(String string) {

		// string = "AABCDEEFGG";
		Set<Character> set = new HashSet<>();
		Set<Character> duplicateSet = new HashSet<>();
		Map<Character, Integer> map = new HashMap<>();

		/*
		 * for(int i =0;i<string.length();i++) { System.out.print(string.charAt(i));
		 * if(!set.add(string.charAt(i))) { duplicateSet.add(string.charAt(i)); } }
		 */

		// find duplicate character .............

		/*
		 * for(char ch : string.toCharArray()) { if(!set.add(ch)) duplicateSet.add(ch);
		 * }
		 * 
		 * System.out.println(set+"::::::"+duplicateSet);
		 */

		// find count of each duplicate character........

		for (char ch : string.toCharArray()) {

			if (!map.containsKey(ch))
				map.put(ch, 1);
			else
				map.put(ch, map.get(ch) + 1);

		}

		System.out.println(map);

		Map<Character, Integer> filteredMap = map.entrySet().stream().filter(entry -> entry.getValue() > 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		filteredMap.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ":" + entry.getValue()));

	}

	public static void findDuplicateCharacterUsingStream(String string) {

		// here this method will find duplicate character with it's occurrence count....

		Map<Character, Long> foudCharacters = string.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		foudCharacters.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
	}

	public static void findDuplicateCharacterUsingStream1(String string) {

		// here this method will find duplicate characters only....

		List<Character> list = string.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).collect(Collectors.toList());

		list.forEach(ch -> System.out.println(ch));
	}
	
	public static void findNonDuplicateCharacter(String string) {
		
		// here this method will find non-duplicate characters only....
		
		List<Character> nonDuplicateCharacterList = string.chars().mapToObj(ch -> (char) ch)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet().stream()
		.filter(entry->entry.getValue()==1)
		.map(entry->entry.getKey())
		.collect(Collectors.toList());
		
		nonDuplicateCharacterList.forEach(ch->System.out.print(ch+" "));
		System.err.println();
	}
	
	public static void reverseString(String string) {
		
		// here this method will reverse string....
		String reversedString = new StringBuilder(string).reverse().toString();
		System.out.println("Entered String IS: "+string+ "\n" +"Reversed String IS: "+reversedString);
	}
	
	public static void checkPalindromeString(String string) {
		
		//here this method will check the string is palindrome or not....
		
		if(new StringBuilder(string).reverse().toString().equals(string))
			System.out.println("Entered String "+ string +" Is Palindrome...");
		else
			System.out.println("Entered String  "+ string +" Is NOT Palindrome....!!!!!");
		
		
	}

}
