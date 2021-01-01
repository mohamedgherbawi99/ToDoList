package utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DocumentCollections;
import com.todolist.todolist.ui.TaskDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.Task;
import adapter.ToDoLists;

public class FirestoreUtil {

    private String TAG= "FirestoreUtil";
    private static final FirestoreUtil instance = new FirestoreUtil();


    //make the constructor private so that this class cannot be
    //instantiated
    private FirestoreUtil(){}

    //Get the only object available
    public static FirestoreUtil getInstance(){
        return instance;
    }

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addToList(String name, ArrayList<Task> tasks){
        ToDoLists toDoLists = new ToDoLists(name,tasks);
        db.collection("lists").document(name)
                .set(toDoLists)
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "DocumentSnapshot successfully written!")
                )
                .addOnFailureListener(e ->
                        Log.w(TAG, "Error writing document", e)
                );
    }

    public void add(HashMap<String, Object> hashMap){
        db.collection("lists").document()
                .set(hashMap)
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "DocumentSnapshot successfully written!")
                )
                .addOnFailureListener(e ->
                        Log.w(TAG, "Error writing document", e)
                );
    }

    public void update(ArrayList<Task> tasks, String documentPath){
        db.collection("lists").document(documentPath)
                .update("arrayList", tasks);
    }

    public void getFromList(String name, CallBackFirestore callBack){
        ArrayList<ToDoLists> toDoListsArrayList = new ArrayList<>();
        db.collection(name)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d(TAG, document.getId() + " => " + document.getData());
                            ArrayList<Task> arrayList = (ArrayList<Task>) document.get("arrayList");
                            ToDoLists toDoLists = new ToDoLists(document.get("nameList").toString(),
                                    arrayList);
                            toDoListsArrayList.add(toDoLists);
                            callBack.onComplete(toDoListsArrayList);
                        }
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });
    }

    public void getFromDocument(String collectionPath, String documentPath, CallBackFirestore callBackFirestore){
        DocumentReference docRef = db.collection(collectionPath).document(documentPath);
        docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "Listen failed.", e);
                return;
            }

//            if (snapshot != null && snapshot.exists()) {
//                Log.d(TAG, "Current data: " + snapshot.getData());
//            } else {
//                Log.d(TAG, "Current data: null");
//            }
//        });

//        docRef.get().addOnCompleteListener(task-> {
//            if (task.isSuccessful()) {
//
//                DocumentSnapshot document = task.getResult();


                if (snapshot.exists()) {

                    ArrayList<Map> _tasks = (ArrayList<Map>) snapshot.get("arrayList");

                    ArrayList<Task> arrayList = new ArrayList<>();
                    for (int i = 0; i < _tasks.size(); i++) {
                        Map _task = _tasks.get(i);
                        arrayList.add(
                            new Task(
                                (String) _task.get("taskName"),
                                (String) _task.get("description"),
                                (boolean) _task.get("taskCheck")
                            )
                        );
                        callBackFirestore.onComplete(arrayList);
                    }

                } else {
                    Log.d(TAG, "No such document");
                }
//            } else {
//                Log.d(TAG, "get failed with ", task.getException());
//            }
//
        });
    }


    public interface CallBackFirestore {
        // this can be any type of method
        void onComplete(ArrayList arrayList);
    }

}
