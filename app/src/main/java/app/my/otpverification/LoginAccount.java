package app.my.otpverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginAccount extends AppCompatActivity {

    int count = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_account);

        EditText newUserName = findViewById(R.id.newUserName);
        EditText newPassword = findViewById(R.id.newPassword);

        TextView forgotPass = findViewById(R.id.forgotPass);

        Button btnLogIN = findViewById(R.id.btnLogIn);

        Intent intent = getIntent();
        String newUser = intent.getStringExtra("userName");
        String newPass = intent.getStringExtra("password");

        btnLogIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newName = newUserName.getText().toString();
                String newName1 = newPassword.getText().toString();
                if (newName.isEmpty() || newName1.isEmpty()){
                    Toast.makeText(LoginAccount.this, "Fill all the requirements", Toast.LENGTH_SHORT).show();
                }else {
                    if (newUser.equals(newUserName.getText().toString()) && newPass.equals(newPassword.getText().toString())) {
                        Toast.makeText(LoginAccount.this, "Welcome...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginAccount.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginAccount.this, "Invalid user or pass\nAttempt " + count + " x", Toast.LENGTH_SHORT).show();
                        count--;
                        if (count == 0) {
                            btnLogIN.setEnabled(false);
                            forgotPass.setVisibility(View.VISIBLE);
                            forgotPass.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(LoginAccount.this, OtpSendActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }
            }
        });
    }
}