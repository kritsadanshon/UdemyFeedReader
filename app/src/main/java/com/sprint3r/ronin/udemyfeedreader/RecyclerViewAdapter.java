package com.sprint3r.ronin.udemyfeedreader;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    private Context context;
    private List<String> itemsTitle;
    private List<Bitmap> itemBitmapImage;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        itemsTitle = new ArrayList();
        itemBitmapImage = new ArrayList();
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        CardView itemCardView
                = (CardView)layoutInflater.inflate(R.layout.layout_cardview, viewGroup, false);
        return new ItemHolder(itemCardView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder itemHolder, int position) {
        itemHolder.setItemCourseTitle(itemsTitle.get(position));
        itemHolder.setImageView(itemBitmapImage.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsTitle.size();
    }

    public void add(int index, String title, Bitmap bitmapImage) {
        itemsTitle.add(index, Integer.toString(index + 1) + " : " + title);
        itemBitmapImage.add(index, bitmapImage);
        notifyItemInserted(getItemCount());
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private RecyclerViewAdapter parent;
        private CardView cardView;
        TextView textCourseTitle;
        ImageView bitmapImageCourse;

        public ItemHolder(CardView cView, RecyclerViewAdapter parent) {
            super(cView);
            cardView = cView;
            this.parent = parent;
            textCourseTitle = (TextView) cardView.findViewById(R.id.course_title);
            bitmapImageCourse = (ImageView) cardView.findViewById(R.id.course_image);
        }

        public void setItemCourseTitle(String courseTitle) {
            textCourseTitle.setText(courseTitle);
        }

        public void setImageView(Bitmap imageCourse) {
            bitmapImageCourse.setImageBitmap(imageCourse);
        }
    }
}

