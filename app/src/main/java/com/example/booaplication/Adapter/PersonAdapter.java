package com.example.booaplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booaplication.Model.Person;
import com.example.booaplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Person> personList;

    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.name.setText(personList.get(position).getName());
        itemViewHolder.field.setText(personList.get(position).getField());

        Picasso.with(context).load(personList.get(position).getImage()).resize(128,128).into(itemViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imageView;
        private TextView name,field;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.image_id);
            name = (TextView) itemView.findViewById(R.id.name_id);
            field = (TextView) itemView.findViewById(R.id.field_id);

        }
    }
}
