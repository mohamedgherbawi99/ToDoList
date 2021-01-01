package utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.todolist.todolist.ui.AuthActivity;


public class AuthUtil {

    private final String TAG = "AuthUtil";

    private static final AuthUtil instance = new AuthUtil();


    //make the constructor private so that this class cannot be
    //instantiated
    private AuthUtil(){}

    //Get the only object available
    public static AuthUtil getInstance(){
        return instance;
    }

    private final FirebaseAuth mAuth  = FirebaseAuth.getInstance();



    public Boolean getCurrentUser(){
        FirebaseUser user = mAuth.getCurrentUser();
        return user != null;
    }

    public void singUp(String email, String password,Activity activity, CallBackAuth callBack){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        callBack.onComplete(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        callBack.onComplete(null);
                    }

                    // ...
                });
    }

    public void singIn(String email, String password,Activity activity, CallBackAuth callBack){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        callBack.onComplete(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        callBack.onComplete(null);
                        // ...
                    }

                    // ...
                });
    }

    public void logOut(Activity activity){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(activity, AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
        activity.startActivity(intent);
    }

    public interface CallBackAuth {
        // this can be any type of method
        void onComplete(FirebaseUser account);
    }
}
