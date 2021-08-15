package moe.tristan.harudex;

import moe.tristan.harudex.model.auth.AuthToken;
import moe.tristan.harudex.model.author.AuthorCreateRequest;
import moe.tristan.harudex.model.author.AuthorEntity;
import moe.tristan.harudex.model.author.AuthorSearchCriteria;
import moe.tristan.harudex.model.author.AuthorSearchResponse;

public interface AuthorService {

    AuthorEntity create(AuthToken authToken, AuthorCreateRequest createRequest);

    AuthorSearchResponse search(AuthorSearchCriteria criteria);

}
