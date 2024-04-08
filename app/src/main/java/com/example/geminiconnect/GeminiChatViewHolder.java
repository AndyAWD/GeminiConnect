package com.example.geminiconnect;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GeminiChatViewHolder extends RecyclerView.ViewHolder{

    public TextView message;

    public GeminiChatViewHolder(@NonNull View itemView) {
        super(itemView);
        message = itemView.findViewById(R.id.vgc_message);
    }
}
