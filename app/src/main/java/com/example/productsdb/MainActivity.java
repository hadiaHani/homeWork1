package com.example.productsdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText etProductName;
EditText edProductPrice;
FirebaseFirestore db =FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etProductName=findViewById(R.id.productName);
        edProductPrice=findViewById(R.id.productPrice);
    }
    public void saveToFirebase(View v){
String ProductName= etProductName.getText().toString();
String ProductPrice= edProductPrice.getText().toString();


        Map<String,Object> product = new HashMap<>();
        product.put("Product Name", ProductName);
        product.put("Product Price",ProductPrice);
        db.collection("products")
                .add(product)
        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("TAG","Data add successfully to DB:");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG","Failed to add to DB"+e);

            }
        })

        ;
    }
}