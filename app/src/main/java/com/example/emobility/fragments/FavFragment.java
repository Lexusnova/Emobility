package com.example.emobility.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emobility.adapters.FavouriteAdapter;
import com.example.emobility.R;
import com.example.emobility.classes.Verwalter;

/**
 * @class FavFragment
 * @brief Beinhaltet die Favouriten Liste
 */
public class FavFragment extends Fragment {
    RecyclerView recyclerView;
    TextView emptyView;
    public FavFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    /**
     * @brief Erstellt der RecyvlerView visuell dar. Die Elemente werden in einzelne Objekte dargestellt
     * @param view View
     * @param savedInstanceState Aktuelle Instance
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFavouriteRL);
        emptyView = (TextView) view.findViewById(R.id.empty_view_favourite);

        // Create Layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        /*
        //Looks into the Bundle and shows if there is any Cont into it
        Bundle bundle = getArguments();
        if(bundle != null) {
            Report report = (Report) bundle.getSerializable("report Object");
            reportArrayList.add(report);
        } else {
            Toast.makeText(getContext(), "Doesnt have information into it", Toast.LENGTH_SHORT).show();
        }
        */
        for (int i = 0; i < Verwalter.getFavouritenListe().size(); i++) {
            Log.d("favouritenListe","Favouriten ID: " + String.valueOf(Verwalter.getFavouritenListe().get(i).getFavouriteID()));
        }
        if (Verwalter.getFavouritenListe().isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
        if(Verwalter.getFavouritenListe().size() == 0 ) {
            FavouriteAdapter myAdapter = new FavouriteAdapter(getContext(),null);
            recyclerView.setAdapter(myAdapter);
        } else {
            for (int i = 0; i < Verwalter.getFavouritenListe().size(); i++) {
                Log.d("favouritenListe","Favouriten ID: " + String.valueOf(Verwalter.getFavouritenListe().get(i).getLadesauleID()));
            }
            // define an adapter
            // this is a referenz of the interface CustomOnMyClickListener Adapter in the class MyAdapter
            FavouriteAdapter myAdapter = new FavouriteAdapter(getContext(),Verwalter.getFavouritenListe());
            recyclerView.setAdapter(myAdapter);
        }
    }

}