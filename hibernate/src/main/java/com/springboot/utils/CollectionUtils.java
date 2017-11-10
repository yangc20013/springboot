package com.springboot.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils {
	public static <T> List<T> getCollection(Iterable<T> ita) {
		List<T> result = new ArrayList<T>();
		if (ita != null) {
			Iterator<T> itr = ita.iterator();
			while (itr.hasNext()) {
				result.add(itr.next());
			}
		}

		return result;
	}
}
