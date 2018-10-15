package com.test.testgame.ui.units;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.testgame.R;
import com.test.testgame.model.Personal;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UnitsActivity extends AppCompatActivity implements ItemWarrionFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        getSupportActionBar().setTitle("Select units");
        EventBus.getDefault().register(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HelloWorldEvent event){
        // your implementation
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Item1:
                Toast.makeText(this,
                        "Молодец что нажал Item1", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.Item2:
                Toast.makeText(this,
                        "Молодец что нажал Item2", Toast.LENGTH_SHORT)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListFragmentInteraction(Personal item) {
        Toast.makeText(this,
                "Молодец что нажал ", Toast.LENGTH_SHORT)
                .show();
    }
    public static class HelloWorldEvent {
        private final String message;

        public HelloWorldEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
