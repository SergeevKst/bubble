package com.generation.application.mapper;

/**
 * Interface for mapping entities from entity to DTO and vice versa
 *
 * @param <F> From object
 * @param <T> To object
 */
public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
