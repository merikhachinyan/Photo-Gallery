package com.example.photogallery.app

import PhotosAdapter
import PhotosViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.photogallery.app.viewmodel.SharedViewModel
import com.example.photogallery.databinding.FragmentPhotosListBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosListFragment: Fragment() {
    private var binding: FragmentPhotosListBinding? = null

    private val viewModel by viewModel<PhotosViewModel>()
    private val sharedViewModel : SharedViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotosListBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        val adapter = PhotosAdapter()

        binding?.photosList?.adapter = adapter

        binding?.run {
            photosList.adapter = adapter
            adapter.onItemClicked = { photoData ->
                val url = "https://live.staticflickr.com/${photoData.server}/${photoData.id}_${photoData.secret}_b.jpg"
                sharedViewModel.showPhoto(url)
            }

            viewModel.fetchPhotos()
            viewModel.photosStateFlow.onEach {
                adapter.submitList(it)
                progressBar.visibility = View.GONE
            }.launchIn(lifecycleScope)

            viewModel.errorStateFlow.filterNotNull().onEach {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }.launchIn(lifecycleScope)
        }
    }

}