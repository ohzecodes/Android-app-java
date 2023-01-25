package com.example.studentprogresstracking.UI.COURSES.parts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.util;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.Instructor;

public class AddOrModifyInstructor extends AppCompatActivity {


    private Repository repo;
    int cid;
    int insid;
    private EditText emailEditText;
    private EditText nameEditText;
    private EditText phoneEditText;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_instructors);
        emailEditText = findViewById(R.id.email);
        nameEditText = findViewById(R.id.AddInstructorname);
        phoneEditText = findViewById(R.id.Phone);
         repo= new Repository(getApplication());
         cid = getIntent().getIntExtra("Cid",-1);
        ((Button) findViewById(R.id.savenew)).setOnClickListener(e->{
            save();
        });
         insid = getIntent().getIntExtra("id", -1);


        if (insid !=-1 ){
//            youre modify existing
            Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
            String name = getIntent().getStringExtra("name");
            String email = getIntent().getStringExtra("email");
            String phone = getIntent().getStringExtra("phone");
            emailEditText.setText(email);
            nameEditText.setText(name);
            phoneEditText.setText(phone);
        }
    }



    void save() {
        if (!validate()) {
            Toast.makeText(this, "Invalid fields ", Toast.LENGTH_SHORT).show();
            return;
        }

            Instructor n = new Instructor(0,
                    emailEditText.getText().toString(),
                    nameEditText.getText().toString(),
                    phoneEditText.getText().toString(),
                    cid);

        if (insid == -1) {
            repo.insert(n);
        }else {
            n.setInstructorID(insid);
            repo.update(n);
        }


        finish();
    }

    boolean validate(){
        String fullName = nameEditText.getText().toString();
        boolean email = util.isValidEmail(emailEditText.getText().toString());
        boolean phone = util.isValidPhoneNumber(phoneEditText.getText().toString());
        boolean name = (!TextUtils.isEmpty(fullName) && fullName.matches("^[a-zA-Z\\s]*$"));
        return email && phone && name;
    }

}