package moe.tristan.harudex.model.manga;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.Relationship;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
interface MangaSearchResultDefinition extends WithResult {

    MangaData getData();

    List<Relationship> getRelationships();

}
