package com.sprint3r.ronin.udemyfeedreader;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    private List<String> itemsName;
    private List<String> itemsValue;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecyclerViewAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        itemsName = new ArrayList<String>();
        itemsValue = new ArrayList<String>();
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview, viewGroup, false);
        return new ItemHolder(itemCardView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder itemHolder, int position) {
        itemHolder.setItemCourseTitle(itemsName.get(position));
        itemHolder.setItemCourseUrl(itemsValue.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsName.size();
    }

    public void add(int location, String iName, String iURL){
        itemsName.add(location, iName);
        itemsValue.add(location, iURL);
        notifyItemInserted(location);
    }

    public static class ItemHolder extends RecyclerView.ViewHolder{

        private RecyclerViewAdapter parent;
        private CardView cardView;
        TextView textCourseTitle;
        TextView textCourseUrl;

        public ItemHolder(CardView cView, RecyclerViewAdapter parent) {
            super(cView);
            cardView = cView;
            this.parent = parent;
            textCourseTitle = (TextView) cardView.findViewById(R.id.course_title);
            textCourseUrl = (TextView) cardView.findViewById(R.id.course_url);
        }

        public void setItemCourseTitle(String courseTitle){
            textCourseTitle.setText(courseTitle);
        }

        public void setItemCourseUrl(String courseUrl){
            textCourseUrl.setText(courseUrl);
        }
    }
}