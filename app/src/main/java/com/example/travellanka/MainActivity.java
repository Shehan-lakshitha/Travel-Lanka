package com.example.travellanka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerViewProvince;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerViewProvince = findViewById(R.id.recycleViewProvince);

        recyclerView();
    }

    private void recyclerView() {
        //Query
        Query query = firebaseFirestore.collection("provinces");

        //RecyclerView
        FirestoreRecyclerOptions<ProvinceModel> options = new FirestoreRecyclerOptions.Builder<ProvinceModel>()
                .setQuery(query,ProvinceModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ProvinceModel, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull ProvinceModel model) {
                holder.provinceName.setText(model.getName());
                holder.imageURL.setImageDrawable(model.getImage());

            }
        };

        recyclerViewProvince.setHasFixedSize(true);
        recyclerViewProvince.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProvince.setAdapter(adapter);


    }

    //ViewHolder
    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        private TextView provinceName;
        private ImageView imageURL;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);

            provinceName = itemView.findViewById(R.id.provinceName);
            imageURL = itemView.findViewById(R.id.imageURL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}