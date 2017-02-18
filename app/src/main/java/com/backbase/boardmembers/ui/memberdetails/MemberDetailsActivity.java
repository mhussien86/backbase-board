package com.backbase.boardmembers.ui.memberdetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.backbase.boardmembers.R;
import com.backbase.boardmembers.ui.memberslist.MembersListFragment;

/**
 * Created by mohamed on 18/02/17.
 */
public class MemberDetailsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MembersListFragment())
                    .commit();
        }
    }
}
