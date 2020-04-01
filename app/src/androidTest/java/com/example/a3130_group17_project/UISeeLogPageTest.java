package com.example.a3130_group17_project;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class UISeeLogPageTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.signin_button)).perform(click());
            onView(withId(R.id.email)).perform(typeText("test@test.ca"));
            onView(withId(R.id.button_next)).perform(click());
            onView(withId(R.id.password)).perform(typeText("test123"));
            onView(withId(R.id.button_done)).perform(click());
        }
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.navdrawer)).perform(NavigationViewActions.navigateTo(R.id.log_summary_option));

    }

    @Test
    public void elementsExistTest(){
        onView(withId(R.id.seelog_textview)).check(matches(isDisplayed()));
        onView(withId(R.id.seelog_button)).check(matches(isDisplayed()));
        onView(withId(R.id.seelog_date)).check(matches(isDisplayed()));
    }

    @Test
    public void viewShowTest(){
        onView(withId(R.id.seelog_textview)).check(matches(withText("Choose a Date to review your consumption")));
    }
    @Test
    public void datePickerTest(){
        onView(withId(R.id.seelog_date)).check(matches(isDisplayed()));
        onView(withId(R.id.seelog_date)).perform(click());
    }

    @Test
    public void buttonClickTest() {
        onView(withId(R.id.seelog_button)).perform(click());
        try {
            onView(withText("SeeLog")).perform(click());
        } catch (NoMatchingViewException e) {

        }
    }

}
