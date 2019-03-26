package com.zo0okadev.mydemoapplication.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zo0okadev.mydemoapplication.R;
import com.zo0okadev.mydemoapplication.model.Category;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

class CategoryViewHolder extends RecyclerView.ViewHolder {

    private ImageView categoryImage;
    private TextView title, count;

    CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryImage = itemView.findViewById(R.id.category_photo);
        title = itemView.findViewById(R.id.category_title);
        count = itemView.findViewById(R.id.category_count);
    }

    void bindTo(final FragmentActivity activity, final Category category, final String language) {
        if (language.equals("en")) {
            title.setText(category.getTitleEN());
        } else if (language.equals("ar")) {
            title.setText(category.getTitleAR());
        }
        count.setText(String.format("(%s)", category.getProductCount()));

        if (category.getPhoto().equals("http://souq.hardtask.co//Images/no_image.png")) {
            Glide.with(activity).load(R.drawable.cat_no_img).into(categoryImage);
        } else {
            Glide.with(activity).load(category.getPhoto()).into(categoryImage);
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (category.getSubCategories().size() > 0) {
                    Bundle args = new Bundle();
                    args.putInt("categoryId", category.getId());
                    Navigation.findNavController(activity, R.id.nav_host_fragment)
                            .navigate(R.id.nav_sub_categories, args);
                } else {
                    if (language.equals("en")) {
                        Toast.makeText(v.getContext(), "No subcategories", Toast.LENGTH_LONG).show();
                    } else if (language.equals("ar")) {
                        Toast.makeText(v.getContext(), "لا توجد أقسام فرعية", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
