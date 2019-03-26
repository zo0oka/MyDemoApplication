package com.zo0okadev.mydemoapplication.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zo0okadev.mydemoapplication.AppViewModel;
import com.zo0okadev.mydemoapplication.R;
import com.zo0okadev.mydemoapplication.model.Category;
import com.zo0okadev.mydemoapplication.ui.CategoryAdapter;
import com.zo0okadev.mydemoapplication.utils.LocaleHelper;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainCategoriesFragment extends Fragment {

    public MainCategoriesFragment() {
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_categories, container, false);

        String language = LocaleHelper.getLanguage(getActivity());

        AppViewModel appViewModel = ViewModelProviders.of(getActivity()).get(AppViewModel.class);
        appViewModel.getCategories(0, 1);

        RecyclerView recyclerView = rootView.findViewById(R.id.categories_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), language);
        recyclerView.setAdapter(categoryAdapter);

        appViewModel.categories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoryAdapter.setCategories(categories);
            }
        });

        appViewModel.error().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}
