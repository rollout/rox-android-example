package io.rollout.rox.android.example;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import java.util.ArrayList;

import io.rollout.rox.android.example.MainActivity;
import io.rollout.rox.android.example.R;
import za.co.riggaroo.materialhelptutorial.TutorialItem;
import za.co.riggaroo.materialhelptutorial.tutorial.MaterialTutorialActivity;

public class WelcomeActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);
    loadTutorial();
  }

  public void loadTutorial() {
    Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
    mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
    startActivityForResult(mainAct, 100);
  }

  private ArrayList<TutorialItem> getTutorialItems(Context context) {
    TutorialItem tutorialItem1 = new TutorialItem(R.string.welcome_main_1, R.string.welcome_sub_1,
        R.color.slide_1, R.drawable.main);

    TutorialItem tutorialItem2 = new TutorialItem(R.string.welcome_main_2, R.string.welcome_sub_2,
        R.color.slide_2, R.drawable.settings);

    ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
    tutorialItems.add(tutorialItem1);
    tutorialItems.add(tutorialItem2);

    return tutorialItems;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(intent);
  }
}
