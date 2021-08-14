package moe.tristan.harudex.model.common;

import java.util.Optional;

public interface PageableRequest {

    Optional<Integer> getLimit();

    Optional<Integer> getOffset();

}
