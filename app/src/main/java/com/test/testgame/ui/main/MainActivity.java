package com.test.testgame.ui.main;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.test.testgame.R;
import com.test.testgame.model.Arena;
import com.test.testgame.model.Personal;
import com.test.testgame.model.event.FinishGame;
import com.test.testgame.ui.main.scene.PersonalFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainContract.View, MainContract.UserActionsListener>
        implements PersonalFragment.MyTextListener, MainContract.View {

    public static String PERSONAL1 = "Warrior Left";
    public static String PERSONAL2 = "Warrior Right";
    public final static String NAME = "name";
    @BindView(R.id.buttonFightingUnits)
    protected Button buttonFightingUnits;
    @Nullable
    @BindView(R.id.textViewStatus)
    protected TextView mTextViewStatus;
    @BindView(R.id.textViewFinish)
    protected TextView textViewFinish;
    @BindView(R.id.layoutFinish)
    protected ConstraintLayout layoutFinish;
    private PersonalFragment personalFragment1;
    private PersonalFragment personalFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        EventBus.getDefault().register(this);
        restartGame();
    }

    private void restartGame() {
        buttonFightingUnits.setEnabled(false);
        layoutFinish.setVisibility(View.GONE);
        mTextViewStatus.setText(getString(R.string.StartGame));
        Arena.deletInstance();
        Arena.getInstance(PERSONAL1, Personal.Pclass.ROCK,
                PERSONAL2, Personal.Pclass.PAPER);
        personalFragment1 = new PersonalFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NAME, PERSONAL1);
        personalFragment1.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPersonal1, personalFragment1, PERSONAL1).commit();

        personalFragment2 = new PersonalFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(NAME, PERSONAL2);
        personalFragment2.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentPersonal2, personalFragment2, PERSONAL2).commit();
        mTextViewStatus.setMovementMethod(new ScrollingMovementMethod());
    }

    @NonNull
    @Override
    public MainContract.UserActionsListener createPresenter() {
        return new MainPresentor();
    }

    @Override
    public void setText(String text) {
        mTextViewStatus.setText(text + "\n" + mTextViewStatus.getText());
    }

    private int index = 0;
    @Override
    public void setOnClick() {
        index++;
        if (index >= 2) {
            buttonFightingUnits.setEnabled(true);
        }
    }

    @OnClick(R.id.buttonFightingUnits)
    protected void onClickSelectUnits() {
        mTextViewStatus.setText(getString(R.string.Fighting) + "\n" + mTextViewStatus.getText());
        Arena.fighting();
        personalFragment1.updatePerconalData();
        personalFragment2.updatePerconalData();

        personalFragment1.buttonAllEnable(true);
        personalFragment2.buttonAllEnable(true);
        buttonFightingUnits.setEnabled(false);
        index=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FinishGame event) {
        textViewFinish.setText(getString(event.getIdMessage()) + " " + event.getNameWinner());
        layoutFinish.setVisibility(View.VISIBLE);
        personalFragment1.getView().setEnabled(false);
        personalFragment2.getView().setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Item1:
                restartGame();
                return true;
            case R.id.Item2:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
