package com.example.geminiconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_HOLDER_MY_CHAT = 0;
    public static final int VIEW_HOLDER_GEMINI_CHAT = 1;

    private Context context;
    private ArrayList<ChatBean> chatList = new ArrayList<>();

    public ChatAdapter(Context context) {
        this.context = context;
    }

    /**
     * 新增資料
     */
    public void add(ChatBean chatBean) {
        this.chatList.add(chatBean);
        notifyItemInserted(this.chatList.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_HOLDER_MY_CHAT:
                view = LayoutInflater.from(context).inflate(R.layout.view_my_chat, parent, false);
                return new MyChatViewHolder(view);
            case VIEW_HOLDER_GEMINI_CHAT:
            default:
                view = LayoutInflater.from(context).inflate(R.layout.view_gemini_chat, parent, false);
                return new GeminiChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ChatBean chat = this.chatList.get(position);

        if (holder instanceof MyChatViewHolder) {
            MyChatViewHolder viewHolderMy = (MyChatViewHolder) holder;
            viewHolderMy.message.setText(chat.getMessage());
        }

        if (holder instanceof GeminiChatViewHolder) {
            GeminiChatViewHolder viewHolderGemini = (GeminiChatViewHolder) holder;
            viewHolderGemini.message.setText(chat.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return this.chatList != null ? this.chatList.size() : 0;
    }

    /**
     * getItemViewType特定position時回傳指定頁面
     */
    @Override
    public int getItemViewType(int position) {
        final ChatBean chat = this.chatList.get(position);
        if (chat.getAvatars() == VIEW_HOLDER_MY_CHAT) {
            return VIEW_HOLDER_MY_CHAT;
        } else {
            return VIEW_HOLDER_GEMINI_CHAT;
        }
    }
}
