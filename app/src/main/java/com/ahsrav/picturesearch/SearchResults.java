package com.ahsrav.picturesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ahsrav.picturesearch.models.ImageList;
import com.ahsrav.picturesearch.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String searchText = getIntent().getStringExtra(MainActivity.SEARCH_TEXT_EXTRA);
        String formattedSearchText = searchText.replaceAll("\\s+", "+");
        getData(formattedSearchText);
    }

    private void getData(String searchText) {
        //https://pixabay.com/api/?key=8676171-014b260fab275b497c9fd8142&q=kittens&per_page=5&image_type=photo
        Map<String, String> parameters = new HashMap<>();
        parameters.put("key", "8676171-014b260fab275b497c9fd8142");
        parameters.put("q", searchText);
        parameters.put("per_page", "5");

        RetrofitClient
                .getPixabayService()
                .baseApiCall(parameters)
                .enqueue(new Callback<ImageList>() {
                    @Override
                    public void onResponse(Call<ImageList> call, Response<ImageList> response) {

                        Log.i("SearchResults", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ImageList> call, Throwable t) {
                        Log.e("SearchResults", t.getMessage());
                    }
                });
    }
}
