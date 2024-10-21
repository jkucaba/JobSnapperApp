package jk.jobsnapper.controllers.AdminControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jk.jobsnapper.R
import jk.jobsnapper.adapters.JobOffersListAdapter
import jk.jobsnapper.adapters.UsersListAdapter
import jk.jobsnapper.databinding.FragmentUsersListBinding
import jk.jobsnapper.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UsersListFragment : Fragment() {
    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        runBlocking {
            withContext(Dispatchers.IO) {
                val users = Model.getInstanceWC().getUsersL()
                activity?.runOnUiThread {
                    val adapter = UsersListAdapter(users, requireActivity())
                    binding.recyclerView.adapter = adapter
                }
            }
        }
        return binding.root
    }

}