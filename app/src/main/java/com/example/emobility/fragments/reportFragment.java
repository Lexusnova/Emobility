package com.example.emobility.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emobility.R;
import com.example.emobility.SharedViewModel;
import com.example.emobility.classes.Ladesaeulen;
import com.example.emobility.classes.Report;
import com.example.emobility.classes.Verwalter;

/**
 * @class reportFragment
 * @brief Zuständig für die Übergabe des Reports
 */
public class reportFragment extends Fragment {
    Report report;
    Verwalter verwalter = new Verwalter();


    public reportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_report, container, false);
        return rootView;
    }

    /**
     * @brief Inhalte werden aus der Cardview entnommen und weitergeben
     * @param view View
     * @param savedInstanceState beinhaltet die Bundles
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Shared Views
        //sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class); // Wird nicht genutzt bugged

        // View finden und damit arbeiten
        TextView subject = (TextView)  view.findViewById(R.id.textViewSubjectReport);// Hier kommt dann die Ladesäule Objekt hinein
        TextView context = (TextView)  view.findViewById(R.id.textViewContextReport);
        TextView id = (TextView) view.findViewById(R.id.textViewIDReport);
        Button button = (Button) view.findViewById(R.id.buttonReport);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //schaut ob in den Views sich inhalSte befinden
                if (subject.getText().toString().equals("") || context.getText().toString().equals("") || subject.getText().toString().equals("")) {
                    Toast.makeText(getContext(),"Field can't be empty!",Toast.LENGTH_SHORT).show();
                } else {
                    //schaut ob die ID stimmt und gibt den Inhalt weiter an Verwalter
                    if (Integer.parseInt(id.getText().toString())  >= 0 && Integer.parseInt(id.getText().toString()) <= Verwalter.getLadesaeule().size()-1) {
                        Toast.makeText(getContext(),"Report Send!",Toast.LENGTH_SHORT).show();
                        String subjectString = subject.getText().toString();
                        String contextString = context.getText().toString();
                        String idString = id.getText().toString();
                        int ladesaeuleID = Integer.parseInt(idString);
                        report = new Report(subjectString, contextString, false,ladesaeuleID);
                        Verwalter.sendReport(ladesaeuleID, false, false, report);
                        subject.setText(null);
                        context.setText(null);
                        id.setText(null);
                        //sendDataToOtherFragment();
                        //sendDataToSharedView();
                    } else {
                        Toast.makeText(getContext(),"Can't be minus Value or to high Value!",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        //Send the Report Data to the ReportListFragment
    }
   /*
    public void sendDataToSharedView() {
        Log.d("Data", "Test");
        sharedViewModel.setSelectedItem(report);
    }

    public void sendDataToOtherFragment(){
        FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Bundle bundle = new Bundle();
        bundle.putSerializable("report Object", report);
        ft.addToBackStack(null);
        //ft.show(reportListFragment);
        ft.commit();
    }
    */
}