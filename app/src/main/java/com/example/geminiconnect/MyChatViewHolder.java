package com.example.geminiconnect;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyChatViewHolder extends RecyclerView.ViewHolder{

    public TextView message;

    public MyChatViewHolder(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.vmc_message);
    }
}
