package moe.tristan.harudex.model.common;

import java.util.List;

public interface Entity<T> extends WithResult {

    T getData();

    List<Relationship> getRelationships();

}
