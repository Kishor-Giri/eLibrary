package elibrary.project.com.elibrary.ui.dashboard.staff.Users;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.UserModel;
import elibrary.project.com.elibrary.ui.dashboard.staff.Navigator;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    List<UserModel> userList;
    private Navigator m;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.name);
            email =  view.findViewById(R.id.email);
            iv = view.findViewById(R.id.iv);
        }
    }

    public UserAdapter(List<UserModel> userList)
    {
        this.userList = userList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_single_user, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserModel user = userList.get(i);
        myViewHolder.name.setText(user.getFirstname());
        myViewHolder.email.setText(user.getEmail());
        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}
