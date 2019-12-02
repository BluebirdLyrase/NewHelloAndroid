package com.example.newhelloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity{
    String ResnameDB;
    String imageViewDB;
    String starDB;
    String statusDB;
    String distanceDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("Detail Created");
        Bundle extras = getIntent().getExtras();
        String ResID = extras.getString("positionID");
        System.out.println("positionID = "+ResID);
        createLayout(ResID);
        super.onCreate(savedInstanceState);



    }

    public void createLayout(String ResID){
        final String TAG = "testFirestore";
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
                            setContentView(R.layout.activity_detail);
                            TextView Resname = (TextView) findViewById(R.id.Name);
                            TextView star = (TextView) findViewById(R.id.star);
                            TextView status = (TextView) findViewById(R.id.status);
                            TextView distance = (TextView) findViewById(R.id.distance);
                            ImageView imageView = (ImageView) findViewById(R.id.imageView);
                            Resname.setText(ResnameDB);
                            star.setText("Rating:"+starDB);
                            distance.setText("Distance:"+distanceDB);
                            status.setText(statusDB);
                            Picasso.with(Detail.this).load(imageViewDB).into(imageView);
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });


    }

}
