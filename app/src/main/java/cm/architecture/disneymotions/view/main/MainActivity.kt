package cm.architecture.disneymotions.view.main

import android.os.Bundle
import cm.velotio.disneymotions.R
import cm.velotio.disneymotions.databinding.ActivityMainBinding
import com.skydoves.bindables.BindingActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        //applyExitMaterialTransform()
        super.onCreate(savedInstanceState)
        binding {
            pagerAdapter = MainPagerAdapter(this@MainActivity)
            vm = getViewModel()
        }
    }
}
