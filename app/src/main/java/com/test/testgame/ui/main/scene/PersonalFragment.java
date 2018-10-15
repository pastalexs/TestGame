package com.test.testgame.ui.main.scene;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Warrion mWarrion;
    private String name;
    private Arena arena = Arena.getInstance();

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
      //  getPresenter().getPersonal(Personal.Pclass.PAPER);
        arena = Arena.getInstance();
        updatePerconalData();
        return view;
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

    public void setTestStatus(String text) {
        listener.setText(name+": "+text);
    }

    private void updateIndecatorHealthPoint() {
        mHealthPointText.setText(mWarrion.getHealthPoint() + "/" + mWarrion.getCurrenciWarrion().getMaxHealthPoint());
        mProgressBar.setProgress(mWarrion.getHealthPoint());
    }

    public void updatePerconalData() {
        mWarrion = arena.getWarrion(name);
        mProgressBar.setMax(mWarrion.getCurrenciWarrion().getMaxHealthPoint());
        mClassText.setText(mWarrion.getCurrenciWarrion().getPclass().toString());
        mNameText.setText(name);
        mAttackText.setText(String.valueOf(mWarrion.getAttack()));
        mProtectionText.setText(String.valueOf(mWarrion.getProtect()));
        updateIndecatorHealthPoint();
    }



    @OnClick(R.id.buttonSelect)
    protected void onClickSelect() {
        new ItemWarrionFragment().show(getFragmentManager(),"tag");
       /* View dialogView =
                View.inflate(getContext(), R.layout.fragment_itemwarrion_list, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle("Selec class unit")
                .setView(dialogView)
                .create();
        dialogView.findViewById(R.id.buttonPaper)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().getPersonal(Personal.Pclass.PAPER);
                        alertDialog.dismiss();
                    }
                });
        dialogView.findViewById(R.id.buttonScissors)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().getPersonal(Personal.Pclass.SCISSORS);
                        alertDialog.dismiss();
                    }
                });
        dialogView.findViewById(R.id.buttonRock)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getPresenter().getPersonal(Personal.Pclass.ROCK);
                        alertDialog.dismiss();
                    }
                });
        alertDialog.show();*/
    }
    @OnClick(R.id.buttonAttack)
    protected void onClickAttack() {
        arena.warrionAttack(mWarrion.getName(),mWarrion.getAttack());
        setTestStatus("Attack " + mWarrion.getAttack());
    }
    @OnClick(R.id.buttonPropertis)
    protected void onClickProtection() {
        arena.warrionProtect(mWarrion.getName(),mWarrion.getProtect());
        setTestStatus("Protection " + mWarrion.getProtect() );
    }

    @Override
    public void showAttack(){
    }

    @Override
    public void showProtection() {

    }

    @Override
    public void showSelectClass() {

    }

    @Override
    public void setPersonal(Personal personal) {
       // mWarrion = personal;
        mWarrion.selectedClassUnit(personal.getPclass());
        setTestStatus("Select unit " + mWarrion.getCurrenciWarrion().getPclass());
        updatePerconalData();
    }

    @Override
    public void onListFragmentInteraction(Personal item) {
        getPresenter().getPersonal(item.getPclass());
    }

    public interface MyTextListener {
        void setText(String text);
    }
}
