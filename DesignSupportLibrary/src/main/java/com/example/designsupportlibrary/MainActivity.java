package com.example.designsupportlibrary;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.designsupportlibrary.weather.model.WeatherData;
import com.example.designsupportlibrary.weather.rest.WeatherClient;

import java.util.Date;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements Callback<WeatherData> {

    @InjectView(R.id.drawer_layout)
    protected DrawerLayout drawer_layout;

    @InjectView(R.id.nav_view)
    protected NavigationView nav_view;

    @InjectView(R.id.toolbar)
    protected Toolbar toolbar;

    @InjectView(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsing_toolbar;

    @InjectView(R.id.temperature_text)
    protected TextView temperature_text;

    @InjectView(R.id.mintem_text)
    protected TextView mintem_text;

    @InjectView(R.id.max_text)
    protected TextView max_text;

    @InjectView(R.id.sunrise_text)
    protected TextView sunrise_text;

    @InjectView(R.id.sunrset_text)
    protected TextView sunrset_text;

    @InjectView(R.id.humidity_text)
    protected TextView humidity_text;

    @InjectView(R.id.wind_text)
    protected TextView wind_text;

    @InjectView(R.id.pressure_text)
    protected TextView pressure_text;

    @InjectView(R.id.main_text)
    protected TextView main_text;

    @InjectView(R.id.desc_text)
    protected TextView desc_text;

    @InjectView(R.id.refresh_button)
    protected FloatingActionButton refresh_button;

    @InjectView(R.id.weather_image)
    protected ImageView weather_image;

    private WeatherClient mWeatherClient;

    private String mCityName = "Tokyo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setupDrawerContent(nav_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.city_icon);

        collapsing_toolbar.setTitle(mCityName);

        mWeatherClient = new WeatherClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadWeatherData();
    }

    @OnClick(R.id.refresh_button)
    public void loadWeatherData() {
        Call<WeatherData> data = mWeatherClient.getWeather(mCityName);

        data.enqueue(this);

        try {
            data.execute();
        } catch (Exception e) {}
    }

    private String getTemperaturText(Double t) {
        return "<b><big>" + (int)(t - 273.15) +"</big></b><sup><small>0</small></sup>" ;
    }

    private void setWeatherInfo(WeatherData data) {
        collapsing_toolbar.setTitle(data.name + "(" + data.sys.country + ")");

        temperature_text.setText(Html.fromHtml(getTemperaturText(data.main.temp)));
        mintem_text.setText(Html.fromHtml("Min : " + getTemperaturText(data.main.temp_min)));
        max_text.setText(Html.fromHtml("Max : " + getTemperaturText(data.main.temp_max)));

        sunrise_text.setText("Sunrise : " + new Date(data.sys.sunrise*1000L).toString());
        sunrset_text.setText("Sunset : " + new Date(data.sys.sunset*1000L).toString());

        humidity_text.setText("Humidity : " + data.main.humidity);

        pressure_text.setText("Pressure : " + data.main.pressure + " hPa");

        wind_text.setText("Wind speed : " + data.wind.speed + " meter/sec");

        if (data.weather.size() > 0) {
            String icon = data.weather.get(0).icon;
            Glide.with(this).load("http://openweathermap.org/img/w/" + icon + ".png")
            .into(weather_image);

            main_text.setText(data.weather.get(0).main);
            desc_text.setText(data.weather.get(0).description);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            drawer_layout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer_layout.closeDrawers();
                String city = menuItem.getTitle().toString();
                if (!city.isEmpty()) {
                    mCityName = city;
                    loadWeatherData();
                }
                return true;
            }
        });
    }

    @Override
    public void onResponse(final Response<WeatherData> response, Retrofit retrofit) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WeatherData data = response.body();
                if (data != null) {
                    setWeatherInfo(data);
                    Snackbar.make(refresh_button, "Weather data loaded", Snackbar.LENGTH_LONG)
                            .setAction("Ok", null).show();
                } else {
                    Snackbar.make(refresh_button, "Error in getting weather info, check the internet connection and appId", Snackbar.LENGTH_LONG)
                            .setAction("Ok", null).show();
                }
            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {
        Snackbar.make(refresh_button, "Failed to load weather data: " + throwable.getMessage(), Snackbar.LENGTH_LONG)
        .setAction("Reload", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWeatherData();
            }
        }).show();
    }
}
