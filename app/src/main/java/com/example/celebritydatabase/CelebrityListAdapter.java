package com.example.celebritydatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class CelebrityListAdapter extends RecyclerView.Adapter<CelebrityListAdapter.CelebrityViewHolder> {

    class CelebrityViewHolder extends RecyclerView.ViewHolder {
        private final TextView cFirstName;
        private final TextView cLastName;
        private final TextView cProfession;

        private CelebrityViewHolder(@NonNull View itemView) {
            super(itemView);
            cFirstName = itemView.findViewById(R.id.tvFirstName);
            cLastName = itemView.findViewById(R.id.tvLastName);
            cProfession = itemView.findViewById(R.id.tvProfession);
        }
    }

    private final LayoutInflater inflater;
    //cached copy of celebrities
    private List<Celebrity> myCelebrities = Collections.emptyList();

    CelebrityListAdapter(Context context){

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CelebrityListAdapter.CelebrityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CelebrityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CelebrityListAdapter.CelebrityViewHolder holder, int position) {
        Celebrity current = myCelebrities.get(position);
        holder.cFirstName.setText(current.getFirstName());
        holder.cLastName.setText(current.getLastName());
        holder.cProfession.setText(current.getProfession());

    }

    public void setCelebrities(List<Celebrity> celebrities) {
        myCelebrities = celebrities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return myCelebrities.size();
    }

}
