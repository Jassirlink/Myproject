package tk.cybayz.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {




     private Button button2;
     private TextView textView1;
     private EditText editText3;
     private EditText  editText4;
     private TextView textView6;
     private ProgressDialog progressDialog;
     private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        button2 =   (Button)  findViewById(R.id.button2);
        textView1 = (TextView)findViewById(R.id.textView1);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        textView6 = (TextView)findViewById(R.id.textView6);
        progressDialog = new ProgressDialog(this);
         button2.setOnClickListener(this);
         textView6.setOnClickListener(this);
         firebaseAuth =  FirebaseAuth.getInstance();

         if(firebaseAuth.getCurrentUser()!= null){


             startActivity(new Intent(getApplicationContext(),profile.class));
             finish();
         }

    }

    private void userlogin()
    {
        String email = editText3.getText().toString().trim();
        String password = editText4.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"pls enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"pls enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("logging user.....");
        progressDialog.show();
       // progressDialog.setTitle("register user");


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                   if(task.isSuccessful()){
                       startActivity(new Intent(getApplicationContext(),profile.class));
                       finish();
                   }

            }
        });




    }

    @Override
    public void onClick(View v) {
        if(v == button2)
        {
            userlogin();
        }

        if(v == textView6)
        {
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }



    }
}


