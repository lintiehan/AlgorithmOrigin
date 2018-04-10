package Problem;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SortMapByValues {
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("THREE", 3);
		map.put("TWO", 1);
		map.put("ONE", 2);
		sortMap(map);
	}

	private static void sortMap(Map<String, Integer> map) {
	 
		Set<Entry<String, Integer>> mapEntries = map.entrySet();
		System.out.println("Values and Keys before sorting ");
		for (Entry<String, Integer> entey : mapEntries) {
			System.out.println(entey.getKey() + "-" + entey.getValue());
		}

		List<Entry<String, Integer>> aList = new LinkedList<>(mapEntries);

		Collections.sort(aList, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		Map<String, Integer> map2 = new LinkedHashMap<>();
		for (Entry<String, Integer> entry : aList) {
			map2.put(entry.getKey(), entry.getValue());
		}
		// printing values after soring of map
		System.out.println("Value " + " - " + "Key");
		for (Entry<String, Integer> entry : map2.entrySet()) {
			System.out.println(entry.getValue() + " - " + entry.getKey());
		}

	}
}
