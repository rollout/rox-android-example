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

  private MyRoxContainer roxContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.roxContainer = ((App)getApplication()).getRoxContainer();
    setContentView(R.layout.activity_main);
    this.navView = (NavigationView) findViewById(R.id.nav_view);
    navView.setNavigationItemSelectedListener(this);

    Toolbar toolbar = setupToolbar();
    setupDrawer(toolbar);
    setupFab(roxContainer);
  }

  @Override
  protected void onStart() {
    super.onStart();

    // Enable/Disable the FAB with Rox experiment
    // This can be controlled by the FlagsListActivity or the Rox dashboard
    if (roxContainer.enableFab.isEnabled()) {
      fab.setVisibility(View.VISIBLE);
    } else {
      fab.setVisibility(View.GONE);
    }
    setupWelcomeMessageLabel();
  }

  private void setupWelcomeMessageLabel() {
    // Show welcome message that was defined using Rox dashboard
    TextView textView = (TextView)findViewById(R.id.text_view);
    textView.setText(roxContainer.welcomeMessage.getValue());

    int textColor = getResources().getColor(R.color.black);
    if (roxContainer.titleColors.value().equals("Yellow")) {
      textColor = getResources().getColor(R.color.yellow);
    } else if (roxContainer.titleColors.value().equals("Blue")) {
      textColor = getResources().getColor(R.color.blue);
    } else if (roxContainer.titleColors.value().equals("Green")) {
      textColor = getResources().getColor(R.color.green);
    }

    textView.setTextColor(textColor);
  }

  private void setupFab(MyRoxContainer roxContainer) {
    this.fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Fab clicked", Snackbar.LENGTH_LONG).show();
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
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
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
      Rox.unfreeze();
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
