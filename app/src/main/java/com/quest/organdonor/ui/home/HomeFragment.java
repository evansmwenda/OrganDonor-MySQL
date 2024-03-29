package com.quest.organdonor.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quest.organdonor.adapters.MyAdapter;
import com.quest.organdonor.R;
import com.quest.organdonor.ui.home.pojo.Message;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView textView = view.findViewById(R.id.text_home);
        recyclerView =view.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(this,);
//        recyclerView.setAdapter(mAdapter);



        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        homeViewModel.getProducts().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                Log.d("mwenda", "onChanged: "+messages.toString());
                Toast.makeText(getActivity(), "data fetched"+messages.get(0).toString(), Toast.LENGTH_SHORT).show();
                // specify an adapter (see also next example)
                mAdapter = new MyAdapter(getActivity(),messages);
                recyclerView.setAdapter(mAdapter);

            }


        });
    }

    //add code to display recycler view with its items
}