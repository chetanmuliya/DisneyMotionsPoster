package cm.architecture.disneymotions.view.main

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import cm.architecture.disneymotions.model.data.Poster
import cm.architecture.disneymotions.repository.MainRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import timber.log.Timber

class MainViewModel constructor(
    mainRepository: MainRepository
) : BindingViewModel(){

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    private val posterListFlow = mainRepository.loadDisneyPosters(
        onSuccess = { isLoading = false }
    )

    @get:Bindable
    val posterList: List<Poster> by posterListFlow.asBindingProperty(viewModelScope, emptyList())

    init {
        Timber.d("injection MainViewModel")
    }
}