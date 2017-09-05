package io.rollout.rox.android.example;

public class TestApp extends App {

  @Override
  public void onCreate() {
    super.onCreate();
    MockitoRox mockitoRox = new MockitoRox();
    mockitoRox.mock(getRoxContainer());
  }

}