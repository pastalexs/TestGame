package com.test.testgame.ui.main.scene;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.test.testgame.R;
import com.test.testgame.model.Arena;
import com.test.testgame.model.Personal;
import com.test.testgame.model.Warrion;
import com.test.testgame.ui.main.MainActivity;
import com.test.testgame.ui.units.ItemWarrionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PersonalFragment extends MvpFragment<PersonalContract.View, PersonalContract.UserActionsListener>
        implements PersonalContract.View, ItemWarrionFragment.OnListFragmentInteractionListener {

    @BindView(R.id.buttonSelect)
    protected Button buttonSelect;
    @BindView(R.id.buttonAttack)
    protected Button buttonAttack;
    @BindView(R.id.buttonPropertis)
    protected Button buttonPropertis;

    @BindView(R.id.textViewName)
    protected TextView mNameText;
    @BindView(R.id.textViewClass)
    protected TextView mClassText;
    @BindView(R.id.textViewHP)
    protected TextView mHealthPointText;
    @BindView(R.id.textViewAttack)
    protected TextView mAttackText;
    @BindView(R.id.textViewProtection)
    protected TextView mProtectionText;
    @BindView(R.id.progressBarHP)
    protected ProgressBar mProgressBar;
    private View view;

    private MyTextListener listener;
    private String name;
    private ItemWarrionFragment itemWarrionFragment;

    @Override
    public PersonalContract.UserActionsListener createPresenter() {
        return new PersonalPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            name = savedInstanceState.getString(MainActivity.NAME);
        } else {
            name = getArguments().getString(MainActivity.NAME);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(MainActivity.NAME, name);
        super.onSaveInstanceState(outState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        updatePerconalData();
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try {
            listener = (MyTextListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement MyTextListener");
        }
    }

    public void updatePerconalData() {
        Warrion mWarrion = getPresenter().getWarrion(name);
        mProgressBar.setMax(mWarrion.getCurrenciWarrion().getMaxHealthPoint());
        mClassText.setText(mWarrion.getCurrenciWarrion().getPclass().toString());
        mNameText.setText(name);
        mAttackText.setText(String.valueOf(mWarrion.getAttack()));
        mProtectionText.setText(String.valueOf(mWarrion.getProtect()));
        mHealthPointText.setText(mWarrion.getHealthPoint() + "/"
                + mWarrion.getCurrenciWarrion().getMaxHealthPoint());
        mProgressBar.setProgress(mWarrion.getHealthPoint());
    }

    public void buttonAllEnable(Boolean enable) {
        buttonSelect.setEnabled(enable);
        buttonAttack.setEnabled(enable);
        buttonPropertis.setEnabled(enable);
    }

    @OnClick(R.id.buttonSelect)
    protected void onClickSelect() {
        itemWarrionFragment = ItemWarrionFragment.newInstance(4);
        itemWarrionFragment.setmListener(this);
        itemWarrionFragment.show(getFragmentManager(), "DialogSelecUnits");
    }

    @OnClick(R.id.buttonAttack)
    protected void onClickAttack() {
        getPresenter().attackWarrion();
        buttonAllEnable(false);
    }

    @OnClick(R.id.buttonPropertis)
    protected void onClickProtection() {
        getPresenter().protectionWarrion();
        buttonAllEnable(false);
    }

    @Override
    public void setPersonal() {
        updatePerconalData();
    }

    @Override
    public void showCommandText(int id, String text) {
        listener.setText(name + ": " + getString(id) + " " + text);
    }

    @Override
    public void onListFragmentInteraction(Personal item) {
        itemWarrionFragment.dismiss();
        getPresenter().getPersonal(item.getPclass());
    }

    public interface MyTextListener {
        void setText(String text);
    }
}
