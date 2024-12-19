package com.lockscreen.voicescreenlock.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.android.material.imageview.ShapeableImageView;
import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.activity.voice_passcode.SetThemeActivity;

import java.util.List;

public class ThemeAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> themeList;
    private int selectedPosition = -1;

    public ThemeAdapter(Context context, List<Integer> themeList) {
        this.context = context;
        this.themeList = themeList;
    }

    @Override
    public int getCount() {
        return themeList.size();
    }

    @Override
    public Object getItem(int position) {
        return themeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_theme, parent, false);
        } else {
            view = convertView;
        }

        ShapeableImageView imageView = view.findViewById(R.id.themeImage);
        imageView.setImageResource(themeList.get(position));

        if (selectedPosition == position) {
            imageView.setBackgroundResource(R.drawable.border_selected);
        } else {
            imageView.setBackgroundResource(0);
        }

        view.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();

            Intent intent = new Intent(context, SetThemeActivity.class);
            intent.putExtra("themeId", selectedPosition);
            context.startActivity(intent);
        });

        return view;
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
        notifyDataSetChanged();
    }
}