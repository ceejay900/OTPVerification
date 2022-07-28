package app.my.otpverification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText confirmPassword = findViewById(R.id.confirmPassword);

        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnEnter = findViewById(R.id.btnEnter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();

                if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()){
                    Toast.makeText(CreateAccount.this, "Fill all the requirements", Toast.LENGTH_SHORT).show();
                }else {
                    if (pass.length() == confirmPass.length()) {
                        if (pass.length() <= 7) {
                            Toast.makeText(CreateAccount.this, "Password is too short\nMust be 8 length above", Toast.LENGTH_SHORT).show();
                        }else {
                            openDialogForContact();
                        }
                    }else {
                        Toast.makeText(CreateAccount.this, "Password not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateAccount.this, LoginAccount.class);
                intent.putExtra("userName", userName.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void openDialogForContact() {
        BottomSheetDialog dialog = new BottomSheetDialog(CreateAccount.this, R.style.AppBottomSheetDialogTheme);
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.contact_info, (LinearLayout) findViewById(R.id.myLinear));
        dialog.setContentView(view);

        EditText contactNum = view.findViewById(R.id.contactNum);
        Button btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contactInfo = contactNum.getText().toString();
                if (contactInfo.isEmpty()){
                    Toast.makeText(CreateAccount.this, "Enter your number", Toast.LENGTH_SHORT).show();
                }else {
                    if (contactInfo.length() == 10){
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CreateAccount.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("contactNum", contactNum.getText().toString());
                        editor.commit();

                        Toast.makeText(CreateAccount.this, "Account Created", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(CreateAccount.this, "Invalid contact number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();;

    }
}