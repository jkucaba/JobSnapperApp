package jk.jobsnapper.controllers.EmployerControllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jk.jobsnapper.adapters.JobOffersListAdapter
import jk.jobsnapper.databinding.FragmentYourJobOffersBinding
import jk.jobsnapper.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class YourJobOffersFragment : Fragment() {
    private lateinit var binding: FragmentYourJobOffersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentYourJobOffersBinding.inflate(inflater, container, false)
        runBlocking {
            withContext(Dispatchers.IO) {
                val jobOffers = Model.getInstanceWC().getYourJobOffersL()
                activity?.runOnUiThread {
                    val adapter = JobOffersListAdapter(jobOffers, requireActivity())
                    binding.recyclerView.adapter = adapter
                }
            }
        }
        return binding.root
    }
}