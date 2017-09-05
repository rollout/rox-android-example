package io.rollout.rox.android.example;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class CustomTestRunner extends AndroidJUnitRunner {

  @Override
  public Application newApplication(ClassLoader classLoader, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return super.newApplication(classLoader, TestApp.class.getName(), context);
  }
}
