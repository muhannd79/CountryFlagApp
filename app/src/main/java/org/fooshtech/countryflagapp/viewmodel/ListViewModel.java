package org.fooshtech.countryflagapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.fooshtech.countryflagapp.di.DaggerApiComponent;
import org.fooshtech.countryflagapp.model.CountriesService;
import org.fooshtech.countryflagapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    public ListViewModel() {
      super();
        DaggerApiComponent.create().inject(this);
    }

    //public CountriesService countriesService = CountriesService.getInstance();

    @Inject
    public CountriesService countriesService;

    private CompositeDisposable disposable = new CompositeDisposable();

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<List<CountryModel>>();

    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();

    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    public void refresh() {

        fetchCountries();
    }

    private void fetchCountries() {

        loading.setValue(true);
        disposable.add(
                countriesService.getCountries()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {

                            @Override
                            public void onSuccess(List<CountryModel> countryModels) {

                                countries.setValue(countryModels);
                                countryLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                countryLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();

                            }
                        })
        );


    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
