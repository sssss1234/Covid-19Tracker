package com.example.covid_19tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19tracker.databinding.ActivityMainBinding;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fetchData();
    }

    public void trackCountries(View view) {
        startActivity(new Intent(this,Countries.class));

    }

    private void fetchData()
    {
        String url="https://corona.lmao.ninja/v2/all/";
        binding.loader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject=new JSONObject(response);
                binding.tvCases.setText(jsonObject.getString("cases"));
                binding.tvRecovered.setText(jsonObject.getString("recovered"));
                binding.tvCritical.setText(jsonObject.getString("critical"));
                binding.tvActive.setText(jsonObject.getString("active"));
                binding.tvTodayCases.setText(jsonObject.getString("todayCases"));
                binding.tvTotalDeaths.setText(jsonObject.getString("todayDeaths"));
                binding.tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                binding.tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                binding.piechart.addPieSlice(new PieModel("Cases",Integer.parseInt(binding.tvCases.getText().toString()
                ), Color.parseColor("#FFA726")));
                binding.piechart.addPieSlice(new PieModel("Recovered",Integer.parseInt(binding.tvRecovered.getText().toString()
                ), Color.parseColor("#66BB6A")));
                binding.piechart.addPieSlice(new PieModel("Deaths",Integer.parseInt(binding.tvTotalDeaths.getText().toString()
                ), Color.parseColor("#EF5350")));
                binding.piechart.addPieSlice(new PieModel("Active",Integer.parseInt(binding.tvActive.getText().toString()
                ), Color.parseColor("#29B6F6")));
                binding.piechart.startAnimation();
                binding.loader.stop();
                binding.loader.setVisibility(View.GONE);
                binding.scrollStats.setVisibility(View.VISIBLE);


            } catch (JSONException e) {
                e.printStackTrace();
                binding.loader.stop();
                binding.loader.setVisibility(View.GONE);
                binding.scrollStats.setVisibility(View.VISIBLE);
            }

        }, error -> Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}