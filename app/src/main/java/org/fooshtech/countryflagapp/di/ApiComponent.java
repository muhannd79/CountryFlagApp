package org.fooshtech.countryflagapp.di;

import org.fooshtech.countryflagapp.model.CountriesService;
import org.fooshtech.countryflagapp.viewmodel.ListViewModel;

import dagger.Component;
import dagger.Module;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel viewModel);

}
