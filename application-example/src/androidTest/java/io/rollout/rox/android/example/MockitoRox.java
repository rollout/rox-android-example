package io.rollout.rox.android.example;

import android.util.Log;

import io.rollout.android.configuration.RoxContainer;
import io.rollout.flags.RoxFlag;
import io.rollout.remoteconfiguration.RoxRemoteConfiguration;

import java.lang.reflect.Field;

import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.when;

public class MockitoRox {

  public void mock(RoxContainer container) {
    Field[] fields = container.getClass().getDeclaredFields();
    for(int i = 0; i < fields.length; i++) {
      Field field = fields[i];
      try {
        mockRoxFlag(container, field);
        mockRoxRemoteConfiguration(container, field);
      } catch (IllegalAccessException e) {
        Log.e(MockitoRox.class.getName(), "Failed to mock RoxContainer", e);
      }
    }
  }

  private void mockRoxRemoteConfiguration(RoxContainer container, Field field) throws IllegalAccessException {
    if (field.getType() == RoxRemoteConfiguration.class) {
      field.set(container, Mockito.mock(RoxRemoteConfiguration.class));
    }
  }

  private void mockRoxFlag(RoxContainer container, Field field) throws IllegalAccessException {
    if (field.getType() == RoxFlag.class) {
      field.set(container, Mockito.mock(RoxFlag.class));
    }
  }
}
