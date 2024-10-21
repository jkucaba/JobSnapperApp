package jk.jobsnapper.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jk.jobsnapper.databinding.CellUserBinding
import jk.jobsnapper.models.User

class UsersListAdapter(private val usersList: ArrayList<User>, private val activity: Activity): RecyclerView.Adapter<UsersListAdapter.UsersListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val view = CellUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val users = usersList[position]
        holder.bind(users)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    inner class UsersListViewHolder(private val itemBinding: CellUserBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(user: User) {
            itemBinding.nameTextView.text = user.firstName
            itemBinding.email.text = user.email

            itemBinding.detailsIcon.setOnClickListener {
                showPopup(user)
            }
        }

        fun showPopup(user: User){

        }
    }
}