package com.lockscreen.voicescreenlock.activity.intro.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.lockscreen.voicescreenlock.R;
import com.lockscreen.voicescreenlock.databinding.ItemSlideLayoutBinding;


public class SlideAdapter extends PagerAdapter {

    private final int[] images = {R.drawable.intro1, R.drawable.intro2, R.drawable.intro3,R.drawable.intro4};
    private final int[] titles = {R.string.note_continue1, R.string.note_continue2, R.string.note_continue3, R.string.note_continue4};
    private final int[] description = {R.string.des1, R.string.des2, R.string.des3, R.string.des4};
    private final Context context;

    public SlideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemSlideLayoutBinding binding = ItemSlideLayoutBinding.inflate(LayoutInflater.from(context), container, false);
        binding.imLogoSlide.setImageResource(images[position]);
        binding.tvTitle.setText(titles[position]);
        binding.tvDes.setText(description[position]);
        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
