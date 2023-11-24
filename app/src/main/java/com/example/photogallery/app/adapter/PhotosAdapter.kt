import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.photogallery.R
import com.example.photogallery.databinding.ImageviewBinding
import com.example.photogallery.domain.entities.PhotoData

class PhotosAdapter : ListAdapter<PhotoData, PhotosAdapter.ImageViewHolder>(
    diffCallback
) {

    var onItemClicked: ((PhotoData) -> Unit)? = null

    class ImageViewHolder(private val binding: ImageviewBinding) : ViewHolder(binding.root) {

        fun bind(photoData: PhotoData): ViewBinding {
            // remove url to come from outside
            val url = "https://live.staticflickr.com/${photoData.server}/${photoData.id}_${photoData.secret}_b.jpg"
            with(binding) {
                Glide.with(itemView.context)
                    .load(url)
                    .into(imageview)
            }
            return binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.imageview, parent, false)
        return ImageViewHolder(ImageviewBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = getItem(position)
        val binding = holder.bind(currentItem)
        binding.root.setOnClickListener {
            onItemClicked?.invoke(currentItem)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PhotoData>() {
            override fun areItemsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoData, newItem: PhotoData): Boolean {
                return oldItem.server == newItem.server && oldItem.secret == newItem.secret
            }

        }
    }

}