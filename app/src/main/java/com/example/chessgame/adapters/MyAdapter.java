package com.example.chessgame.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chessgame.R;
import com.example.chessgame.viewholders.MyviewHolder;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyviewHolder> {

    ArrayList<String> data;
    Context context;

    public MyAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_board,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        // holder.boardimage.setImageURI();
        String imageName = data.get(position); // Assuming imageName holds the name of the image
        int imageResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (imageResourceId != 0) {
            Drawable imageDrawable = context.getResources().getDrawable(imageResourceId);
            holder.boardimage.setImageDrawable(imageDrawable);
//            ArrayList<Drawable> drawables = new ArrayList<>();
//            drawables.add(imageDrawable);
        } else {
            // Image with the given name not found
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
