package vnu.uet.tuan.myuet.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vnu.uet.tuan.myuet.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoadingFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadingFrag extends Fragment {


    public LoadingFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * @return A new instance of fragment LoadingFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static LoadingFrag newInstance() {
        LoadingFrag fragment = new LoadingFrag();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

}
