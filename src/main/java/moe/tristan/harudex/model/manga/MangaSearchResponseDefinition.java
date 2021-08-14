package moe.tristan.harudex.model.manga;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.Paged;

@Immutable
@DataClass
interface MangaSearchResponseDefinition extends Paged {

    List<MangaEntity> getResults();

}
