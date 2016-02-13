package com.epicodus.epicoduspetstore;

import android.support.test.rule.ActivityTestRule;

import com.epicodus.epicoduspetstore.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by staff on 2/11/16.
 */
public class MainActivityInstrumentationTests {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void validateEditText() {
        onView(withId(R.id.textView)).check(matches(withText("Welcome")));
    }
}
