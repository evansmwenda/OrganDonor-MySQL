package com.quest.organdonor.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.quest.organdonor.App.API;
import com.quest.organdonor.App.Constants;
import com.quest.organdonor.Models.PostModel;
import com.quest.organdonor.Models.ProductsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //private MutableLiveData<ArrayList<ProductsModel>> mPostsList;
    public ArrayList<ProductsModel> mPostsList= new ArrayList<>();
    private static final String TAG = "mwenda";

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<ProductsModel>> getAllPosts (){
        //fetch posts from api and submit to view

        String URL = API.CARFIX_ALLPRODUCTS;

        AndroidNetworking.get(URL)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, "onResponse: "+response.toString());
                        boolean status = response.optBoolean("success");
                        if(status){
                            JSONArray jsonArrayMessage = response.optJSONArray("message");
                            for(int i=0;i<jsonArrayMessage.length();i++){
                                try {
                                    JSONObject jsonObject = jsonArrayMessage.getJSONObject(i);
                                    String id = jsonObject.optString("id");
                                    String title= jsonObject.optString("title");
                                    String cat_id= jsonObject.optString("cat_id");
                                    String description= jsonObject.optString("description");
                                    String price= jsonObject.optString("price");
                                    String image= jsonObject.optString("image");
                                    String random= jsonObject.optString("random");

                                    mPostsList.add(new ProductsModel(id,title,cat_id,description,price,image,random));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                         }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "onError: "+error.getErrorBody());
                    }
                });

        MutableLiveData<List<ProductsModel>> data =  new MutableLiveData<>();

        data.setValue(mPostsList);


        return data;
    }
}