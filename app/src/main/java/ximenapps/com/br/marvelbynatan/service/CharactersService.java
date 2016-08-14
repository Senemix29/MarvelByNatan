package ximenapps.com.br.marvelbynatan.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ximenapps.com.br.marvelbynatan.model.CharactersDataWrapper;

/**
 * Created by Natan on 04/08/2016.
 */
public interface CharactersService {

    @GET("characters")
    Call<CharactersDataWrapper> listCharacters();
}
