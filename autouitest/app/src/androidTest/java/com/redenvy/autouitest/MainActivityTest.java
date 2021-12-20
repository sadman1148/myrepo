package com.redenvy.autouitest;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    public String pass = "d3f!n!telyN0tMyActualPa$$w0rd";

    @Test
    public void test1(){
        Espresso.onView(withId(R.id.pass)).perform(typeText(pass));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.button)).perform(click());
        Espresso.onView(withId(R.id.passtxt)).check(matches(withText(pass)));
    }

    @Test
    public void test2(){
        Espresso.onView(withId(R.id.checkBox)).perform(click());
        Espresso.onView(withId(R.id.checkBox)).check(matches(withText(R.string.checked)));
        Espresso.onView(withId(R.id.checkBox)).perform(click());
        Espresso.onView(withId(R.id.checkBox)).check(matches(withText(R.string.unchecked)));
    }

    @Test
    public void test3(){
        Espresso.onView(withId(R.id.switcher)).perform(click());
        Espresso.onView(withId(R.id.switcher)).check(matches(withText(R.string.on)));
        Espresso.onView(withId(R.id.switcher)).perform(click());
        Espresso.onView(withId(R.id.switcher)).check(matches(withText(R.string.off)));
    }

    @Test
    public void test4(){
        Espresso.onView(withId(R.id.seekBar)).perform(swipeRight());
        Espresso.onView(withId(R.id.seeker)).check(matches(withText(R.string.hunnid)));
        Espresso.onView(withId(R.id.seekBar)).perform(swipeLeft());
        Espresso.onView(withId(R.id.seeker)).check(matches(withText(R.string.zero)));
    }
}