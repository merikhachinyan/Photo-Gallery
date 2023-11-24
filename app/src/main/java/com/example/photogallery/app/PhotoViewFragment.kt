package com.example.photogallery.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.photogallery.databinding.FragmentPhotoViewBinding

class PhotoViewFragment: Fragment() {
    private var binding: FragmentPhotoViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoUrl = arguments?.getString(PHOTO_URL)
        binding?.run {
            Glide.with(fullScreenPhoto.context)
                .load(photoUrl)
                .into(fullScreenPhoto)
        }
    }

    companion object {
        fun newInstance(photoUrl: String): PhotoViewFragment {
            val fragment = PhotoViewFragment()
            val args = Bundle()
            args.putString(PHOTO_URL, photoUrl)
            fragment.arguments = args
            return fragment
        }

        private const val PHOTO_URL = "photo_url"
    }
}