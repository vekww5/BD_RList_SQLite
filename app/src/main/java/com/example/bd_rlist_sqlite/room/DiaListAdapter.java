package com.example.bd_rlist_sqlite.room;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class DiaListAdapter extends ListAdapter<Dia, DiaViewHolder>{

    public DiaListAdapter(@NonNull DiffUtil.ItemCallback<Dia> diffCallback) {
        super(diffCallback);
    }

    @Override
    public DiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DiaViewHolder holder, int position) {
        Dia current = getItem(position);

        holder.bind(current.getInjectLongString(), current.getInjectShortString(), current.getGlucoseString(), current.getXeString());
    }

    //TODO: getDiaAtPosition
    public Dia getDiaAtPosition (int position) {
        return getItem(position);
    }

    public static class DiaDiff extends DiffUtil.ItemCallback<Dia> {
        @Override
        public boolean areItemsTheSame(@NonNull Dia oldItem, @NonNull Dia newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dia oldItem, @NonNull Dia newItem) {
            return false;
        }

        /* @Override
        public boolean areContentsTheSame(@NonNull Dia oldItem, @NonNull Dia newItem) {
            return oldItem.getGlucose()? oldItem.getGlucose() == newItem.getGlucose() : oldItem ;
        }
        */
    }
}
