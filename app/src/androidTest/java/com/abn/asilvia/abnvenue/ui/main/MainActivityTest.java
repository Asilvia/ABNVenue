package com.abn.asilvia.abnvenue.ui.main;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by asilvia on 22/05/2018.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = activityActivityTestRule.getActivity();
    }

    @Test
    public void testSearchLounch() {
        View search = mainActivity.mActivityStartBinding.tvLastSearched;
        assertNotNull(search);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}