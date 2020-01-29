package com.harshraj.dsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String m_word = "";
    private String m_meaning = "";
    private HashMap<String, String> hashMap;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    public static final String MyPreferences = "harsh";
    private ListView lv;
    private ArrayList<Model> arraylist;
    private SearchView searchView;
    private Model_Adapter adapter;
    private EditText editText;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ChildEventListener mlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arraylist = new ArrayList<>();

        adapter=new Model_Adapter(this,arraylist);
        recyclerView.setAdapter(adapter);

        editText = findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Add a word");

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);

            final EditText word = new EditText(this);
            word.setHint("Word");
            layout.addView(word); // Notice this is an add method

            final EditText meaning = new EditText(this);
            meaning.setHint("Meaning");
            layout.addView(meaning);

            dialog.setView(layout);

            //@harsh---->button setup
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_word = word.getText().toString();
                    m_meaning = meaning.getText().toString();
                   arraylist.add(new Model(m_word,m_meaning));
                        }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        ArrayList<Model> filteredlist = new ArrayList<>();
        for (Model item : arraylist) {
            if (item.getWord().contains(text)) {
                filteredlist.add(item);
            }
        }
        adapter.filterList(filteredlist);
    }
}