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
import com.todolist.todolist.ui.AuthActivity;
import com.todolist.todolist.ui.MainActivity;
import com.todolist.todolist.ui.SplashActivity;

import helperclass.MyFragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingUpFragment newInstance(String param1, String param2) {
        SingUpFragment fragment = new SingUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sing_up, container, false);
        Button button = root.findViewById(R.id.btn);

        button.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });

        AppCompatTextView haveAccountLogin = root.findViewById(R.id.haveAccountLogin);
        haveAccountLogin.setOnClickListener(v ->
                MyFragmentManager.getInstance().switchFragment(new LoginFragment(),R.id.auth_frame,getContext())
        );

        return root;
    }
}