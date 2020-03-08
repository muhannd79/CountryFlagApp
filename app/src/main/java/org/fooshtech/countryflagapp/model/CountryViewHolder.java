package org.fooshtech.countryflagapp.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import org.fooshtech.countryflagapp.R;
import org.fooshtech.countryflagapp.view.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.imageView)
    AppCompatImageView countryImage;

    @BindView(R.id.name)
    TextView countryName;

    @BindView(R.id.capital)
    TextView countryCapital;

    public CountryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(CountryModel country) {

        countryName.setText(country.getCountryname());
        countryCapital.setText(country.getCapital());
        Util.loadImage(countryImage,country.getFlag(),Util.getProgressDrawable(countryImage.getContext()));

    }
}
