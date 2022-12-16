package cm.architecture.disneymotions.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cm.architecture.disneymotions.model.data.Poster
import cm.architecture.disneymotions.view.adapter.PosterAdapter
import cm.architecture.disneymotions.view.adapter.PosterAdapterLine
import cm.architecture.disneymotions.view.adapter.PosterCircleAdapter
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
        view.adapter = baseAdapter
    }

    @JvmStatic
    @BindingAdapter("adapterPosterList")
    fun bindAdapterPosterList(view: RecyclerView, posters: List<Poster>?) {
        posters.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<PosterAdapter> { adapter ->
                adapter.addPosterList(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterPosterLineList")
    fun bindAdapterPosterLineList(view: RecyclerView, posters: List<Poster>?) {
        posters.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<PosterAdapterLine> { adapter ->
                adapter.addPosterList(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("adapterPosterCircleList")
    fun bindAdapterPosterCircleList(view: RecyclerView, posters: List<Poster>?) {
        posters.whatIfNotNullOrEmpty { items ->
            view.adapter.whatIfNotNullAs<PosterCircleAdapter> { adapter ->
                adapter.addPosterList(items)
            }
        }
    }
}