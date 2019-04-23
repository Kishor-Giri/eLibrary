package elibrary.project.com.elibrary.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.UserModel;
import elibrary.project.com.elibrary.ui.dashboard.member.MemberDashboardActivity;
import elibrary.project.com.elibrary.ui.dashboard.staff.StaffDashboardActivity;
import elibrary.project.com.elibrary.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextInputLayout emailWrapper = findViewById(R.id.emailWrapper);
        final TextInputLayout passwordWrapper = findViewById(R.id.passwordWrapper);
        final TextView register = findViewById(R.id.register);
        Button btnLogin = findViewById(R.id.btnLogin);
        emailWrapper.setHint("Email");
        passwordWrapper.setHint("Password");


        //When user click login button
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
                @Override
                public void onClick(View v)
                {
                    hideKeyboard();
                    String email = emailWrapper.getEditText().getText().toString();
                    String password = passwordWrapper.getEditText().getText().toString();
                    if (!validateEmail(email))
                    {
                        emailWrapper.setError("Not a valid email address!");
                    }
                    else if (!validatePassword(password))
                    {
                        emailWrapper.setErrorEnabled(false);
                        passwordWrapper.setError("Not a valid password!");
                    }
                    else
                        {
                        emailWrapper.setErrorEnabled(false);
                        passwordWrapper.setErrorEnabled(false);
                        doLogin(email,password);
                    }
                }
        });

        //when user click on sign up button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 1;
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    //connect to server and check user login
    public void doLogin(String em, String pw)
    {
        String email = em;
        String password = pw;
        HashMap postData = new HashMap();
        postData.put("email",email);
        postData.put("password",password);

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(LoginActivity.this, postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

               if(s.contains("failed") || s.isEmpty())
               {
                   Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   UserModel u = new UserModel();
                   Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                   ArrayList<UserModel> user = new JsonConverter<UserModel>().toArrayList(s,UserModel.class);
                   for(int i=0;i<user.size();i++)
                   {
                       u = user.get(i);
                   }

                   Gson gson = new Gson();
                   String userJson = gson.toJson(u);
                   //If user is staff redirect to staff dashboard (note: usertype:1=staff, usertype:2 = member)
                    if(u.usertype==1)
                    {
                        Intent intent = new Intent(LoginActivity.this, StaffDashboardActivity.class);
                        intent.putExtra("user", userJson);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(LoginActivity.this, MemberDashboardActivity.class);
                        intent.putExtra("user", userJson);
                        startActivity(intent);
                    }

               }
            }
        });
        task1.execute("http://192.168.254.6/eLibrary/index.php");


        task1.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                Toast.makeText(LoginActivity.this, "Error with internet or web server.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                Toast.makeText(LoginActivity.this, "Error with the URL.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                Toast.makeText(LoginActivity.this, "Error with protocol.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                Toast.makeText(LoginActivity.this, "Error with text encoding.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
