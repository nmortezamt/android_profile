package com.example.v2.tasks.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2.R;

import java.util.ArrayList;
import java.util.List;

public class PlayListActivity extends AppCompatActivity {
    public static List<Song> songList = new ArrayList<>();
    private static final int PICK_AUDIO_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        songList.add(new Song("Batel Shod", R.raw.batel_shod));
        songList.add(new Song("Spazz", R.raw.spazz));
        songList.add(new Song("Rock A Chock", R.raw._rock_a_chock));

        SongAdapter adapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(adapter);

        Button selectButton = findViewById(R.id.selectMusicButton);
        selectButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("audio/*");
            startActivityForResult(intent, PICK_AUDIO_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri audioUri = data.getData();
            if (audioUri != null) {
                // Optional: Persist permission for this URI
                getContentResolver().takePersistableUriPermission(audioUri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Start PlayerActivity with selected file
                Intent playerIntent = new Intent(this, PlayerActivity.class);
                playerIntent.putExtra("audioUri", audioUri.toString());
                startActivity(playerIntent);
            } else {
                Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
