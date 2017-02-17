package com.backbase.boardmembers.ui.memberslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.backbase.boardmembers.R;

/**
 * Created by mohamed on 17/02/17.
 */
public class MembersListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_list_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MembersListFragment())
                    .commit();
        }

    }

}
