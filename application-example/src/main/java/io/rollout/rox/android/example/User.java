package io.rollout.rox.android.example;

public class User {

  public boolean isTester() {
    return true;
  }

  public static User loadUser() {
    return new User();
  }
}
