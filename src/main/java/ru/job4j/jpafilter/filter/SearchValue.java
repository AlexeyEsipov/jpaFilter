package ru.job4j.jpafilter.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchValue {
    private Filter filter;
    private PageParam pageParam;
}
