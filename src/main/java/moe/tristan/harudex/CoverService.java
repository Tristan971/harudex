package moe.tristan.harudex;

import java.io.InputStream;
import java.util.UUID;

import moe.tristan.harudex.model.cover.CoverEntity;
import moe.tristan.harudex.model.cover.CoverSearchResponse;

public interface CoverService {

    CoverEntity findById(UUID id);

    CoverSearchResponse search();

    CoverEntity upload(UUID mangaId, InputStream content);

}
