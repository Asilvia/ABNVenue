package com.abn.asilvia.abnvenue.util;

import android.content.Context;
import android.content.Intent;

import com.abn.asilvia.abnvenue.ui.details.DetailsActivity;

/**
 * Created by asilvia on 20/05/2018.
 */

public class AppNavigation {

    public static void goToDetailsActivity(Context context, String id, String title) {

        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }
}
