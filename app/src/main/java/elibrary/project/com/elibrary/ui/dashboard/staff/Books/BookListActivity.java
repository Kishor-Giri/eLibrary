package elibrary.project.com.elibrary.ui.dashboard.staff.Books;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import elibrary.project.com.elibrary.R;
import elibrary.project.com.elibrary.data.models.Book;

public class BookListActivity extends AppCompatActivity {

    private List<Book> bookList;
    private RecyclerView rv;
    private BookAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        rv= findViewById(R.id.rv);
        bookList = new ArrayList<>();
        mAdapter = new BookAdapter(bookList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
        prepareMovieData();
    }

    public void prepareMovieData()
    {
        Book b = new Book();
       b.name="Science";
       b.author="Kishor";
        bookList.add(b);

        Book b1 = new Book();
        b1.name="Science";
        b1.author="Kishor";
        bookList.add(b1);

        mAdapter.notifyDataSetChanged();
    }
}
