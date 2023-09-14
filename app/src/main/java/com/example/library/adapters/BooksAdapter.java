package com.example.library.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.activities.BookDetails;
import com.example.library.Books;
import com.example.library.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.library.activities.AppHome.AUTHOR;
import static com.example.library.activities.AppHome.AVAILABLE;
import static com.example.library.activities.AppHome.CATEGORY;
import static com.example.library.activities.AppHome.COVER_URL;
import static com.example.library.activities.AppHome.DESCRIPTIONS;
import static com.example.library.activities.AppHome.ISBN;
import static com.example.library.activities.AppHome.REQUESTS;
import static com.example.library.activities.AppHome.TITLE;
import static com.example.library.activities.AppHome.UPLOADED_BY;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {

    private Context mcontext;
    String mlogged, mrole;
    private ArrayList<Books> marrayList;

    // Constructor for the adapter
    public BooksAdapter(Context context, ArrayList<Books> arrayList, String logged, String role) {
        marrayList = arrayList;
        mcontext = context;
        mlogged = logged;
        mrole = role;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item view for the RecyclerView
        View view = LayoutInflater.from(mcontext).inflate(R.layout.book_card, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        Books currentBook = marrayList.get(position);

        // Extract book information from the current item
        final String title = currentBook.getTitle();
        final String author = currentBook.getAuthor();
        final String cover = currentBook.getCover();
        final String isbn = currentBook.getIsbn();
        final String category = currentBook.getCategory();
        final String uploadedby = currentBook.getUploadedBy();
        final String available = currentBook.getAvailable();
        final String description = currentBook.getDescript();
        final String requested = currentBook.getRequests();

        // Set the extracted information to the corresponding views
        holder.bookTitle.setText(title);
        holder.bookAuthor.setText(author);
        holder.bookDescriptions.setText(description);
        holder.isbn.setText(isbn);
        holder.bookCategory.setText(category);
        holder.uploader.setText(uploadedby);
        holder.available.setText(available);
        holder.Requests.setText(requested);

        // Load book cover image using Picasso library
        if (cover.equals("")) {
            holder.bookCover.setImageResource(R.drawable.book_cover);
        } else {
            Picasso.get().load(cover).fit().centerInside().into(holder.bookCover);
        }

        // Handle click events on the item view
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the BookDetails activity
                Intent intent = new Intent(mcontext, BookDetails.class);

                // Pass book information as extras to the intent
                intent.putExtra(COVER_URL, cover);
                intent.putExtra(TITLE, title);
                intent.putExtra(AUTHOR, author);
                intent.putExtra("Mail", mlogged);
                intent.putExtra("ROLE", mrole);
                intent.putExtra(ISBN, isbn);
                intent.putExtra(UPLOADED_BY, uploadedby);
                intent.putExtra(AVAILABLE, available);
                intent.putExtra(CATEGORY, category);
                intent.putExtra(DESCRIPTIONS, description);
                intent.putExtra(REQUESTS, requested);

                // Start the BookDetails activity and finish the current activity
                mcontext.startActivity(intent);
                ((Activity) mcontext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return marrayList.size();
    }

    // ViewHolder class to hold and manage the item view's UI elements
    public class BooksViewHolder extends RecyclerView.ViewHolder {
        public ImageView bookCover;
        View view;
        public TextView bookTitle, bookAuthor, bookDescriptions, available, isbn, uploader, bookCategory, Requests;

        // Constructor for the ViewHolder
        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookAuthor = itemView.findViewById(R.id.author);
            bookCover = itemView.findViewById(R.id.bCover);
            bookTitle = itemView.findViewById(R.id.title);
            view = itemView.findViewById(R.id.bookCard);
            bookDescriptions = itemView.findViewById(R.id.descripts);
            available = itemView.findViewById(R.id.available);
            isbn = itemView.findViewById(R.id.isbn);
            uploader = itemView.findViewById(R.id.uploadby);
            bookCategory = itemView.findViewById(R.id.category);
            Requests = itemView.findViewById(R.id.requs);
        }
    }

    // Method to filter the list of books
    public void filterList(ArrayList<Books> filteredList) {
        marrayList = filteredList;
        notifyDataSetChanged();
    }
}
