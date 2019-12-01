package com.example.newhelloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Restaurant extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> restaurantsList = new ArrayList<String>();
    private ArrayList<String> pic = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        System.out.println("test00200"+restaurantName()[0]);
//         specify an adapter (see also next example)
//
//        String[] mydataset = {"j","g"};
        final String TAG = "testFirestore";
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Resturant").orderBy("name").limit(9)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                              Log.d(TAG, document.getId() + " => " + document.getData());
//                                Log.d(TAG, document.getId() + " => " + document.get("name"));
//                                Log.d(TAG, document.getId() + " => " + document.get("img"));
                                restaurantsList.add(document.get("name").toString());
                                pic.add(document.get("img").toString());
                                id.add(Integer.parseInt(document.get("id").toString()));
                            }
                            Integer[] drawableArray = {R.drawable.saitama, R.drawable.saitama, R.drawable.saitama,
                                    R.drawable.saitama, R.drawable.saitama,R.drawable.saitama, R.drawable.saitama, R.drawable.saitama,
                                    R.drawable.saitama, R.drawable.saitama};
                            String[] myData = restaurantsList.toArray(new String[0]);
                            String[] myData2 = pic.toArray(new String[0]);
                            mAdapter = new CustomAdepter(Restaurant.this,drawableArray,myData,myData);
//                            mAdapter = new MyAdapter(myData);
                            recyclerView.setAdapter(mAdapter);
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
