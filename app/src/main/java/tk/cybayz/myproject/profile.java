package tk.cybayz.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private Button button3;
    private TextView textView7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth =  FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){

            finish();
            startActivity(new Intent(getApplicationContext(),login.class));

        }
        FirebaseUser user = firebaseAuth.getCurrentUser();


        button3 = (Button)findViewById(R.id.button3);
        textView7 = (TextView)findViewById(R.id.textView7);

        textView7.setText("Welcome" +user.getEmail());

        button3.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {


        if(v == button3)
        {
            firebaseAuth.signOut();
            finish();

            startActivity(new Intent(this,login.class));
        }

    }
}
