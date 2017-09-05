package io.rollout.rox.android.example;

import android.app.Application;

import io.rollout.android.Rox;

public class App extends Application {

  protected MyRoxContainer roxContainer;

  @Override
  public void onCreate() {
    super.onCreate();

    // setup Rox
    this.roxContainer = new MyRoxContainer(this);
    Rox.register(roxContainer);
    Rox.setup(this);
  }

  public MyRoxContainer getRoxContainer() {
    return roxContainer;
  }
}
