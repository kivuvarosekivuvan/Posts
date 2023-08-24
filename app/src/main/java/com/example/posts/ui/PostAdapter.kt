import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.PostListBinding
import com.example.posts.model.Post

class PostAdapter(private var postList: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    fun updatePosts(newPosts: List<Post>) {
        postList = newPosts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    inner class PostViewHolder(private val binding: PostListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                textViewTitle.text = post.title
                textViewBody.text = post.body
            }
        }
    }
}
