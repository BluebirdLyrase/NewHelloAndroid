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

    public void sendMessage(View view) {
        Intent intent = new Intent(this, disaply.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();


        Log.d("editText",message);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Send Message Clicked",Toast.LENGTH_SHORT).show();
    }

    public void wrong(View view) {
        Toast.makeText(getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
    }

    public void testFirestore(){
        final String TAG = "testFirestore";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG,"Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void Login(View view) {

        Intent intent = new Intent(this, disaply.class);
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

    public void openListPage(View view){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }




}
