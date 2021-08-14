package moe.tristan.harudex;

import java.util.UUID;

import moe.tristan.harudex.model.manga.MangaCreateRequest;
import moe.tristan.harudex.model.manga.MangaEntity;
import moe.tristan.harudex.model.manga.MangaSearchCriteria;
import moe.tristan.harudex.model.manga.MangaSearchResponse;

public interface MangaService {

    MangaSearchResponse search();

    MangaSearchResponse search(MangaSearchCriteria criteria);

    MangaEntity findById(UUID id);

    MangaEntity create(MangaCreateRequest createRequest);

}
