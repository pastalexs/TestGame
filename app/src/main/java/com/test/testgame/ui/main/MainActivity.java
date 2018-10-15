package com.test.testgame.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.test.testgame.R;
import com.test.testgame.model.Arena;
import com.test.testgame.model.Personal;
import com.test.testgame.model.event.FinishGame;
import com.test.testgame.ui.main.myfragment.TestFragment;
import com.test.testgame.ui.main.scene.PersonalFragment;
import com.test.testgame.ui.units.UnitsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainContract.View, MainContract.UserActionsListener>
        implements PersonalFragment.MyTextListener, MainContract.View {

    public final static String PERSONAL1 = "PERSONA1";
    public final static String PERSONAL2 = "PERSONA2";
    public final static String NAME = "name";
    @Nullable
    @BindView(R.id.textViewStatus)
    protected TextView mTextViewStatus;
    @BindView(R.id.textViewFinish)
    protected TextView textViewFinish;
    private PersonalFragment personalFragment1;
    private PersonalFragment personalFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        EventBus.getDefault().register(this);
        getSupportActionBar().show();
        restartGame();
    }

    private void restartGame() {
        textViewFinish.setVisibility(View.GONE);
        mTextViewStatus.setText("Start game");
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

    @OnClick(R.id.buttonFightingUnits)
    protected void onClickSelectUnits() {
        //Intent intent = new Intent(this, UnitsActivity.class);
        //startActivity(intent);
        mTextViewStatus.setText("Fighting\n" + mTextViewStatus.getText());
        Arena.fighting();
        personalFragment1.updatePerconalData();
        personalFragment2.updatePerconalData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FinishGame event) {
        textViewFinish.setText(event.getMessage());
        textViewFinish.setVisibility(View.VISIBLE);
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
