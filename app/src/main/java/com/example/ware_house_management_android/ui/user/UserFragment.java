package com.example.ware_house_management_android.ui.user;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ware_house_management_android.adapters.UserAdapter;
import com.example.ware_house_management_android.contracts.UserContract;
import com.example.ware_house_management_android.databinding.FragmentUserBinding;
import com.example.ware_house_management_android.presenters.UserPresenter;

import org.json.JSONException;

public class UserFragment extends Fragment implements UserContract.View {

    private FragmentUserBinding binding;

    private UserPresenter userPresenter;

    private RecyclerView listUsers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel homeViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUser;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        userPresenter = new UserPresenter(this.getContext(), homeViewModel, this);
        try {
            userPresenter.getUsersList();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        listUsers = binding.recyclerViewUser;
        listUsers.setLayoutManager(new GridLayoutManager(this.getContext(), 1));

        Log.i("UserFragment", "Users list size: " + homeViewModel.getUsersList().getValue().size());

        homeViewModel.getUsersList().observe(getViewLifecycleOwner(), users -> {
            Log.i("UserFragment", "Users list updated, size: " + users.size());
            listUsers.setAdapter(new UserAdapter(this.getContext(), users));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}