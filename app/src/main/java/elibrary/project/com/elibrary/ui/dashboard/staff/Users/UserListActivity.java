package elibrary.project.com.elibrary.ui.dashboard.staff.Users;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.UserModel;
import elibrary.project.com.elibrary.ui.dashboard.staff.Navigator;
import elibrary.project.com.elibrary.ui.dashboard.staff.Users.UserAdapter;

public class UserListActivity extends AppCompatActivity implements Navigator {

    private List<UserModel> userList;
    private RecyclerView rv;
    private UserAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        rv= findViewById(R.id.rv);
        userList = new ArrayList<>();
        mAdapter = new UserAdapter(userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        prepareMovieData();
    }

    @Override
    public void itemClick(UserModel u, int position) {

    }

    public void prepareMovieData()
    {
        UserModel u = new UserModel();
        u.firstname="Kishor";
        u.email="asdhf";
        userList.add(u);

        UserModel u1 = new UserModel();
        u1.firstname="Anil";
        u1.email="a@a.c";
        userList.add(u1);

        mAdapter.notifyDataSetChanged();
    }

}
