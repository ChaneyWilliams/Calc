package com.example.calc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<String> historyList;

    public HistoryAdapter(List<String> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new HistoryViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        String record = historyList.get(position);
        holder.bind(record);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    // ---------------------------
    // ViewHolder
    // ---------------------------
    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView textNoteTitle;

        HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textNoteTitle = itemView.findViewById(R.id.textNoteTitle);
        }

        void bind(String record) {
            textNoteTitle.setText(record);
        }
    }
}