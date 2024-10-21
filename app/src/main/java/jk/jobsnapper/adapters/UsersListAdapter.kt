package jk.jobsnapper.adapters

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import jk.jobsnapper.databinding.CellUserBinding
import jk.jobsnapper.databinding.PopupUserDetailsBinding
import jk.jobsnapper.models.Model
import jk.jobsnapper.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
            val popupBinding: PopupUserDetailsBinding = PopupUserDetailsBinding.inflate(LayoutInflater.from(itemBinding.root.context))
            val popupView: View = popupBinding.root
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            popupWindow.isFocusable = true

            popupView.setOnTouchListener { _, _ -> true }

            popupBinding.fNameEt.setText(user.firstName)
            popupBinding.lNameEt.setText(user.lastName)
            popupBinding.emailEt.text = user.email
            popupBinding.bDayEt.setText(user.birthday)

            popupBinding.closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            popupBinding.deleteButton.setOnClickListener {
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().deleteUserM(user.iduser)
                    }
                }

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    usersList.removeAt(position)
                    notifyItemRemoved(position)

                }
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "User deleted :(", Toast.LENGTH_SHORT).show()
                }
            }
            popupBinding.editButton.setOnClickListener {
                runBlocking {
                    withContext(Dispatchers.IO) {
                    // TODO zrobić edytowanie użytkownika    Model.getInstanceWC().updateUserM(user.iduser, popupBinding.fNameEt.text.toString(), popupBinding.lNameEt.text.toString(), popupBinding.bDayEt.text.toString())
                    }
                }
/*                val position = adapterPosition
                notifyItemChanged(position)
                activity.runOnUiThread {
                    Toast.makeText(activity.applicationContext, "User edited :)", Toast.LENGTH_SHORT).show()
                }*/
            }

            // Show the popup window
            popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)
        }
        }
}
