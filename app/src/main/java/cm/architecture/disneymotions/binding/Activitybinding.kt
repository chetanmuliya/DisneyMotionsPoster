package cm.architecture.disneymotions.binding

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import cm.velotio.disneymotions.R
import com.google.android.material.appbar.MaterialToolbar

@BindingAdapter("simpleToolbarWithHome", "simpleToolbarTitle", requireAll = true)
fun bindToolbarWithTitle(toolbar: MaterialToolbar, activity: AppCompatActivity, title: String?) {
    activity.simpleToolbarWithHome(toolbar, title)
}

private fun AppCompatActivity.simpleToolbarWithHome(
    toolbar: MaterialToolbar,
    title_: String? = ""
) {
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        setDisplayHomeAsUpEnabled(true)
        title = title_
    }
}