package com.hanabi.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class MapCounter<T> {
  HashMap<T, Integer> counter = new HashMap<>();

  public void increment(T t) {
    if (counter.containsKey(t)) {
      counter.put(t, counter.get(t) + 1);
    } else {
      counter.put(t, 1);
    }
  }

  public void decrement(T t) {
    if (counter.containsKey(t)) {
      Integer count = counter.get(t);
      if (count == 1) {
        counter.remove(t);
      } else {
        counter.put(t, count - 1);
      }
    }
  }

  public void setCount(T t, int count) {
    counter.put(t, count);
  }

  public int getCount(T t) {
    if (counter.containsKey(t)) {
      return counter.get(t);
    } else {
      return 0;
    }
  }

  public int size() {
    return counter.size();
  }

  public Set<T> keySet() {
    return counter.keySet();
  }

  public void removeIf(Predicate<? super T> filter) {
    Iterator<Map.Entry<T, Integer>> it = counter.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<T, Integer> entry = it.next();
      if (filter.test(entry.getKey())) {
        it.remove();
      }
    }
  }
}
