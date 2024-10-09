package com.ios.start;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ios.entity.Employee;

public class StartTest {

	public static void mains(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, CloneNotSupportedException {

		List<Employee> list = Arrays.asList(new Employee(1, "abc", 20000, "Pune"),
				new Employee(2, "pqr", 90000, "Pune"), new Employee(3, "xyz", 30000, "mumbai"),
				new Employee(4, "kkl", 45000, "Pune"), new Employee(5, "llc", 100000, "mumbai"));

//want employees whose from pune city only...

		List<Employee> puneEmployeeList = list.stream().filter(emp -> emp.getCity().equals("Pune"))
				.collect(Collectors.toList());

		System.out.println(puneEmployeeList);

//find out employee name only whose salary is greater than or equal to 55k
		List<String> employeeNamesHavingSalary = list.stream().filter(emp -> emp.getSalary() >= 55000)
				.map(emp -> emp.getName()).collect(Collectors.toList());

		System.out.println(employeeNamesHavingSalary);

//find employee having same city..
		Map<String, List<Employee>> sameCityOfEmployee = list.stream()
				.collect(Collectors.groupingBy(Employee::getCity));

		System.out.println(sameCityOfEmployee);

// find employee name having same city..

		Map<String, List<String>> employeenameHavingSameCity = list.stream().collect(
				Collectors.groupingBy(Employee::getCity, Collectors.mapping(Employee::getName, Collectors.toList())));

		System.out.println(employeenameHavingSameCity);

// find highest salary from pune location....
		Employee highestSalaryFromPune = list.stream().filter(employee -> employee.getCity().equals("Pune"))
				.max(Comparator.comparingDouble(Employee::getSalary)).get();

		System.out.println(highestSalaryFromPune);

// find 2nd highest salary...

		Employee secondHighestSalaryEmployee = list.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().get();

		System.out.println(secondHighestSalaryEmployee);

// print only employee names using java 8 features...		

		list.stream().forEach(employee -> System.out.println(employee.getName()));

// print employee names whose employee salary is >=30000

		list.stream().filter(employee -> employee.getSalary() >= 30000)
				.forEach(employee -> System.out.println(employee.getName()));

//
		Map<Integer, List<Employee>> employeeMap = list.stream().collect(Collectors.groupingBy(Employee::getId));

// print this map using java 8...

		employeeMap.forEach((key, value) -> System.out.println(key + ":" + value));

// print key of the map and employee name in the value  of the map....

		for (Map.Entry<Integer, List<Employee>> entry : employeeMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue().stream().map(employee -> employee.getName()));
		}

		for (Map.Entry<Integer, List<Employee>> entry : employeeMap.entrySet()) {
			Integer key = entry.getKey();

			for (Employee employeeList : entry.getValue()) {
				System.out.println(key + " : " + employeeList.getName());
			}
		}

//other way to iterate map of list.....using stream.....

		employeeMap.forEach((key, value) -> {
			value.forEach(list1 -> System.out.println(key + " : " + list1.getName()));
		});

// get the map of employee names whose salary is >=30000

		// Map<Integer,List<String>>

		Map<Integer, List<Employee>> e = employeeMap.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
						.filter(emp3 -> emp3.getSalary() > 30000).collect(Collectors.toList())));
		System.out.println(e);

		Map<String, Integer> hm = new HashMap<>();

		hm.put("suman", 98);
		hm.put("Diya", 85);
		hm.put("Delhi", 91);
		hm.put("John", 95);
		hm.put("Anand", 79);
		hm.put("Naina", 80);

		System.out.println(hm);

		// sort this map based on the values....

		/*
		 * hm.entrySet().stream() .collect(Collectors.toMap(Map.Entry::getKey,
		 * 
		 * 
		 * Map.Entry.comparingByValue()));
		 */

		/*
		 * Map<String, Integer> sortedMap = hm.entrySet().stream()
		 * .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, // or
		 * Map.Entry::getValue if you just // want to copy the values (existing,
		 * replacement) -> existing // or however you want to handle duplicates ));
		 */

		LinkedHashMap<String, Integer> sortedByValue = hm.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(
						Collectors.toMap(Map.Entry::getKey,
								Map.Entry::getValue,
								(e1, e2) -> e1, 
								LinkedHashMap::new));
		System.out.println("-----------------------------\n" + sortedByValue);
		
		
		
		
		hm.entrySet().stream().sorted(Map.Entry.comparingByValue())
		.collect(Collectors.toMap(Map.Entry::getKey,
									Map.Entry::getValue,
									(e1,e2)->e1,
									LinkedHashMap::new
									));
		
// find max value in the map....
		
		Optional<Entry<String, Integer>> min = hm.entrySet().stream().sorted(Map.Entry.comparingByValue())
					.findFirst();
			System.out.println("----------------"+min);
			
		Optional<Entry<String,Integer>>	max = hm.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.reduce((e1, e2)->e2);

		System.out.println("---------------------------------"+max);

		System.out.println("---------------------------------------------------------------------");
// sort Map<String,List<Integer>>
		
		
		Map<String,List<Integer>> newHm = new LinkedHashMap<>();
		
		newHm.put("oneToTen", Arrays.asList(1,2,3,10,4,5,6,7,8,9));
		newHm.put("elevenToTwenty", Arrays.asList(11,12,14,13,16,17,18,19,20,15));
		newHm.put("twentyOneToThirty", Arrays.asList(24,21,22,23,25,26,27,28,29,30));
		newHm.put("thirtyOneToFourty", Arrays.asList(31,32,34,35,36,37,33,38,39,40));
		newHm.put("fourtyOneToFifty", Arrays.asList(41,42,50,43,44,45,46,47,48,49));
	
		
		newHm.entrySet().stream().
		forEach(entry->System.out.println(entry.getKey()+" : "+entry.getValue()));
	
		//newHm.entrySet().stream()
		
		
		System.out.println("---------------------------------------------------------------------");

		Map<String, List<Integer>> sortedListInTheMap = newHm.entrySet().stream()
		.collect(Collectors.toMap(Map.Entry::getKey, 
				entry->entry.getValue().stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList())));
	
	
		sortedListInTheMap.entrySet().stream().forEach(entry->System.out.println(entry.getKey()+" : "+entry.getValue()));
		
		
		


		hm.entrySet().stream().sorted(Map.Entry.comparingByValue()).
		collect(Collectors.toMap(Map.Entry::getKey,entry->entry.getValue(),(o1,o2)->o1,LinkedHashMap::new));
		
		Entry<String, Integer> minValue = hm.entrySet().stream().sorted(Map.Entry.comparingByValue())
		.findFirst().get();
		System.out.println("***********"+minValue);
		
		Entry<String, Integer> maxValue = hm.entrySet().stream().max(Map.Entry.comparingByValue())
		.get();
		System.out.println("**********"+maxValue);
		
		
		/*
		 * //creating object by using new keyword..... StartTest t = new StartTest();
		 * 
		 * //creating object by using newInstance method...will throws
		 * InstantiationException StartTest t1 = (StartTest)
		 * Class.forName("StartTest").newInstance();
		 * 
		 * t1.test(); //crating object by using clone method... clone will create the
		 * copy of an existing object...clone method will throws
		 * CloneNotSupportedExceptopn.... StartTest t2 = (StartTest) t1.clone();
		 */
		
		IntSummaryStatistics summery = hm.values().stream().mapToInt(Integer::intValue).summaryStatistics();
		
		System.out.println("*************"+summery);
		

// sort list in the map and maintain the insertion order of the map....		
		
		Map<String,List<Integer>> sortedListOfMap = newHm.entrySet().stream().collect(Collectors.
				toMap(Map.Entry::getKey, 
						entry->entry.getValue().stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList())
						,(e1,e2)->e1,LinkedHashMap::new));
		System.out.println("******************"+sortedListOfMap);
		sortedListOfMap.forEach((key,value)-> System.out.println(key +" : "+ value));
		
	}
	
	public void test() {
		System.out.println("Testing....");
	}

}
