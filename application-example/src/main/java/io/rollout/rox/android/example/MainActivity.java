package io.rollout.rox.android.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.rollout.android.Rox;
import io.rollout.android.activities.FlagsListActivity;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private NavigationView navView;

  private FloatingActionButton fab;

  private MenuItem toolbarEditButton;

  private MyRoxContainer roxContainer;

  private TextView textView;

  private boolean launched = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.roxContainer = ((App) getApplication()).getRoxContainer();
    this.setContentView(R.layout.activity_main);
    this.fab = (FloatingActionButton) findViewById(R.id.fab);
    this.navView = (NavigationView) findViewById(R.id.nav_view);
    this.navView.setNavigationItemSelectedListener(this);

    Toolbar toolbar = setupToolbar();
    setupDrawer(toolbar);
  }

  private void updateUI() {
    this.textView = (TextView) findViewById(R.id.text_view);
    setupFlagsActivity();
    setupWelcomeMessageText(textView);
    setupWelcomeMessageColor(textView);

    if (toolbarEditButton != null) {
      setCameraButton();
    }
  }

  private void setupFlagsActivity() {
    if (roxContainer.enableFlagsActivity.isEnabled()) {
      navView.getMenu().findItem(R.id.nav_flags).setVisible(true);
    } else {
      navView.getMenu().findItem(R.id.nav_flags).setVisible(false);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    updateUI();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (launched) {
      Intent intent = new Intent(this, WelcomeActivity.class);
      startActivity(intent);
    }
    this.launched = true;
  }

  /**
   * Enable/Disable the FAB with Rox experiment
   */
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

  private void setupWelcomeMessageColor(TextView textView) {

    int textColor = getResources().getColor(R.color.white);

    switch (roxContainer.titleColors.getValue()) {
      case BLUE:
        textColor = getResources().getColor(R.color.blue);
        break;
      case YELLOW:
        textColor = getResources().getColor(R.color.yellow);
        break;
    }

    textView.setTextColor(textColor);
  }

  private void setupWelcomeMessageText(TextView textView) {
    textView.setText(roxContainer.welcomeMessage.getValue());
  }

  private void setupEditButtonBehaviour(MyRoxContainer roxContainer) {
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Fab clicked", Snackbar.LENGTH_LONG).show();
      }
    });

    this.toolbarEditButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        Snackbar.make(fab, "Toolbar clicked", Snackbar.LENGTH_LONG).show();
        return false;
      }
    });
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    this.toolbarEditButton = menu.findItem(R.id.toolbar_camera_button);
    setCameraButton();
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.toolbar_camera_button) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {

    int id = item.getItemId();

    if (id == R.id.nav_flags) {
      showRoxFlagsActivity();

      // unfreeze the experiment so we can see the changes immediately
//      Rox.unfreeze();
    } else if (id == R.id.nav_docs) {
      showRoxDocs();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /*
   * Show the Rox FlagsListActivity
   */
  private void showRoxFlagsActivity() {
    Intent intent = new Intent(this, FlagsListActivity.class);
    startActivity(intent);
  }

  private void showRoxDocs() {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("http://support.rollout.io"));
    startActivity(intent);
  }

  private Toolbar setupToolbar() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    return toolbar;
  }

  private void setupDrawer(Toolbar toolbar) {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();
  }
}
