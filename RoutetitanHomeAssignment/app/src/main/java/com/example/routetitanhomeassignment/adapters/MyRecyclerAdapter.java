package com.example.routetitanhomeassignment.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routetitanhomeassignment.R;
import com.example.routetitanhomeassignment.fragments.MapFragment;
import com.example.routetitanhomeassignment.ui.main.Adresses;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
     List<Adresses> adressList;
    private String[] st_estimated_time_arrival;
    private String[] st_delivery;
    private Context context;
    private Random random;
    private int rand_int = 50000;
    private int nPos;
    private AddresClickListener clickListener;

    public MyRecyclerAdapter(Context context, String[] st_estimated_time_arrival, String[] st_delivery,
                             List<Adresses> addressesList, AddresClickListener clickListener) {
        this.context = context;
        this.st_estimated_time_arrival = st_estimated_time_arrival;
        this.st_delivery = st_delivery;
        this.adressList= addressesList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        switch (viewType)
        {
            case 0: view = layoutInflater.inflate(R.layout.list_item_add, parent, false); break;
            case 1: view = layoutInflater.inflate(R.layout.list_item_add_active, parent, false); break;
            case 2: view = layoutInflater.inflate(R.layout.list_item_add_ended, parent, false); break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
       // View view = layoutInflater.inflate(R.layout.list_item_add, parent, false);
        return new MyViewHolder(view, viewType, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder itemViewHolder, final int position) {
        itemViewHolder.bind(position);
     }

    private void removeItem(int pos) {
         adressList.remove(pos);
        notifyItemRemoved(pos);
    }

    @Override
    public int getItemCount() {
        return adressList.size();
    }

    public boolean checkInfo_picker(String estimated_time, String st_delivery) {
        DateFormat df = new SimpleDateFormat("hh:mm:ss");
        long diffInMillies = 0;
        try {
            Time estimated = (Time) df.parse(estimated_time);
            Time delivery = (Time) df.parse(st_delivery);
            diffInMillies = Math.abs(delivery.getTime() - estimated.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diffInMillies > 1;
    }

    @Override
    public int getItemViewType(int position) {
        return adressList.get(position).getType();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private AddresClickListener clickListener;

        TextView tv_estimated_time_arrival;
        TextView tv_deliver;
        TextView current_id;
        TextView tv_navi;
        TextView tv_finish;
        TextView tv_st;
        TextView info_icon;
        boolean isShow;


        TextView tv_random_number;
        AppCompatImageView imageView_current_id;
        AppCompatImageButton button_finish;
        AppCompatImageButton button_navi;
        ConstraintLayout constraint_main_layout;
        ConstraintLayout constraint_layout;
        androidx.cardview.widget.CardView cardView;

        MyViewHolder(@NonNull View itemView, int nType, AddresClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            isShow = false;
            if(nType != 2) {

                tv_estimated_time_arrival = itemView.findViewById(R.id.tv_estimated_time_arrival);
                tv_deliver = itemView.findViewById(R.id.tv_delivery_time);
                current_id = itemView.findViewById(R.id.current_id);
            }
            if(nType == 1) {
                button_finish = itemView.findViewById(R.id.img_finish);
                button_navi = itemView.findViewById(R.id.img_navi);
                tv_navi = itemView.findViewById(R.id.tv_navi);
                tv_finish = itemView.findViewById(R.id.tv_finish);
            }

            cardView = itemView.findViewById(R.id.card_view_id);
            info_icon = itemView.findViewById(R.id.info_icon);
            imageView_current_id = itemView.findViewById(R.id.img_view_id);
            tv_st = itemView.findViewById(R.id.tv_st);
            tv_random_number = itemView.findViewById(R.id.tv_random_number);
            constraint_main_layout = itemView.findViewById(R.id.constraint_main_layout);
            constraint_layout = itemView.findViewById(R.id.constraint_layout);

        }

        public void setData(Adresses adresses, int position) {


        }

        public void bind(final int pos) {
            final int position = getLayoutPosition() ;
            if(adressList.get(position).getType() == 0 || adressList.get(position).getType() == 1) {
                tv_estimated_time_arrival.setText(st_estimated_time_arrival[position]);
                tv_deliver.setText(st_delivery[position]);
            }

            if(adressList.get(position).getType() == 1) {
                button_navi.setVisibility(View.GONE);
                button_finish.setVisibility(View.GONE);
                tv_finish.setVisibility(View.GONE);
                tv_navi.setVisibility(View.GONE);
            }

            tv_st.setText(adressList.get(position).getStreet());


             final String int_random_number = valueOf(adressList.get(position).getRandom());

             tv_random_number.setText(int_random_number);
            if(adressList.get(position).getType() != 2)
                current_id.setText(String.valueOf(position));

            if(adressList.get(position).getType() == 1) {

                button_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        adressList.get(getLayoutPosition()).setType(2);
                        adressList.get(getLayoutPosition() + 1).setType(1);
                        notifyItemChanged(getLayoutPosition());
                        notifyItemChanged(getLayoutPosition()+1);
                    }
                });
                button_navi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        clickListener.onClicked(adressList.get(position));

                    }
                });
            }

            constraint_main_layout.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {

                    if(adressList.get(position).getType() == 1) {
                        button_navi.setVisibility(!isShow ? View.VISIBLE : View.GONE);
                        button_finish.setVisibility(!isShow ? View.VISIBLE : View.GONE);
                        tv_finish.setVisibility(!isShow ? View.VISIBLE : View.GONE);
                        tv_navi.setVisibility(!isShow ? View.VISIBLE : View.GONE);
                        isShow = !isShow;
                    }
                }
            });
        }
    }

    public interface AddresClickListener {
       void  onClicked(Adresses adresses);
    }
}
