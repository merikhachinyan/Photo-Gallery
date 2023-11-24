package com.example.photogallery.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.photogallery.app.viewmodel.SharedViewModel
import com.example.photogallery.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {
    private var binding: ActivityMainBinding? = null

    private val viewModel : SharedViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        binding?.run {
            supportFragmentManager.beginTransaction()
                .replace(fragmentContainer.id, PhotosListFragment()).addToBackStack("List").commit()
        }

        viewModel.openPhotoShowFragment.observe(this) {url ->
            binding?.run {
                url?.let { photoUrl ->
                    supportFragmentManager.beginTransaction()
                        .replace(fragmentContainer.id, PhotoViewFragment.newInstance(photoUrl))
                        .addToBackStack("Show")
                        .commit()
                }

            }
        }
    }
}