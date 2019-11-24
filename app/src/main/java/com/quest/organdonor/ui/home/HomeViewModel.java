package com.quest.organdonor.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quest.organdonor.constants.Api;
import com.quest.organdonor.ui.home.pojo.AllProducts;
import com.quest.organdonor.ui.home.pojo.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private MutableLiveData<ArrayList<ProductsModel>> mPostsList;
    private static final String TAG = "mwenda";

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }




    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<Message>> productsModelList;

    //we will call this method to get the data
    public LiveData<List<Message>> getProducts() {
        //if the list is null
        if (productsModelList == null) {
            productsModelList = new MutableLiveData<List<Message>>();
            //we will load it asynchronously from server in this method
            loadProducts();
        }

        //finally we will return the list
        return productsModelList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadProducts() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<AllProducts> call = api.getProducts();

        call.enqueue(new Callback<AllProducts>() {
            @Override
            public void onResponse(Call<AllProducts> call, Response<AllProducts> response) {
                //finally set the list to our mutable live data
                productsModelList.setValue(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<AllProducts> call, Throwable t) {

            }
        });
    }
}