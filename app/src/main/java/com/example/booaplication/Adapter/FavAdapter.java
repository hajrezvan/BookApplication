package com.example.booaplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booaplication.ContentActivity2;
import com.example.booaplication.Model.Person;
import com.example.booaplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Person> personList;

    public FavAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.name.setText(personList.get(position).getName());
        Picasso.with(context).load(personList.get(position).getImage()).resize(128, 128).into(itemViewHolder.imageView);

        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContentActivity2.class);
                intent.putExtra("ID", personList.get(position).getId());
                intent.putExtra("NAME", personList.get(position).getName());
                intent.putExtra("FIELD", personList.get(position).getField());
                intent.putExtra("DISC", personList.get(position).getDisc());
                intent.putExtra("IMAGE", personList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        private TextView name;
        private CardView item;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.image_id);
            name = (TextView) itemView.findViewById(R.id.name_id);
            item = (CardView) itemView.findViewById(R.id.item_id);
        }
    }
}

