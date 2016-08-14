package ximenapps.com.br.marvelbynatan.service;

import android.os.Environment;
import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ximenapps.com.br.marvelbynatan.MainView;
import ximenapps.com.br.marvelbynatan.model.CharactersDataWrapper;

/**
 * Created by Natan on 04/08/2016.
 */
public class CharactersRestAdapter {
    private final String TAG = getClass().getCanonicalName();
    final private String BASE_URL = "http://gateway.marvel.com/v1/public/";
    private final String TS = "123";
    private final String APIKEY = "1ea5d5fe5b35118534e04253fb9f8481";
    private final String HASH = "23bbf3bbde891d0cbc0da1539e71e773";
    CharactersService charactersService;
    MainView mainView;

    public CharactersRestAdapter(MainView mainView) {
        this.mainView = mainView;
        OkHttpClient.Builder client = new OkHttpClient().newBuilder();

        client.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                HttpUrl httpUrl = originalRequest.url().newBuilder()
                        .addQueryParameter("ts", TS)
                        .addQueryParameter("apikey", APIKEY)
                        .addQueryParameter("hash", HASH)
                        .build();

                Request.Builder requestBuilder = originalRequest.newBuilder().url(httpUrl);
                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(logging);

        OkHttpClient okClient = client.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        charactersService = retrofit.create(CharactersService.class);
    }

    public void getCharacters() {
        charactersService.listCharacters().enqueue(new Callback<CharactersDataWrapper>() {
            @Override
            public void onResponse(Call<CharactersDataWrapper> call, Response<CharactersDataWrapper> response) {
                if (response.isSuccessful()) {
                    CharactersDataWrapper charactersDataWrapper = response.body();
                    mainView.showCharacters(charactersDataWrapper.getData().getResults());
                } else {
                    Log.d(TAG, "Erro - " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CharactersDataWrapper> call, Throwable t) {
                Log.d(TAG, "Erro: " + t.getMessage());
            }
        });
    }
}
