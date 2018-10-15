package com.test.testgame.ui.units;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.testgame.R;
import com.test.testgame.model.Personal;
import com.test.testgame.ui.units.ItemWarrionFragment.OnListFragmentInteractionListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyItemWarrionRecyclerViewAdapter extends RecyclerView.Adapter<MyItemWarrionRecyclerViewAdapter.ViewHolder> {

    private final List<Personal> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemWarrionRecyclerViewAdapter(List<Personal> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_itemwarrion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItemPersonal = mValues.get(position);
        holder.mpClassText.setText(mValues.get(position).getPclass().toString());
        holder.mArrackText.setText(String.valueOf(mValues.get(position).getAttack()));
        holder.mProtectText.setText(String.valueOf(mValues.get(position).getProtect()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItemPersonal);
                    
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pClass)
        public TextView mpClassText;
        @BindView(R.id.attack)
        public TextView mArrackText;
        @BindView(R.id.protect)
        public TextView mProtectText;

        public Personal mItemPersonal;
        public final View mView;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;

        }
    }
}
