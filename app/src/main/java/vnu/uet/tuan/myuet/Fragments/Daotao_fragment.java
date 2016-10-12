package vnu.uet.tuan.myuet.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vnu.uet.tuan.myuet.Adapter.TabAdapter;
import vnu.uet.tuan.myuet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Daotao_fragment extends Fragment implements TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager pager;
    public Daotao_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daotao, container, false);
        // khoi tao Tab, ViewPager
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        pager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(pager);

        return view;
    }
    /**
     * cai dat ViewPager
     * Tao class TabAdapter de dua vao ViewPager
     * @param pager
     */
    private void setupViewPager(ViewPager pager) {
        TabAdapter adapter = new TabAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new FragAllNotifi(), getResources().getString(R.string.tkb));
        adapter.addFragment(new FragAllNotifi(), getResources().getString(R.string.tuyendung));
        adapter.addFragment(new FragAllNotifi(), getResources().getString(R.string.hocbong));
        adapter.addFragment(new FragAllNotifi(), getResources().getString(R.string.other));

        tabLayout.setupWithViewPager(pager);
        tabLayout.addOnTabSelectedListener(this);
        pager.setAdapter(adapter);


        //setcustomview cho tap
    }

    //Tab click
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.e("TAB","Click tab " + tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
