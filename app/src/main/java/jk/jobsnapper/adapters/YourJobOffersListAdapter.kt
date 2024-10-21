package jk.jobsnapper.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import jk.jobsnapper.R
import jk.jobsnapper.databinding.CellJobOfferEmployerBinding
import jk.jobsnapper.databinding.CellYourJobOfferBinding
import jk.jobsnapper.models.JobOffer

class YourJobOffersListAdapter(private val jobOffersList: ArrayList<JobOffer>, private val activity: Activity) : RecyclerView.Adapter<YourJobOffersListAdapter.JobOffersListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobOffersListViewHolder {
        val view = CellYourJobOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobOffersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobOffersListViewHolder, position: Int) {
        val jobOffers = jobOffersList[position]
        holder.bind(jobOffers)
    }

    override fun getItemCount(): Int {
        return jobOffersList.size
    }

    inner class JobOffersListViewHolder(private val itemBinding: CellYourJobOfferBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(jobOffer: JobOffer) {
            itemBinding.titleTextView.text = jobOffer.title
            itemBinding.locationTextView.text = jobOffer.location
            itemBinding.startDateTextView.text = jobOffer.startDate
            itemBinding.endDateTextView.text = jobOffer.endDate
            itemBinding.salaryTextView.text = jobOffer.salary
            itemBinding.phoneTextView.text = jobOffer.phoneNumber.toString()

            val ratio = jobOffer.acceptedApplicants.toFloat() / jobOffer.peopleRequired

            val redColor = ContextCompat.getColor(activity, R.color.red)
            val greenColor = ContextCompat.getColor(activity, R.color.green)

            itemBinding.personIcon1.setColorFilter(if (ratio >= 1.0 / 3) redColor else greenColor)
            itemBinding.personIcon2.setColorFilter(if (ratio >= 2.0 / 3) redColor else greenColor)
            itemBinding.personIcon3.setColorFilter(if (ratio.toDouble() == 1.0) redColor else greenColor)

            itemBinding.detailsButton.setOnClickListener {
                showPopup(jobOffer)
            }
        }

        fun showPopup(jobOffer: JobOffer){

        }
    }

}