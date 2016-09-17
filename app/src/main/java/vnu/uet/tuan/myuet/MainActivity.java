package vnu.uet.tuan.myuet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vnu.uet.tuan.myuet.Fragments.ListNotification;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity,new ListNotification())
                    .commit();
        }
    }
}
