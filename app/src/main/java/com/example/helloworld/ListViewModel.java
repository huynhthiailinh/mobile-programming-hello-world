package com.example.helloworld;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    private MutableLiveData<List<Integer>> list;
    public LiveData<List<Integer>> getList() {
        if (list == null) {
            list = new MutableLiveData<List<Integer>>();
            list.setValue(new ArrayList<>());
        }
        return list;
    }

    public void addToList(Integer integer) {
        List<Integer> array = list.getValue();
        array.add(integer);
        list.setValue(array);
    }

    public void updateList(int index, int value) {
        List<Integer> array = list.getValue();
        array.set(index, value);
        list.setValue(array);
    }
}
