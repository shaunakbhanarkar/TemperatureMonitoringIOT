package com.example.temperature;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.temperature.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener{

    private ArrayList<DataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        ImageView icon;
    }



    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }




    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
//        DataModel dataModel=(DataModel)object;
//
//
//
//
//        switch (v.getId())
//        {
//
////            case R.id.item_info:
////
////                Snackbar.make(v, "Release date " +dataModel.getRoomNo(), Snackbar.LENGTH_LONG)
////                        .setAction("No action", null).show();
////
////                break;
//
//
//        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.roomNo);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.temp);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.txtName.setText(dataModel.getRoomNo());
        viewHolder.txtType.setText(dataModel.getTemperature());
        viewHolder.icon.setImageResource(dataModel.getIcon());
        // Return the completed view to render on screen



//
//        if(currInt >=38)
//        {
//            // do something change color
//            convertView.setBackgroundColor (ContextCompat.getColor(mContext, R.color.Red)); // some color
//        }
//        else if(currInt<25)
//        {
//            // default state
//            convertView.setBackgroundColor (ContextCompat.getColor(mContext, R.color.Blue)); // default coloe
//        }

        return convertView;
    }

}
