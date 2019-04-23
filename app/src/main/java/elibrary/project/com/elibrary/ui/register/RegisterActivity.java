package elibrary.project.com.elibrary.ui.register;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final TextInputLayout firstNameWrapper = findViewById(R.id.firstNameWrapper);
        final TextInputLayout lastNameWrapper = findViewById(R.id.lastNameWrapper);
        final TextInputLayout emailWrapper = findViewById(R.id.emailWrapper);
        final TextInputLayout passwordWrapper = findViewById(R.id.passwordWrapper);
        final TextInputLayout cpasswordWrapper = findViewById(R.id.cpasswordWrapper);
        Button btn_signUp = findViewById(R.id.btn_signUp);


        //when user click on register button
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstNameWrapper.getEditText().getText().toString();
                String lastname = lastNameWrapper.getEditText().getText().toString();
                String email = emailWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                String cpassword = cpasswordWrapper.getEditText().getText().toString();

                if(firstname.isEmpty())
                {
                    firstNameWrapper.setError("Please enter name!");
                }
                else if(lastname.isEmpty())
                {
                    firstNameWrapper.setErrorEnabled(false);
                    lastNameWrapper.setError("Please enter last name!");
                }
                else if (!validateEmail(email))
                {
                    lastNameWrapper.setErrorEnabled(false);
                    emailWrapper.setError("Not a valid email address!");
                }
                else if (!validatePassword(password))
                {
                    emailWrapper.setErrorEnabled(false);
                    passwordWrapper.setError("Not a valid password!");
                }
                else if(!cpassword.equals(password))
                {
                    passwordWrapper.setErrorEnabled(false);
                    cpasswordWrapper.setError("Password doesnot match");
                }
                else
                {

                    firstNameWrapper.setErrorEnabled(false);
                    lastNameWrapper.setErrorEnabled(false);
                    emailWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    cpasswordWrapper.setErrorEnabled(false);
                    userRegister(firstname,lastname,email,password);
                }

            }
        });
    }

    // register user in server
    public void userRegister(String firstname, String lastname, String email, String password)
    {
        String usertype = "2";
        String status = "1";
        HashMap data = new HashMap();
        data.put("firstname",firstname);
        data.put("lastname",lastname);
        data.put("email",email);
        data.put("password",password);
        data.put("usertype",usertype);
        data.put("status",status);



        String url = "http://192.168.254.6/eLibrary/register.php";
        PostResponseAsyncTask readTask = new PostResponseAsyncTask(RegisterActivity.this, data, true, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
               if (s.contains("success"))
               {
                   AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
                   alertDialog.setTitle("Online Library");
                   alertDialog.setMessage("User registration successful");
                   alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                           new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int which) {
                                   Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                   startActivity(intent);
                                   finish();
                                   dialog.dismiss();
                               }
                           });
                   alertDialog.show();

               }
               else
               {
                   Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
               }
            }
        });
        readTask.execute(url);

        readTask.setEachExceptionsHandler(new EachExceptionsHandler() {
            @Override
            public void handleIOException(IOException e) {
                Toast.makeText(RegisterActivity.this, "Error with internet or web server.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleMalformedURLException(MalformedURLException e) {
                Toast.makeText(RegisterActivity.this, "Error with the URL.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleProtocolException(ProtocolException e) {
                Toast.makeText(RegisterActivity.this, "Error with protocol.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {
                Toast.makeText(RegisterActivity.this, "Error with text encoding.", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public boolean validatePassword(String password) {
        return password.length() > 1;
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
}
