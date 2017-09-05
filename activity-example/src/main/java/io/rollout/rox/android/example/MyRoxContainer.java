package io.rollout.rox.android.example;

import android.content.Context;

import io.rollout.android.configuration.RoxContainer;
import io.rollout.flags.RoxFlag;
import io.rollout.flags.RoxVariant;
import io.rollout.remoteconfiguration.types.RoxConfigurationString;

public class MyRoxContainer extends RoxContainer {

  // remove namespace prefix
  public String getNamespace() {
    return "";
  }

  // welcome message ROX configuration
  public RoxConfigurationString welcomeMessage;

  // enableFab flag
  public RoxFlag enableFab = new RoxFlag();

  // multi variant feature flag for testing the best color for the title
  public RoxVariant titleColors = new RoxVariant("Black", "Blue", "Green", "Yellow");


  // declare more flags here
  // ...

  public MyRoxContainer(Context context) {

    //Example for a flag that require context
    String welcomeMessageDefault = context.getResources().getString(R.string.welcome_message);
    welcomeMessage = new RoxConfigurationString(welcomeMessageDefault);
  }
}
