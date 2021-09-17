package moe.tristan.harudex.model.cover;

import java.util.List;

import org.immutables.value.Value.Immutable;

import moe.tristan.harudex.DataClass;
import moe.tristan.harudex.model.common.Paged;

@Immutable
@DataClass
public interface CoverSearchResponseDefinition extends Paged {

    List<CoverData> getData();

}
