package cm.architecture.disneymotions.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME to { HomeFragment() },
        LIBRARY to { LibraryFragment() },
        RADIO to { RadioFragment() }
    )

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    override fun getItemCount() = fragmentsCreators.size

    companion object {
        private const val HOME = 0
        private const val LIBRARY = 1
        private const val RADIO = 2
    }
}