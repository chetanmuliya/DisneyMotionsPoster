package cm.architecture.disneymotions.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.skydoves.baserecyclerviewadapter.BaseViewHolder

inline fun <reified T : ViewBinding> BaseViewHolder.bindings(): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE) {
        requireNotNull(DataBindingUtil.bind(itemView)){ "cannot find the matched view to layout." }
    }