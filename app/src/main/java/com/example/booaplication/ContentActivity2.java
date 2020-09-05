package com.example.booaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booaplication.DataBase.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContentActivity2 extends AppCompatActivity {

    private ImageView back;
    private CircleImageView image;
    private TextView name,field, disc;
    private FloatingActionButton fab;
    private Database database;
    private int id, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content2);
        Listener listener = new Listener();
        setupViews();
        getAndSetData();
        favCondition();
        back.setOnClickListener(listener);
        fab.setOnClickListener(listener);
    }

    public void setupViews() {
        back = findViewById(R.id.back);
        image = findViewById(R.id.image_G_id);
        name = findViewById(R.id.name_G_id);
        disc = findViewById(R.id.disc_id);
        field = findViewById(R.id.field_G_id);
        fab = findViewById(R.id.floating_action_button_id);
    }

    public void getAndSetData() {
        Intent intent = getIntent();
        name.setText(intent.getExtras().getString("NAME"));
        field.setText(intent.getExtras().getString("FIELD"));
        disc.setText(intent.getExtras().getString("DISC"));
        id = intent.getExtras().getInt("ID");
        String imageLink = intent.getExtras().getString("IMAGE");
        Picasso.with(ContentActivity2.this).load(imageLink).resize(128, 128).into(image);
    }

    public void favCondition() {
        try {
            database = new Database(ContentActivity2.this);
            value = database.favValue(id);
            if (value == 0) {
                fab.setImageResource(R.drawable.favorite1_border_24);
            } else {
                fab.setImageResource(R.drawable.ic_baseline_favorite_24);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addOrRemoveFav() {
        try {
            database = new Database(ContentActivity2.this);
            value = database.favValue(id);
            if (value == 0) {
                database.fav(1,id);
                fab.setImageResource(R.drawable.ic_baseline_favorite_24);
                Toast.makeText(ContentActivity2.this,"به لیست علاقه مندی ها اضافه شد",Toast.LENGTH_SHORT).show();
            } else {
                database.fav(0,id);
                fab.setImageResource(R.drawable.favorite1_border_24);
                Toast.makeText(ContentActivity2.this,"از لیست علاقه مندی ها حذف شد",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Listener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            switch (viewId) {
                case R.id.back:
                    onBackPressed();
                    break;
                case R.id.floating_action_button_id:
                    addOrRemoveFav();
                    break;
            }
        }
    }
}