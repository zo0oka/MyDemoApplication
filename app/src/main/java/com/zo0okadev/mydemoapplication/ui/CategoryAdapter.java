package com.zo0okadev.mydemoapplication.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zo0okadev.mydemoapplication.R;
import com.zo0okadev.mydemoapplication.model.Category;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories;
    private FragmentActivity activity;
    private String language;

    public CategoryAdapter(FragmentActivity activity, String language) {
        this.language = language;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindTo(activity, categories.get(position), language);
    }

    @Override
    public int getItemCount() {
        return (categories != null) ? categories.size() : 0;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
