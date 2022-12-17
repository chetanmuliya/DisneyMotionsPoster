package cm.architecture.disneymotions.view.main

import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cm.architecture.disneymotions.extension.gone
import cm.architecture.disneymotions.extension.visible
import cm.architecture.disneymotions.view.adapter.PosterAdapter
import cm.velotio.disneymotions.R
import cm.velotio.disneymotions.databinding.FragmentHomeBinding
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.skydoves.bindables.BindingFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding {
            viewModel = getViewModel<MainViewModel>()
            adapter = PosterAdapter()
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            fab.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    container,
                    getTransform(it, card)
                )
                card.visible()
                it.gone(true)
            }

            card.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    container,
                    getTransform(it, fab)
                )
                fab.visible()
                it.gone(true)
            }
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