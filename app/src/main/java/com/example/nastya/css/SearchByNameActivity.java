package com.example.nastya.css;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import domain.Professor;

public class SearchByNameActivity extends AppCompatActivity {

    Toolbar mToolbar;

    private EditText mSearchField;
    private ImageButton mSearchButton;
    private RecyclerView mResultList;
    private DatabaseReference mProfessorDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        mProfessorDatabase = FirebaseDatabase.getInstance().getReference("Professors");

        mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchButton = (ImageButton) findViewById(R.id.search_button);
        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

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

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchField.getText().toString();
                firebaseProfessorSearch(searchText);
            }
        });

    }

    private void firebaseProfessorSearch(String searchText) {

        Toast.makeText(SearchByNameActivity.this, "Пошук почато", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mProfessorDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerAdapter<Professor, ProfessorsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Professor, ProfessorsViewHolder>(

                Professor.class,
                R.layout.list_layout,
                ProfessorsViewHolder.class,
                firebaseSearchQuery
        ) {
            @Override
            protected void populateViewHolder(ProfessorsViewHolder viewHolder, Professor model, int position) {

                viewHolder.setDetails(getApplicationContext(), model.getFirstName(), model.getLastName());

            }
        };
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    //View holder class
    public static class ProfessorsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ProfessorsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String name, String surname) {

            TextView professor_name = (TextView) mView.findViewById(R.id.name_view);
            professor_name.setText((name + " " + surname));

        }

    }
}
