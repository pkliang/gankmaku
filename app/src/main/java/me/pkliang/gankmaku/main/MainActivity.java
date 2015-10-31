package me.pkliang.gankmaku.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import me.pkliang.gankmaku.R;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      String fragment = getIntent().getStringExtra("fragment");
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.container, Fragment.instantiate(this,
              fragment == null ? "me.pkliang.gankmaku.fuli.FuliFragment" : fragment))
          .commit();
    }
  }
}
