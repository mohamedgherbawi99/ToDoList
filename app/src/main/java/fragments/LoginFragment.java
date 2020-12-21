package fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.todolist.todolist.R;
import com.todolist.todolist.ui.MainActivity;

import helperclass.MyFragmentManager;


public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        Button login = root.findViewById(R.id.login);
        login.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });

        AppCompatTextView createAccountFromLogin = root.findViewById(R.id.createAccountFromLogin);
        createAccountFromLogin.setOnClickListener(v -> {
            //getActivity().onBackPressed();
            MyFragmentManager.getInstance().finishFragment(getContext());
        });

        return root;
    }
}