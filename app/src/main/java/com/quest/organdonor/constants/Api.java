package com.quest.organdonor.constants;

import com.quest.organdonor.ui.home.pojo.AllProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://skytoptrial.000webhostapp.com/functions/";

    @GET("Products/allproducts.php")
    Call<AllProducts> getProducts();
}
