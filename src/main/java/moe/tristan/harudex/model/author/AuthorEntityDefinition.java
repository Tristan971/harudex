package moe.tristan.harudex.model.author;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.Relationship;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
public interface AuthorEntityDefinition extends WithResult {

    AuthorData getData();

    List<Relationship> getRelationships();

}
