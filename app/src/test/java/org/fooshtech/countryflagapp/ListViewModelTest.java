package org.fooshtech.countryflagapp;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.fooshtech.countryflagapp.model.CountriesService;
import org.fooshtech.countryflagapp.model.CountryModel;
import org.fooshtech.countryflagapp.viewmodel.ListViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Scheduler;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();


    @Mock
    CountriesService countriesService;

    @InjectMocks
    ListViewModel listViewModel =new ListViewModel();

    private Single<List<CountryModel>> testSingle;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCountriesSuccess(){
        CountryModel country = new CountryModel("countryname","capital","flag");
        ArrayList<CountryModel> countryiesList = new ArrayList<>();
        countryiesList.add(country);

        testSingle = Single.just(countryiesList);

        Mockito.when(countriesService.getCountries()).thenReturn(testSingle);

        listViewModel.refresh();

        Assert.assertEquals(1,listViewModel.countries.getValue().size());
        Assert.assertEquals(false,listViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,listViewModel.loading.getValue());
    }

    @Before
    public void setUp() throws Exception {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(new Executor() {
                    @Override
                    public void execute(Runnable command) {
                        command.run();
                    }
                }, true);
            }
        };

      RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
      RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> immediate);

    }
}
