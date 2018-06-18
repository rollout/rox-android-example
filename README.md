ROX
==============

## How to use this project 

Follow [Rollout.io](https://app.rollout.io/) installation instrcutions and place `rollout environment key` in `AndroidManifest.xml` file

## What can you control

This project includes 3 flags:

  * enable fab - Controls the Floating Action Button visibility
 * Title Color - a enum base solution that controls the title colors
 * enable flags activity - Allows you to control who see's rollout built in flag activity controller (see [docs](https://support.rollout.io/docs/flags-view)
 
 You can see these flags definitons here: (`MyRoxContainer.java`)
 ```java
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
```

And flag usage example here: (from `MainActivity.java`)
```java
 private void setCameraButton() {
    if (roxContainer.enableFab.isEnabled()) {
      fab.setVisibility(View.VISIBLE);
      toolbarEditButton.setVisible(false);
    } else {
      fab.setVisibility(View.GONE);
      toolbarEditButton.setVisible(true);
    }

    setupEditButtonBehaviour(roxContainer);
  }
```


## Continuous Feature Rollouts for Mobile Release

Release mobile features quickly and safely with fully controlled rollouts, measure impact, and react as needed without waiting for your next code release.

## How to get started
Visit http://www.rollout.io/ to add ROX SDK to your app

## Features

* **Mobile-first:** ROX is the first feature flagging system built from the ground up to run directly inside phones, tablets and wearables.
* **Simple:** ROX allows developer to focus on their business logic and not working with complex configuration on the dashabord 
* **Modern:** Using static types, the compiler and IDE are responsible to prevent collisions to allow easy flags discovery by develoeprs (by autocomplete) 
* **Support Optimizations:** ROX unique proposition is that is supports the company needs a full capable exerimentation system, for A/B/N testing, optimizations, customizations and more
* **Remote Conifguraion included:** ROX include a remote configuraiton module that allows developers to defined configuration that can be controlled from the server

## Getting Started

Please see the detailed instructions in our docs on how to [install Rollout SDK in your android project](https://support.rollout.io/docs/android) and [Rollout getting started](https://support.rollout.io/docs/getting-started-guide) to get you quickly up and running.

## Documentation

Getting started guide, use cases, examples and videos can be found in [Rollout support site](https://support.rollout.io)

## Feedback

**_If you use ROX and are happy with it, all we ask is that you please consider emailing [support@rollout.io](mailto:support@rollout.io) to share your thoughts!_**

**_And if you don't like it, please let us know what you would like improved, so we can fix it!_**
