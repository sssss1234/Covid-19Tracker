package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.covid_19tracker.databinding.ActivityCountriesBinding;
import com.example.covid_19tracker.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private  int positionCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+Countries.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.tvCountry.setText(Countries.countryModelsList.get(positionCountry).getCountry());
        binding.tvCases.setText(Countries.countryModelsList.get(positionCountry).getCases());
        binding.tvRecovered.setText(Countries.countryModelsList.get(positionCountry).getRecovered());
        binding.tvCritical.setText(Countries.countryModelsList.get(positionCountry).getCritical());
        binding.tvActive.setText(Countries.countryModelsList.get(positionCountry).getActive());
        binding.tvTodayCases.setText(Countries.countryModelsList.get(positionCountry).getTodayCases());
        binding.tvDeaths.setText(Countries.countryModelsList.get(positionCountry).getDeaths());
        binding.tvTodayDeaths.setText(Countries.countryModelsList.get(positionCountry).getTodayDeaths());
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}