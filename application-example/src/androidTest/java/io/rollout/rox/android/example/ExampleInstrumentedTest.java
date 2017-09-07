package io.rollout.rox.android.example;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import io.rollout.flags.RoxFlag;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
  @Rule
  public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, false, false);

  MyRoxContainer roxContainer;

  public ExampleInstrumentedTest() {
    App app = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
    this.roxContainer = app.getRoxContainer();
  }

  @Test
  public void whenEnableFabIsFalseFabShouldNotBeVisible() {
    roxContainer.enableFab = Mockito.mock(RoxFlag.class);
    when(roxContainer.enableFab.isEnabled()).thenReturn(false);

    activityRule.launchActivity(new Intent(Intent.ACTION_MAIN));
    onView(withId(R.id.fab)).check(matches(not(isDisplayed())));
  }

  @Test
  public void whenEnableFabIsTrueFabShouldBeVisible() {
    roxContainer.enableFab = Mockito.mock(RoxFlag.class);
    when(roxContainer.enableFab.isEnabled()).thenReturn(true);

    activityRule.launchActivity(new Intent(Intent.ACTION_MAIN));
    onView(withId(R.id.fab)).check(matches(isDisplayed()));
  }
}
