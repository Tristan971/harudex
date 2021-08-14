package moe.tristan.harudex.model.common;

public interface Paged {

    int getLimit();

    int getOffset();

    int getTotal();

}
