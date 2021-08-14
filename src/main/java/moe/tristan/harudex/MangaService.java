package moe.tristan.harudex;

import moe.tristan.harudex.model.manga.MangaSearchCriteria;
import moe.tristan.harudex.model.manga.MangaSearchResponse;

public interface MangaService {

    MangaSearchResponse search();

    MangaSearchResponse search(MangaSearchCriteria criteria);

}
