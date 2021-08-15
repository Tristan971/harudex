package moe.tristan.harudex.model.common;

import java.util.Optional;
import java.util.Set;

public interface WithIncludes {

    Optional<Set<String>> getIncludes();

}
