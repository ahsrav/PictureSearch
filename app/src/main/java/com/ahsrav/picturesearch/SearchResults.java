package com.ahsrav.picturesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        Log.e("SearchResults", "searchText: " + searchText);
        parameters.put("key", "8676171-014b260fab275b497c9fd8142");
        parameters.put("q", searchText);
        parameters.put("per_page", "60");

        RetrofitClient
                .getPixabayService()
                .baseApiCall(parameters)
                .enqueue(new Callback<ImageList>() {
                    @Override
                    public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                        createRecyclerView(response.body());
                        Log.i("SearchResults", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ImageList> call, Throwable t) {
                        Log.e("SearchResults", t.getMessage());
                    }
                });
    }

    private void createRecyclerView(ImageList imageList) {
        RecyclerView imagesRecyclerView = findViewById(R.id.images_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.num_images_per_row));
        imagesRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ImageGridAdapter(imageList);
        imagesRecyclerView.setAdapter(adapter);
    }
}
