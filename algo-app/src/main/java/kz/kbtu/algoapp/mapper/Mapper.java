package kz.kbtu.algoapp.mapper;

import org.springframework.stereotype.Component;

public interface Mapper<E, D> {
    D entityToDto(E e);
}
