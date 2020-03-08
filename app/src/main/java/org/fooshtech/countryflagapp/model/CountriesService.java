package org.fooshtech.countryflagapp.model;

import org.fooshtech.countryflagapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesService {


    private static CountriesService instance;

    @Inject
    public CountriesApi api;


//    private CountriesApi api = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(CountriesApi.class);

    private CountriesService() {

        DaggerApiComponent .create().inject(this);

    }

    public static CountriesService getInstance() {
        if (instance == null) {
            instance = new CountriesService();

        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return api.getCountries();
    }

}
