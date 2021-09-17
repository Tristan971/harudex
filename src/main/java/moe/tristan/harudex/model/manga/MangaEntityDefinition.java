package moe.tristan.harudex.model.manga;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.WithResult;

@Immutable
@DataClass
public interface MangaEntityDefinition extends WithResult {

    MangaData getData();

}
