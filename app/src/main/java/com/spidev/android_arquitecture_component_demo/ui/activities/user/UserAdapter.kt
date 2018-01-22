package com.spidev.android_arquitecture_component_demo.ui.activities.user

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.spidev.android_arquitecture_component_demo.R
import com.spidev.android_arquitecture_component_demo.repository.local.entity.User
import com.spidev.android_arquitecture_component_demo.util.inflate
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */
class UserAdapter(private val userList: MutableList<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
            holder.bind(userList[position])

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserAdapter.UserViewHolder(parent)

    class UserViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup.inflate(R.layout.item_user)),
            View.OnClickListener {

        private lateinit var user: User

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bind(user: User) {
            this.user = user
            itemView.tvName.text = user.name
        }
    }

    fun addUser(user: User){
        userList.add(user)
        notifyItemInserted(userList.size)
    }

}