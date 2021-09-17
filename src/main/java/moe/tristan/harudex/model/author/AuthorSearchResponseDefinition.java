package moe.tristan.harudex.model.author;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.Paged;

@Immutable
@DataClass
public interface AuthorSearchResponseDefinition extends Paged {

    List<AuthorData> getData();

}
