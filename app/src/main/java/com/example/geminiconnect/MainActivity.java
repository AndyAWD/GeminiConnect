package com.example.geminiconnect;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.geminiconnect.response.Candidates;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout loading;
    private RecyclerView chatList;
    private EditText key;
    private EditText input;
    private MaterialButton send;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();
        initAdapter();
        initClickerListener();

    }

    private void initComponent() {
        loading = findViewById(R.id.am_loading);
        loading.setEnabled(false);
        chatList = findViewById(R.id.am_chat);
        key = findViewById(R.id.am_key);
        key.setText(BaseContent.API_KEY);
        input = findViewById(R.id.am_input);
        send = findViewById(R.id.am_send);
    }

    private void initAdapter() {
        chatAdapter = new ChatAdapter(MainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        chatList.setLayoutManager(layoutManager);
        chatList.setAdapter(chatAdapter);
        chatList.setItemAnimator(new DefaultItemAnimator());    //改變資料淡入淡出動畫
    }


    private void initClickerListener() {
        send.setOnClickListener(view -> {

            if (!BaseContent.EMPTY_STRING.equals(input.getText().toString())) {
                loading.setRefreshing(true);
                String requestJson = new Gson().toJson(BaseUtils.getInstance().getRequestObject(input.getText().toString()));
                chatAdapter.add(new ChatBean(
                        ChatAdapter.VIEW_HOLDER_MY_CHAT,
                        input.getText().toString()));

                input.setText(BaseContent.EMPTY_STRING);

                Log.d("maho", "請求的json: " + requestJson);

                okhttp3.Request request = new okhttp3
                        .Request.Builder()
                        .url(BaseUtils.getInstance().getUrl(BaseContent.MODEL, key.getText().toString()))
                        .post(RequestBody.create(MediaType.parse("application/json"), requestJson))
                        .build();

                // 執行異步請求
                OkHttpClient client = new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        // 處理失敗的響應
                        Log.d("maho", "Error: " + e.getMessage());
                        runOnUiThread(() -> {
                            addGeminiChat(e.getMessage());
                            loading.setRefreshing(false);
                        });
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        // 處理成功的響應
                        String responseBody;
                        if (response.body() != null) {
                            responseBody = response.body().string();
                            Log.d("maho", "responseBody: " + responseBody);
                            Candidates candidates = new Gson().fromJson(responseBody, Candidates.class);
                            runOnUiThread(() -> {
                                try {
                                    addGeminiChat(candidates.getCandidates().get(0).getContent().getParts().get(0).getText());
                                } catch (Exception e) {
                                    addGeminiChat(e.getMessage());
                                } finally {
                                    loading.setRefreshing(false);
                                }
                            });
                        }
                    }
                });
            }
        });

        send.setOnLongClickListener(view -> {
            if (!BaseContent.EMPTY_STRING.equals(input.getText().toString())) {
                chatAdapter.add(new ChatBean(ChatAdapter.VIEW_HOLDER_GEMINI_CHAT, input.getText().toString().trim()));
                input.setText(BaseContent.EMPTY_STRING);
                chatList.smoothScrollToPosition(chatAdapter.getItemCount());
            }
            return true;
        });
    }

    private void addGeminiChat(String candidates) {
        chatAdapter.add(new ChatBean(ChatAdapter.VIEW_HOLDER_GEMINI_CHAT, candidates));
        chatList.smoothScrollToPosition(chatAdapter.getItemCount());
    }
}