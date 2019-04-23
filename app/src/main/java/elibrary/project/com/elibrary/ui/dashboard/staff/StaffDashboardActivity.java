package elibrary.project.com.elibrary.ui.dashboard.staff;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.UserModel;
import elibrary.project.com.elibrary.ui.dashboard.staff.Books.BookListActivity;
import elibrary.project.com.elibrary.ui.dashboard.staff.Users.UserListActivity;

public class StaffDashboardActivity extends AppCompatActivity {

    TextView un,bn,in;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);
        Gson gson = new Gson();
        UserModel user  = gson.fromJson(getIntent().getStringExtra("user"), UserModel.class);

         un= findViewById(R.id.un);
         bn=findViewById(R.id.bn);
         in = findViewById(R.id.in);
        ConstraintLayout c = findViewById(R.id.ul);
        ConstraintLayout c1 = findViewById(R.id.c1);
        getData();

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffDashboardActivity.this,BookListActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getData()
    {
        PostResponseAsyncTask readData = new PostResponseAsyncTask(this,
                new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        String [] words = s.split(" ");
                        un.setText(words[0]);
                        bn.setText(words[1]);
                        in.setText(words[2]);
                    }
                });
        readData.execute("http://192.168.254.6/eLibrary/dashboard.php");
    }

}
