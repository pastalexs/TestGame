package com.test.testgame.ui.units;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.testgame.R;
import com.test.testgame.model.CreaterUnits;
import com.test.testgame.model.Personal;

import java.util.ArrayList;
import java.util.List;

public class ItemWarrionFragment extends DialogFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;


    private OnListFragmentInteractionListener mListener;

    public ItemWarrionFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.SelecClassUnit);
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorBackgroud);
        return dialog;
    }
    public static ItemWarrionFragment newInstance(int columnCount) {
        ItemWarrionFragment fragment = new ItemWarrionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }
    public void setmListener(OnListFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemwarrion_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            List<Personal> items = new ArrayList<Personal>();
            Personal personal = CreaterUnits.getPaper();
            items.add(personal);
            personal = CreaterUnits.getRock();
            items.add(personal);
            personal = CreaterUnits.getScissors();
            items.add(personal);
            recyclerView.setAdapter(new MyItemWarrionRecyclerViewAdapter(items, mListener));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Personal item);
    }
}
