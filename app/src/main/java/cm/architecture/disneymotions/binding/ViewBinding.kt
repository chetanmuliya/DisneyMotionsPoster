package cm.architecture.disneymotions.binding

import android.graphics.Color
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cm.architecture.disneymotions.extension.gone
import cm.architecture.disneymotions.extension.visible
import cm.velotio.disneymotions.R
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.skydoves.androidbottombar.AndroidBottomBarView
import com.skydoves.androidbottombar.BottomMenuItem
import java.lang.Math.abs

object ViewBinding {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("pagerAdapter")
    fun bindPagerAdapter(view: ViewPager2, adapter: FragmentStateAdapter) {
        view.adapter = adapter
        view.offscreenPageLimit = 3
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean?) {
        if (shouldBeGone == true) {
            view.gone(true)
        } else {
            view.gone(false)
        }
    }

    @JvmStatic
    @BindingAdapter("bindNavigation")
    fun bindNavigation(view: ViewPager2, navigationView: AndroidBottomBarView) {
        navigationView.addBottomMenuItems(
            mutableListOf(
                BottomMenuItem(view.context)
                    .setTitle("Home")
                    .setIcon(R.drawable.ic_home)
                    .setIconSize(24)
                    .build(),

                BottomMenuItem(view.context)
                    .setTitle("Tv")
                    .setIcon(R.drawable.ic_library)
                    .setIconSize(24)
                    .build(),

                BottomMenuItem(view.context)
                    .setTitle("Radio")
                    .setIcon(R.drawable.ic_baseline_radio_24)
                    .setIconSize(24)
                    .build()
            )
        )

        navigationView.setOnMenuItemSelectedListener { index, _, _ ->
            navigationView.dismissBadge(index)
            view.currentItem = index
        }

        navigationView.setOnBottomMenuInitializedListener {
            navigationView.bindViewPager2(view)
        }
    }

    @JvmStatic
    @BindingAdapter("bindFab")
    fun bindAppBarLayoutWithFab(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
        appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout1: AppBarLayout, verticalOffset: Int ->
                val verticalOffsetPercentage = abs(
                    verticalOffset
                ).toFloat() / appBarLayout1.totalScrollRange.toFloat()
                if (verticalOffsetPercentage > 0.4f && fab.isOrWillBeShown) {
                    fab.hide()
                } else if (verticalOffsetPercentage <= 0.4f && fab.isOrWillBeHidden && fab.tag != View.GONE) {
                    fab.show()
                }
            }
        )
    }

    @JvmStatic
    @BindingAdapter("transformFab", "transformContainer")
    fun bindTransformFab(view: View, fab: FloatingActionButton, container: CoordinatorLayout) {
        fab.setOnClickListener {
            // Begin the transition by changing properties on the start and end views or
            // removing/adding them from the hierarchy.
            fab.tag = View.GONE
            TransitionManager.beginDelayedTransition(container, getTransform(fab, view))
            fab.gone(true)
            view.visible()
        }

        view.setOnClickListener {
            fab.tag = View.VISIBLE
            TransitionManager.beginDelayedTransition(container, getTransform(view, fab))
            fab.visible()
            view.gone(true)
        }
    }

    private fun getTransform(mStartView: View, mEndView: View): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            startView = mStartView
            endView = mEndView
            addTarget(mEndView)
            pathMotion = MaterialArcMotion()
            duration = 550
            scrimColor = Color.TRANSPARENT
        }
    }
}
