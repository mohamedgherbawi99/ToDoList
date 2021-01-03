package adapter;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoLists implements Serializable {

    private String userId;
    private String nameList;
    private ArrayList<Task> arrayList;

    public ToDoLists(String userId, String nameList, ArrayList<Task> arrayList) {
        this.userId = userId;
        this.nameList = nameList;
        this.arrayList = arrayList;
    }

    public ToDoLists(String nameList, ArrayList<Task> arrayList) {
        this.nameList = nameList;
        this.arrayList = arrayList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public ArrayList<Task> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Task> arrayList) {
        this.arrayList = arrayList;
    }
}
