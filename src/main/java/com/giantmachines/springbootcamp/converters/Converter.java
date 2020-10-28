package com.giantmachines.springbootcamp.converters;

@FunctionalInterface
public interface Converter<I, O> {

  O convert(I input);

}
