package com.quest.organdonor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.quest.organdonor.Adapters.MyAdapter;
import com.quest.organdonor.App.API;
import com.quest.organdonor.Models.ProductsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Home2Activity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList<ProductsModel> mPostsList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        recyclerView=findViewById(R.id.recyclerView2);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(this,);
//        recyclerView.setAdapter(mAdapter);
        fetchData();
    }

    private void fetchData(){
        String URL = API.CARFIX_ALLPRODUCTS;
        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        AndroidNetworking.get(URL)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        pDialog.dismissWithAnimation();
                        Log.d("mwenda", "onResponseActivity: "+response.toString());
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

                                    mAdapter = new MyAdapter(Home2Activity.this,mPostsList);
                                    recyclerView.setAdapter(mAdapter);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        pDialog.dismissWithAnimation();
                        Log.d("mwenda", "onError: "+error.getErrorBody());
                    }
                });
    }

}
