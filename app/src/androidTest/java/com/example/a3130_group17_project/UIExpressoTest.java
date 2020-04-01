package com.example.a3130_group17_project;

import android.view.MenuItem;

import com.google.android.material.internal.NavigationMenuItemView;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class UIExpressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void listExistsTest(){
        onView(withId(R.id.list)).check(matches(isDisplayed()));
    }

    @Test
    public void clickableTest() {
        onView(withId(R.id.alcohol_button)).perform(click());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.product_name)).check(matches(withText("Beer1")));
        onView(withId(R.id.apv_percentage)).check(matches(withText("APV: 5%")));
        onView(withId(R.id.log_button)).check(matches(isDisplayed()));
    }

    @Test
    public void populateAlcoholTest() {
        onView(withId(R.id.alcohol_button)).perform(click());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.product_name)).check(matches(withText("Beer1")));
        onView(withId(R.id.apv_percentage)).check(matches(withText("APV: 5%")));
        onView(withId(R.id.log_button)).check(matches(isDisplayed()));
    }

    @Test
    public void populateCannabisTest() {
        onView(withId(R.id.cannabis_button)).perform(click());
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.product_name)).check(matches(withText("Cann1")));
        onView(withId(R.id.thc_percentage)).check(matches(withText("THC: 15%")));
        onView(withId(R.id.cbd_percentage)).check(matches(withText("CBD: 15%")));
        onView(withId(R.id.log_button)).check(matches(isDisplayed()));
    }

    @Test
    public void navigationDrawerTest() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navdrawer)).check(matches(isDisplayed()));
    }

    @Test
    public void signInButtonTest() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.signin_button)).perform(click());
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    @Test
    public void signInButtonResponseTest() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.signin_button)).perform(click());
        onView(withId(R.id.email)).perform(typeText("test@test.ca"));
        onView(withId(R.id.button_next)).perform(click());
        onView(withId(R.id.password)).perform(typeText("test123"));
        onView(withId(R.id.button_done)).perform(click());

        onView(withId(R.id.signin_button)).check(matches(withText("Sign Out")));
        onView(withId(R.id.nav_drawer_header_display_name)).check(matches(withText("Test User")));
        onView(withId(R.id.nav_drawer_header_email)).check(matches(withText("test@test.ca")));
    }

    @Test
    public void navigationDrawerHeaderTest() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_drawer_header_display_name)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_drawer_header_email)).check(matches(isDisplayed()));
        onView(withId(R.id.nav_drawer_header_icon)).check(matches(isDisplayed()));
    }

    @Test
    public void signOutButtonResponseTest() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.signin_button)).perform(click());
            onView(withId(R.id.email)).perform(typeText("test@test.ca"));
            onView(withId(R.id.button_next)).perform(click());
            onView(withId(R.id.password)).perform(typeText("test123"));
            onView(withId(R.id.button_done)).perform(click());
        }
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.signin_button)).perform(click());

        onView(withId(R.id.signin_button)).check(matches(withText("Sign In")));
        onView(withId(R.id.nav_drawer_header_display_name)).check(matches(withText("Not Signed In")));
        onView(withId(R.id.nav_drawer_header_email)).check(matches(withText("Click below to sign in!")));
    }

    @Test
    public void navDrawerListItems() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Product Lists")).check(matches(isDisplayed()));
        onView(withText("Log Summary")).check(matches(isDisplayed()));
    }

    @Test
    public void logSummaryButtonDisableTest() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.signin_button)).perform(click());
        }

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText("Log Summary")).check(matches(hasTextColor(R.color.colorAccent)));
    }

    @Test
    public void logSummaryButtonEnabledTest() {
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.signin_button)).perform(click());
        onView(withId(R.id.email)).perform(typeText("test@test.ca"));
        onView(withId(R.id.button_next)).perform(click());
        onView(withId(R.id.password)).perform(typeText("test123"));
        onView(withId(R.id.button_done)).perform(click());

        onView(withText("Log Summary")).check(matches(hasTextColor(R.color.iconColorUnchecked)));
    }
}
