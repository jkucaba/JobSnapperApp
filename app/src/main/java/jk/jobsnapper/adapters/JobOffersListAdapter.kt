package jk.jobsnapper.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jk.jobsnapper.databinding.CellJobOfferEmployerBinding
import jk.jobsnapper.models.JobOffer

class JobOffersListAdapter(private val jobOffersList: ArrayList<JobOffer>, private val activity: Activity) : RecyclerView.Adapter<JobOffersListAdapter.JobOffersListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOffersListViewHolder {
        val view = CellJobOfferEmployerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobOffersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobOffersListViewHolder, position: Int) {
        val jobOffers = jobOffersList[position]
        holder.bind(jobOffers)
    }

    override fun getItemCount(): Int {
        return jobOffersList.size
    }

    inner class JobOffersListViewHolder(private val itemBinding: CellJobOfferEmployerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(jobOffer: JobOffer) {
            itemBinding.titleTextView.text = jobOffer.title
            itemBinding.locationTextView.text = jobOffer.location
            itemBinding.startDateTextView.text = jobOffer.startDate
            itemBinding.endDateTextView.text = jobOffer.endDate
            itemBinding.salaryTextView.text = jobOffer.salary
            itemBinding.phoneTextView.text = jobOffer.phoneNumber.toString()
            itemBinding.personIcon1.setColorFilter(com.google.android.libraries.places.R.color.quantum_googgreen)
            itemBinding.personIcon2.setColorFilter(com.google.android.libraries.places.R.color.quantum_googgreen)
            itemBinding.personIcon3.setColorFilter(com.google.android.libraries.places.R.color.quantum_googgreen)

            itemBinding.detailsButton.setOnClickListener {
                showPopup(jobOffer)
            }
        }

        fun showPopup(jobOffer: JobOffer){

        }
    }

}