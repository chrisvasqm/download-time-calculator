package com.christianv07.dev.speedy.contactActivities;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.christianv07.dev.speedy.R;

class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] cards;
    private final Integer[] cardsInt;

    public CustomAdapter(Activity context, String[] cards, Integer[] cardsInt) {
        super(context, R.layout.cards_layout, cards);

        this.context = context;
        this.cards = cards;
        this.cardsInt = cardsInt;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater cardsInflater = LayoutInflater.from(getContext());
        View customView = cardsInflater.inflate(R.layout.cards_layout, parent, false);

        String singleCardItem = getItem(position);
        ImageView cardPic = (ImageView) customView.findViewById(R.id.profpic);
        TextView cardText = (TextView) customView.findViewById(R.id.txtName);

        cardText.setText(singleCardItem);
        cardPic.setImageResource(cardsInt[position]);

        return customView;
    }
}
