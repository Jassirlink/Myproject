package tk.cybayz.myproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements  View.OnClickListener {

       private Button button2;
       private TextView textView5;
       private EditText editText3;
       private EditText editText4;
       private FirebaseAuth firebaseAuth;
       private ProgressDialog progressDialog;
       private TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressDialog = new ProgressDialog(MainActivity.this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){

            finish();
            startActivity(new Intent(getApplicationContext(),profile.class));

        }


        button2    = (Button) findViewById(R.id.button2);
        textView5 = (TextView)findViewById(R.id.textView5);
        editText3  = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        textView4 = (TextView)findViewById(R.id.textView4);

        button2.setOnClickListener(this);
        textView4.setOnClickListener(this);
        


    }


    private void registerUser() {
        String email = editText3.getText().toString().trim();
        String password = editText4.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(this, "pls enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "pls enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), login.class));
                }
                else
                    {
                    Toast.makeText(MainActivity.this, "not sucess", Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();



            }


        });





        progressDialog.setMessage("Registering User");
        progressDialog.show();
        progressDialog.setTitle("register user");



    }


    @Override
    public void onClick(View v) {

            if(v == button2){
                registerUser();
            }
            if(v == textView4)
            {
                startActivity(new Intent(this,login.class));
            }


    }
}
