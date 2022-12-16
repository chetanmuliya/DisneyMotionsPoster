package cm.architecture.disneymotions.view.adapter

import android.view.View
import androidx.core.view.ViewCompat
import cm.architecture.disneymotions.model.data.Poster
import cm.velotio.disneymotions.R
import cm.velotio.disneymotions.databinding.ItemPosterBinding
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow

class PosterAdapter: BaseAdapter() {

    init {
        addSection(arrayListOf<Poster>())
    }

    fun addPosterList(posters: List<Poster>){
        sections().first().run {
            clear()
            addAll(posters)
            notifyDataSetChanged()
        }
    }

    override fun layout(sectionRow: SectionRow): Int = R.layout.item_poster

    override fun viewHolder(layout: Int, view: View): BaseViewHolder = PosterViewHolder(view)

}

class PosterViewHolder(view: View) : BaseViewHolder(view) {

    private lateinit var data: Poster
    private val binding: ItemPosterBinding by bindings()

    override fun bindData(data: Any) {
        if (data is Poster) {
            this.data = data
            drawItemUI()
        }
    }

    private fun drawItemUI() {
        binding.apply {
            ViewCompat.setTransitionName(binding.itemContainer, data.name)
            poster = data
            executePendingBindings()
        }
    }

    override fun onClick(p0: View?) = Unit
    // PosterDetailActivity.startActivity(context, binding.itemContainer, data)

    override fun onLongClick(p0: View?) = false
}