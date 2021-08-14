package moe.tristan.harudex.model.common;

public interface SearchResponse<T> {

    T getResults();

    int getLimit();

    int getOffset();

    int getTotal();

}
