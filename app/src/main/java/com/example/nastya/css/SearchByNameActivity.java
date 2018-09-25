package com.example.nastya.css;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import domain.User;

public class SearchByNameActivity extends AppCompatActivity {

    Toolbar mToolbar;

    private ImageButton mSearchButton;
    private RecyclerView mResultList;
    private DatabaseReference mUserDatabase;
    private SearchView mSearchField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Users");

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mSearchField = (SearchView) findViewById(R.id.search_view);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        SearchView.SearchAutoComplete searchAutoComplete =
                (SearchView.SearchAutoComplete) mSearchField.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(getResources().getColor(R.color.colorDarkText));

        firebaseUsersSearch("");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchByNameActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);

            }
        });

        mSearchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {

                searchText = mSearchField.getQuery().toString();
                firebaseUsersSearch(searchText);

                return false;
            }

        });

       /*mSearchField.setOnCloseListener(new SearchView.OnCloseListener(){
           @Override
           public boolean onClose() {
               firebaseUsersSearch("");
               return false;
           }
       });*/
    }


    private void firebaseUsersSearch(String searchText) {

        Query firebaseSearchQuery = mUserDatabase.orderByChild("lastName").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(

                User.class,
                R.layout.list_layout,
                UserViewHolder.class,
                firebaseSearchQuery

        ) {
            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, User model, int position) {

                viewHolder.setDetails(getApplicationContext(), model.getFirstName(), model.getLastName());

            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }


    //View holder class
    public static class UserViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public UserViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String name, String surname) {

            TextView professor_name = (TextView) mView.findViewById(R.id.name_view);
            professor_name.setText((surname + " " + name));

        }

    }
}
