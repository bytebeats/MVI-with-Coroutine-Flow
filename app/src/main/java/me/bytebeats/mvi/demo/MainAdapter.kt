package me.bytebeats.mvi.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.bytebeats.mvi.demo.model.User

/**
 * Created by bytebeats on 2021/11/19 : 10:32
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class MainAdapter(private val users: MutableList<User> = mutableListOf()) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    fun updateUsers(users: List<User>?) {
        this.users.clear()
        users?.let {
            this.users.addAll(users)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): User = users[position]

    override fun getItemCount(): Int = users.size

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userName by lazy { view.findViewById<TextView>(R.id.user_name) }
        private val email by lazy { view.findViewById<TextView>(R.id.email) }
        private val avatar by lazy { view.findViewById<ImageView>(R.id.avatar) }

        fun bind(user: User) {
            userName.text = user.name
            email.text = user.email
            Glide.with(avatar).load(user.avatar).into(avatar)
        }
    }
}