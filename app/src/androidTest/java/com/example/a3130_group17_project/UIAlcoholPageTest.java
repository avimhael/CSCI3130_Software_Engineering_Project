package com.example.a3130_group17_project;

import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class UIAlcoholPageTest {

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
    }

    @Test
    public void elementExistTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.log_button)).check(matches(isDisplayed()));
        onView(withId(R.id.product_name)).check(matches(isDisplayed()));
        onView(withId(R.id.apv_percentage)).check(matches(isDisplayed()));
        onView(withId(R.id.date_picker)).check(matches(isDisplayed()));
    }
    @Test
    public void logButtonTest(){
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.log_button)).perform(click());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
        onView((withId(R.id.status_line))).check(matches(withText("Beer1 logged successfully!")));
    }

    @Test
    public void notLoggedInTest() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.signin_button)).perform(click());
        }
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.log_button)).perform(click());
        onView((withId(R.id.status_line))).check(matches(withText("You must be signed in to log products!")));
    }
}

