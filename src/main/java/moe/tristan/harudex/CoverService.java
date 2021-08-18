package moe.tristan.harudex;

import java.util.UUID;

import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.cover.CoverEntity;
import moe.tristan.harudex.model.cover.CoverSearchResponse;

public interface CoverService {

    CoverEntity findById(UUID id);

    CoverSearchResponse search();

    CoverEntity upload(AuthToken authToken, UUID mangaId, byte[] content);

}
