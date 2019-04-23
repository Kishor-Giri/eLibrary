package elibrary.project.com.elibrary.ui.dashboard.staff.Books;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.Book;
import elibrary.project.com.elibrary.data.models.UserModel;
import elibrary.project.com.elibrary.ui.dashboard.staff.Navigator;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder>{

    List<Book> bookList;
    private Navigator m;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, author;
        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.id);
            author =  view.findViewById(R.id.name);
            iv = view.findViewById(R.id.iv);
        }
    }

    public BookAdapter(List<Book> bookList)
    {
        this.bookList = bookList;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_single_book, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Book book = bookList.get(i);
        myViewHolder.name.setText(book.getName());
        myViewHolder.author.setText(book.getAuthor());
        myViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

}
