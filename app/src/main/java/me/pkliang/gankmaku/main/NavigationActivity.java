package me.pkliang.gankmaku.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.pkliang.gankmaku.R;
import me.pkliang.gankmaku.android.AndroidFragment;
import me.pkliang.gankmaku.android.IOSFragment;
import me.pkliang.gankmaku.fuli.FuliFragment;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fuli) public void onFuliClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", FuliFragment.class.getName());
        startActivity(intent);
    }

    @OnClick(R.id.android) public void onAndroidClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", AndroidFragment.class.getName());
        startActivity(intent);
    }

    @OnClick(R.id.ios) public void onIOSClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", IOSFragment.class.getName());
        startActivity(intent);
    }
}
