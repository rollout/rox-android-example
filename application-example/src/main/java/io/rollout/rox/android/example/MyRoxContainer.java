package io.rollout.rox.android.example;

import android.content.Context;

import io.rollout.configuration.RoxContainer;
import io.rollout.flags.RoxEnumVariant;
import io.rollout.flags.RoxFlag;
import io.rollout.flags.RoxVariant;
import io.rollout.remoteconfiguration.types.RoxConfigurationString;

public class MyRoxContainer implements RoxContainer {

  // welcome message ROX configuration
  public RoxConfigurationString welcomeMessage;

  // enableFab flag
  public RoxFlag enableFab = new RoxFlag(true);

/*
 * Enum based multi variant feature flag with default value white
 * This can also be done without Enum with the following code
 *
 * <pre>
 * public RoxVariant titleColors = new RoxVariant("White", new String[] {"White, ""Blue", "Yellow"});
 * </pre>
 */
  public RoxEnumVariant<Color> titleColors = new RoxEnumVariant<>(Color.WHITE);

  public enum Color {
    WHITE, BLUE, YELLOW
  }

  // Flag value is false by default
  public RoxFlag enableFlagsActivity = new RoxFlag();

  // declare more flags here
  // ...

  public MyRoxContainer(Context context) {

    //Example for a flag that requires Android context
    String welcomeMessageDefault = context.getResources().getString(R.string.welcome_message);
    welcomeMessage = new RoxConfigurationString(welcomeMessageDefault);
  }
}
