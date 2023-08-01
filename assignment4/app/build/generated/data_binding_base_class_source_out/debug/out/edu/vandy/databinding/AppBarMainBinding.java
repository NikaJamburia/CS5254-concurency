// Generated by view binder compiler. Do not edit!
package edu.vandy.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.vandy.R;
import edu.vandy.app.ui.widgets.ProgressFab;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AppBarMainBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final FloatingActionButton actionFab;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final ProgressFab progressFab;

  @NonNull
  public final ToolbarBinding simulationStats;

  @NonNull
  public final SimulationViewBinding simulationView;

  @NonNull
  public final Toolbar toolbar;

  private AppBarMainBinding(@NonNull CoordinatorLayout rootView,
      @NonNull FloatingActionButton actionFab, @NonNull ProgressBar progressBar,
      @NonNull ProgressFab progressFab, @NonNull ToolbarBinding simulationStats,
      @NonNull SimulationViewBinding simulationView, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.actionFab = actionFab;
    this.progressBar = progressBar;
    this.progressFab = progressFab;
    this.simulationStats = simulationStats;
    this.simulationView = simulationView;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AppBarMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AppBarMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.app_bar_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AppBarMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.actionFab;
      FloatingActionButton actionFab = ViewBindings.findChildViewById(rootView, id);
      if (actionFab == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.progressFab;
      ProgressFab progressFab = ViewBindings.findChildViewById(rootView, id);
      if (progressFab == null) {
        break missingId;
      }

      id = R.id.simulationStats;
      View simulationStats = ViewBindings.findChildViewById(rootView, id);
      if (simulationStats == null) {
        break missingId;
      }
      ToolbarBinding binding_simulationStats = ToolbarBinding.bind(simulationStats);

      id = R.id.simulation_view;
      View simulationView = ViewBindings.findChildViewById(rootView, id);
      if (simulationView == null) {
        break missingId;
      }
      SimulationViewBinding binding_simulationView = SimulationViewBinding.bind(simulationView);

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new AppBarMainBinding((CoordinatorLayout) rootView, actionFab, progressBar,
          progressFab, binding_simulationStats, binding_simulationView, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
