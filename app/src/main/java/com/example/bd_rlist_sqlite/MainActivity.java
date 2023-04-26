package com.example.bd_rlist_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bd_rlist_sqlite.room.Dia;
import com.example.bd_rlist_sqlite.room.DiaListAdapter;
import com.example.bd_rlist_sqlite.room.DiaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private DiaViewModel mDiaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: EDIT THIS WordListAdapter
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DiaListAdapter adapter = new DiaListAdapter(new DiaListAdapter.DiaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mDiaViewModel = new ViewModelProvider(this).get(DiaViewModel.class);

        // Добавьте наблюдателя на LiveData, возвращаемые getAlphabetizedWords.
        // Метод onChanged() срабатывает, когда наблюдаемые данные изменяются и активность находится на переднем плане.
        mDiaViewModel.getAllDias().observe(this, dias -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(dias);
        });




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            //обработка сдвига элемента recyclerView
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                Snackbar snackbar = Snackbar.make( recyclerView , "Удаление", Snackbar.LENGTH_SHORT)
                        .setAction("Отменить", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //adapterFirebase.notifyItemChanged(position);
                            }
                        }).addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {

                                if (event != DISMISS_EVENT_ACTION) {
                                    //adapterFirebase.deleteItem(position);
                                    Dia dia = adapter.getDiaAtPosition(position);
                                    mDiaViewModel.delete(dia.uid);
                                }
                                if (event == DISMISS_EVENT_CONSECUTIVE){
                                    Dia dia = adapter.getDiaAtPosition(position);
                                    mDiaViewModel.delete(dia.uid);
                                    //adapterFirebase.deleteItem(position);
                                }
                            }
                        });
                snackbar.show();
            }

        }).attachToRecyclerView(recyclerView);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String str = data.getStringExtra(NewWordActivity.EXTRA_REPLY);
            Dia dia = new Dia(0,  0, Float.parseFloat(str),0,0);

            mDiaViewModel.insert(dia);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

}