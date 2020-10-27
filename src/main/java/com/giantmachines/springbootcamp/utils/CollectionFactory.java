package com.giantmachines.springbootcamp.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionFactory {

  public static <T> List<T> toList(Iterable<T> iterable) {
    List<T> result = new ArrayList<>();

    iterable.forEach(result::add);

    return result;
  }
}
