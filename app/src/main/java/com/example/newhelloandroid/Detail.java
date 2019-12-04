package com.example.newhelloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {
    String ResnameDB;
    String imageViewDB;
    private RecyclerView.LayoutManager layoutManager;
    String starDB;
    String statusDB;
    String distanceDB;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> aprice = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("Detail Created");
        Bundle extras = getIntent().getExtras();
        String ResID = extras.getString("positionID");
        System.out.println("positionID = "+ResID);
        createLayout(ResID);
        createFoodcard(ResID);
        super.onCreate(savedInstanceState);



    }

    public void createLayout(String ResID){
        setContentView(R.layout.activity_detail);
        final String TAG = "LayoutFirestore";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Resturant").document(ResID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot  document = task.getResult();
                                ResnameDB = document.get("name").toString();
                                imageViewDB = document.get("img").toString();
                                starDB = document.get("star").toString();
                                String statusboolean = document.get("status").toString();
                                if(statusboolean.equals("true")){
                                    statusDB = "open";
                                }else{
                                    statusDB = "close";
                                }
                                distanceDB = document.get("distance").toString();
                                System.out.println("Data from firebase"+ResnameDB+starDB+statusDB);
                            TextView Resname = (TextView) findViewById(R.id.Name);
                            TextView star = (TextView) findViewById(R.id.star);
                            TextView status = (TextView) findViewById(R.id.status);
                            TextView distance = (TextView) findViewById(R.id.distance);
                            ImageView imageView = (ImageView) findViewById(R.id.imageView);
                            Resname.setText(ResnameDB);
                            star.setText("Rating:"+starDB);
                            distance.setText("Distance:"+distanceDB+"km");
                            status.setText(statusDB);
                            Picasso.with(Detail.this).load(imageViewDB).into(imageView);
                            ////////////////////////foodcard/////////////////////////////
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });


    }

    public void createFoodcard(String ResID) {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final String TAG = "testFirestore foodcard";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Resturant").document(ResID).collection("Food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name.add(document.get("name").toString());
                                aprice.add(document.get("price").toString());
                            }
                            String food[] = name.toArray(new String[0]);
                            String price[] = aprice.toArray(new String[0]);
                            mAdapter = new FoodAdepter(food,price);
                            recyclerView.setAdapter(mAdapter);;
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });



    }


}
