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

public class SearchResults extends AppCompatActivity implements ImageGridAdapter.InfiniteScrollCallback {
    private int currentPage = 1;
    private ImageGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        createRecyclerView();

        getData(getFormattedSearchTerms());
    }

    private String getFormattedSearchTerms() {
        String searchText = getIntent().getStringExtra(MainActivity.SEARCH_TEXT_EXTRA);
        return searchText.replaceAll("\\s+", "+");
    }

    private void getData(String searchText) {
        //https://pixabay.com/api/?key=8676171-014b260fab275b497c9fd8142&q=kittens&per_page=5&image_type=photo
        Map<String, String> parameters = new HashMap<>();
        Log.e("SearchResults", "searchText: " + searchText);
        parameters.put("key", "8676171-014b260fab275b497c9fd8142");
        parameters.put("q", searchText);
        parameters.put("per_page", "60");
        parameters.put("page", String.valueOf(currentPage));

        RetrofitClient
                .getPixabayService()
                .baseApiCall(parameters)
                .enqueue(new Callback<ImageList>() {
                    @Override
                    public void onResponse(Call<ImageList> call, Response<ImageList> response) {
                        if (currentPage == 1) {
                            adapter.setImageList(response.body());
                        } else {
                            adapter.addToHits(response.body());
                        }
                        adapter.notifyDataSetChanged();
                        Log.i("SearchResults", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<ImageList> call, Throwable t) {
                        Log.e("SearchResults", t.getMessage());
                    }
                });
    }

    private void createRecyclerView() {
        RecyclerView imagesRecyclerView = findViewById(R.id.images_recycler_view);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, getResources().getInteger(R.integer.num_images_per_row));
        imagesRecyclerView.setLayoutManager(layoutManager);

        adapter = new ImageGridAdapter(this);
        imagesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void reachedBottom() {
        currentPage++;
        getData(getFormattedSearchTerms());
    }
}
