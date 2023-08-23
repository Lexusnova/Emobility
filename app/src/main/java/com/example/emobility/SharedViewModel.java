package com.example.emobility;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.emobility.classes.Report;


//IMPORTANT: NOT USED

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Report> selectedItem  = new MutableLiveData<>();

    public void setSelectedItem(Report report) {
        this.selectedItem.setValue(report);
    }

    public LiveData<Report> getSelectedItem() {
        return selectedItem;
    }
}
