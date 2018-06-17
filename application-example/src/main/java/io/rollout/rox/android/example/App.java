package io.rollout.rox.android.example;

import android.app.Application;

import io.rollout.android.Rox;
import io.rollout.properties.CustomPropertyGenerator;

public class App extends Application {

  protected MyRoxContainer roxContainer;
  private User user;
  
  @Override
  public void onCreate() {
    super.onCreate();

    this.user = User.loadUser();

    setupRox();
  }

  private void setupRox() {

    // setup Rox
    Rox.setCustomComputedBooleanProperty("isTester", new CustomPropertyGenerator<Boolean>() {
      @Override
      public Boolean generateProperty() {
        return user.isTester();
      }
    });

    this.roxContainer = new MyRoxContainer(this);
    Rox.register("", roxContainer);
    Rox.setup(this);
  }

  public MyRoxContainer getRoxContainer() {
    return roxContainer;
  }
}
