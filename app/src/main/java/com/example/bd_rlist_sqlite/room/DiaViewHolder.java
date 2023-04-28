package com.example.bd_rlist_sqlite.room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.bd_rlist_sqlite.R;

//TODO: Подделать под данные Dia
public class DiaViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private DiaViewHolder(View itemView) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String il, String is, String gl, String xe) {
        wordItemView.setText("il:" + il + "      is:" + is+"      gl:" + gl+"      xe:" + xe);
    }

    static DiaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new DiaViewHolder(view);
    }
}
