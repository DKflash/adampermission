package com.example.adampermission;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {
    private cardInterf recyclerViewCallback;
    private Context context;
    private ArrayList<PermissionItem> permissionItemList;

    public PermissionAdapter(Context context, ArrayList<PermissionItem> permissionItemList, cardInterf recyclerViewCallback) {
        this.context = context;
        this.permissionItemList = permissionItemList;
        this.recyclerViewCallback = recyclerViewCallback;
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.perm_card, parent, false);
        return new PermissionViewHolder(view, recyclerViewCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        PermissionItem currentItem = permissionItemList.get(position);

        holder.permissionTitle.setText(currentItem.getPermissionTitle());
        holder.permissionIcon.setImageResource(currentItem.getIconResource());

        if (currentItem.isPermissionGranted()) {
            holder.permissionCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorb));
        } else {
            holder.permissionCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colora));
        }
    }

    @Override
    public int getItemCount() {
        return permissionItemList.size();
    }

    public static class PermissionViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView permissionIcon;
        AppCompatTextView permissionTitle;
        CardView permissionCardView;

        public PermissionViewHolder(@NonNull View itemView, cardInterf recyclerViewCallback) {
            super(itemView);
            permissionIcon = itemView.findViewById(R.id.imageView);
            permissionTitle = itemView.findViewById(R.id.textView);
            permissionCardView = itemView.findViewById(R.id.cardView);

            itemView.setOnClickListener(v -> {
                if (recyclerViewCallback != null) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewCallback.onItemClick(pos);
                    }
                }
            });
        }
    }
}
