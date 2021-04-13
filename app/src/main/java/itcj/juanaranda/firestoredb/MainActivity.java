package itcj.juanaranda.firestoredb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     // FirebaseApp fb = FirebaseApp.initializeApp(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextView textViewName = findViewById(R.id.editTextTextPersonName2);
        TextView textViewLastName = findViewById(R.id.editTextTextPersonName);
        TextView textViewPhone = findViewById(R.id.editTextPhone);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            String name = textViewName.getText().toString();
            String lastName = textViewLastName.getText().toString();
            String phone = textViewPhone.getText().toString();

            User user = new User(name, lastName, phone);

            // Add a new document with a generated ID
            db.collection("example").document("deleteAfter").collection("users")
                    .add(user)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(getApplicationContext(),
                            "Documento agregado con ID: " + documentReference.getId(),
                            Toast.LENGTH_SHORT).show();
                        textViewName.setText("");
                        textViewLastName.setText("");
                        textViewPhone.setText("");
                    });
        });

    }
}