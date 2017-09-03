package io.rollout.rox.android.example;

import android.content.Context;

import io.rollout.android.configuration.RoxContainer;
import io.rollout.flags.RoxFlag;
import io.rollout.remoteconfiguration.types.RoxConfigurationString;

/**
 * Created by shimi on 03/09/2017.
 */

public class MyRoxContainer extends RoxContainer {

  // remove namespace prefix
  public String getNamespace() {
    return "";
  }

  // welcome message ROX configuration
  public RoxConfigurationString welcomeMessage;

  // enableFab flag
  public RoxFlag enableFab = new RoxFlag();

  // declare more flags here
  // ...

  public MyRoxContainer(Context context) {
    String welcomeMessageDefault = context.getResources().getString(R.string.welcome_message);
    welcomeMessage = new RoxConfigurationString(welcomeMessageDefault);
  }
}
