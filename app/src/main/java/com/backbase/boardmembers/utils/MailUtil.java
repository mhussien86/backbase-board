package com.backbase.boardmembers.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * Created by mohamed on 19/02/17.
 */
public class MailUtil {

    public static void openMailClient(Context context , String mailAddress, String subject){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailIntent.setType("vnd.android.cursor.item/email");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {mailAddress});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
        context.startActivity(Intent.createChooser(emailIntent, "Send mail using..."));
    }
}
