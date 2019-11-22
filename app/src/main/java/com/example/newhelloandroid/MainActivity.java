package com.example.newhelloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFirestore();
    }

    public void testFirestore(){
        final String TAG = "testFirestore";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Resturant")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Log.d(TAG, document.getId() + " => " + document.get("name"));
                                Log.d(TAG, document.getId() + " => " + document.get("img"));
                            }
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });

//        DocumentReference docRef = db.collection("Resturant").document("name");
    }



    public void Login(View view) {

        Intent intent = new Intent(this, Restaurant.class);
        EditText editText2 = (EditText) findViewById(R.id.name);
        String name = editText2.getText().toString();
        EditText editText3 = (EditText) findViewById(R.id.password);
        String password = editText3.getText().toString();

        if(name.equals("user")&&password.equals("1234")){
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Loged in",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"Rock in Fail",Toast.LENGTH_SHORT).show();
        }
    }





}
