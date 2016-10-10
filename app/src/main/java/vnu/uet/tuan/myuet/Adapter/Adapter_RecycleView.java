package vnu.uet.tuan.myuet.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import vnu.uet.tuan.myuet.Anim.AnimUlis;
import vnu.uet.tuan.myuet.Models.Noti_data;
import vnu.uet.tuan.myuet.R;

/**
 * Created by tuanu on 1/5/2016.
 */
public class Adapter_RecycleView extends RecyclerView.Adapter{
    ArrayList<Noti_data> list;
    Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    int previousposition = -1;
    public Adapter_RecycleView(Context context, ArrayList<Noti_data> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getItemViewType(int position) {

        return list.get(position).getTitle().equals("") ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(viewType == VIEW_TYPE_ITEM) {
            View view = inflater.inflate(R.layout.recycle_view_item, parent, false);
            ItemViewHolder holder = new ItemViewHolder(view);
            return holder;
        }else{
            View view = inflater.inflate(R.layout.footer_view,parent,false);
            LoadingViewHolder holder = new LoadingViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.txt_title.setText(list.get(position).getTitle());
            itemViewHolder.txt_content.setText(list.get(position).getContent());

            if(position >= previousposition ){
                AnimUlis.animate(itemViewHolder,true);
            }
            else AnimUlis.animate(itemViewHolder,false);

            previousposition = position;

        }else {
            //can than cho nay nhe
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            if(getItemCount()==1&&list.get(0).getContent().equals("REFRESH")){
                loadingViewHolder.progressBar.setVisibility(View.INVISIBLE);
            }
            else if(getItemCount()==1&&list.get(0).getContent().equals("UNREFRESH")){
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                loadingViewHolder.progressBar.setIndeterminate(true);
            }
            else {
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                loadingViewHolder.progressBar.setIndeterminate(true);
            }

        }




    }
    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }


    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView txt_title;
        TextView txt_content;
        public ItemViewHolder(final View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.recycle_item_title);
            txt_content = (TextView) itemView.findViewById(R.id.recycle_item_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
