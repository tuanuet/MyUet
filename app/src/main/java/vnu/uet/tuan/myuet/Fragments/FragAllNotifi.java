package vnu.uet.tuan.myuet.Fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import vnu.uet.tuan.myuet.Adapter.Adapter_RecycleView;
import vnu.uet.tuan.myuet.KiemTraKetNoiInternet.Kiemtra;
import vnu.uet.tuan.myuet.Models.Noti_data;
import vnu.uet.tuan.myuet.R;
import vnu.uet.tuan.myuet.Receiver.Receiver;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragAllNotifi extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    Adapter_RecycleView adapter;
    ArrayList<Noti_data> list;
    Boolean isLoading = false;
    private int pageNumber = 0;
    private SwipeRefreshLayout mSwipeRefresh;
    private LoadingFrag loadingFrag;
    public FragAllNotifi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_notifi, container, false);

        //init
        init(view);

        //onRefresh
        //cap nhap lai recycleView
        mSwipeRefresh.setOnRefreshListener(this);

        //lay data lan dau

        getDatafromUet(pageNumber);



        //sau khoang thoi gian 5s thi se get du leu ve
        interValData(1000*60*10);

        //load moredata
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                //Check when scroll to last item in listview, in this tut, init data in listview = 10 item
                if(lastVisiblePosition == recyclerView.getAdapter().getItemCount()-1
                        && isLoading == false && recyclerView.getAdapter().getItemCount()!=1) {
                    isLoading = true;
                    pageNumber++;
                    getDatafromUet(pageNumber);

                }
            }
        });

        return view;
    }


    private void interValData(long timetomilis) {
        Intent intent =new Intent(getContext(), Receiver.class);
        PendingIntent pendingIntent= PendingIntent
                .getBroadcast(getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                timetomilis,
                pendingIntent);

    }

    //add them phan tu dau tien de dung load more
    private void init(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recycleview);
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout_allnoti);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        list = new ArrayList<>();
        initListwithbottomlayout("UNREFRESH");
        adapter= new Adapter_RecycleView(getActivity(),list);
        recyclerView.setAdapter(adapter);
        loadingFrag = LoadingFrag.newInstance();

    }

    private void initListwithbottomlayout(String isRefresh) {

        list.add(new Noti_data("",isRefresh));
    }

    public void getDatafromUet(final int page){
        Kiemtra check_internet = new Kiemtra(getActivity());
        final boolean isConnected = check_internet.checkMobileInternetConn();
        if(isConnected){
            //BackGround thread
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new GetDataFromUET().execute(String.valueOf(page));
                }
            });
        }
        else {
            Toast.makeText(getActivity(),getResources().getString(R.string.loimang),Toast.LENGTH_SHORT).show();
        }
   }

    @Override
    public void onRefresh() {
        list.clear();
        initListwithbottomlayout("REFRESH");
        adapter.notifyDataSetChanged();
        getDatafromUet(0);

    }

    public class GetDataFromUET extends AsyncTask<String,Void,ArrayList> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("PreEx","preEx");

        }

        @Override
        protected ArrayList doInBackground(String... params) {

            ArrayList datas = new ArrayList();

            String numberPage= params[0];
            String url = "http://www.uet.vnu.edu.vn/taxonomy/term/53"+"?page="+numberPage;
            Document doc = null;

            try {
                doc = Jsoup.connect(url).get();
                Elements content = doc.select(".view-content div.views-row");


                for (int i = 0; i < content.size(); i++) {
                    try{
                        Element div = content.get(i);

                        //lay dc title va link
                        Elements link_title =div.select(".views-field-title .field-content a");
                        Element a = link_title.get(0);
                        String link = a.attr("href");
                        String title = a.attr("alt");

                        //lay content
                        Elements cnt =div.select(".rtejustify span span");
                        Element cont = cnt.get(0);
                        String con = cont.text();

                        //
                        Noti_data item = new Noti_data(title,con,link);
                        datas.add(item);
                    }
                    catch (Exception e){
                        Log.d("exception", "doInBackground: "+e.getMessage());

                    }
                }

            } catch (IOException e) {
                Log.d("exception", "getData: "+e.getMessage());
            }
            return  datas;
        }

        private ArrayList facedata(int page) {
            ArrayList datas = new ArrayList();



            return datas;
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            super.onPostExecute(arrayList);

            if(arrayList.size()!=0){
                Log.d("PreEx","post");


                list.addAll(list.size()-1,arrayList);

                adapter.notifyItemInserted(list.size()-1);
                isLoading = false;
                if(mSwipeRefresh.isRefreshing()){
                    mSwipeRefresh.setRefreshing(false);
                }
            }


        }

    }

}
