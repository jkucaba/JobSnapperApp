package jk.jobsnapper.controllers.EmployerControllers

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import jk.jobsnapper.R
import jk.jobsnapper.databinding.FragmentAddJobOfferBinding
import java.io.IOException
import java.io.InputStreamReader
import java.util.Calendar
import java.util.Properties


class AddJobOfferFragment : Fragment() {
    private lateinit var binding : FragmentAddJobOfferBinding
    private val PLACE_PICKER_REQUEST = 1

    private fun getPlacesApiKey(): String? {
        val properties = Properties()
        try {
            val inputStream = resources.openRawResource(R.raw.api_keys)
            properties.load(InputStreamReader(inputStream))
            println(properties.getProperty("PLACES_API_KEY"))
            return properties.getProperty("PLACES_API_KEY")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddJobOfferBinding.inflate(inflater, container, false)

        val apiKey = getPlacesApiKey()
        if (apiKey != null) {
            println("AddJobOfferFragment" + "Initializing Places with API Key")
            Places.initialize(requireContext(), apiKey)
        } else {
            println("AddJobOfferFragment" + "API Key not found")
        }

        // Initialize a new instance of DatePickerDialog for start date
        val calendar = Calendar.getInstance()
        val startDatePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Display the selected date in start date TextView
                binding.startDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Initialize a new instance of DatePickerDialog for end date
        val endDatePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                // Display the selected date in end date TextView
                binding.endDate.text = "$dayOfMonth/${month + 1}/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        startDatePickerDialog.datePicker.minDate = calendar.timeInMillis
        // Show the DatePickerDialog when the start button is clicked
        binding.startBtn.setOnClickListener {
            activity?.runOnUiThread {
                startDatePickerDialog.show()
            }
        }
        endDatePickerDialog.datePicker.minDate = calendar.timeInMillis
        // Show the DatePickerDialog when the end button is clicked
        binding.endBtn.setOnClickListener {
            activity?.runOnUiThread {
                endDatePickerDialog.show()
            }
        }

        // Set up the place picker on click listener
        binding.locationBtn.setOnClickListener {
            println("AddJobOfferFragment" + "Place picker clicked")
            activity?.runOnUiThread {
                try {
                    val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS)
                    val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(requireContext())
                    startActivityForResult(intent, PLACE_PICKER_REQUEST)
                } catch (e: Exception) {
                    Log.e("AddJobOfferFragment", "Error launching place picker", e)
                }
            }
        }



        val categories = mapOf(
            "Prace fizyczne" to listOf("Prace budowlane", "Prace magazynowe", "Przeprowadzki", "Rozładunek i załadunek", "Prace porządkowe", "Pomoc przy remontach", "Ogrodnictwo i prace związane z zielenią", "Pomoc w gospodarstwach rolnych", "Inna"),
            "Prace biurowe" to listOf("Wprowadzanie danych", "Archiwizacja dokumentów", "Obsługa klienta", "Pomoc w księgowości", "Tłumaczenia","Asystent/ka administracyjna", "Praca zdalna (obsługa korespondencji, planowanie)", "Inna"),
            "Prace sezonowe" to listOf("Zbiory owoców/warzyw", "Praca na jarmarkach", "Sezonowe prace w kurortach", "Pomoc w eventach", "Inna"),
            "Usługi domowe" to listOf("Usługi domowe", "Sprzątanie domów i mieszkań", "Opieka nad dziećmi", "Opieka nad osobami starszymi", "Opieka nad zwierzętami", "Pranie i prasowanie","Pomoc przy zakupach i dostarczaniu produktów", "Inna"),
            "Prace kreatywne i multimedialne" to listOf("Grafika komputerowa", "Tworzenie treści", "Montaż wideo", "Fotografia", "Zarządzanie profilami w social mediach", "Pisanie artykułów", "Projektowanie stron internetowych", "Inna"),
            "Edukacja i korepetycje" to listOf("Korepetycje z przedmiotów szkolnych", "Kursy językowe", "Treningi sportowe i personalne", "Nauka gry na instrumentach", "Pomoc w przygotowaniu do egzaminów", "Inna"),
            "Transport i logistyka" to listOf("Kierowca (np. dostawy, transport osób)", "Kurier", "Przeprowadzki", "Pomoc przy pakowaniu") ,
            "Gastronomia i catering" to listOf("Kelner", "Barman", "Pomoc kuchenna", "Kucharz na eventy", "Roznoszenie ulotek reklamowych, próbek żywności"),
            "Eventy i promocje" to listOf("Hostessy", "Animatorzy czasu wolnego", "Prace związane z organizacją wydarzeń", "Praca przy stoiskach promocyjnych", "Wolontariat podczas eventów"),
            "Techniczne i serwisowe" to listOf("Serwis komputerowy", "Naprawy domowe (np. drobne naprawy hydrauliczne, elektryczne)", "Pomoc techniczna (IT, naprawa sprzętu)", "Montaż mebli"),
            "Inne" to listOf("Inne")

        )

        val adapter = object : BaseExpandableListAdapter() {
            override fun getGroupCount(): Int = categories.size

            override fun getChildrenCount(groupPosition: Int): Int = categories.values.toList()[groupPosition].size

            override fun getGroup(groupPosition: Int): Any = categories.keys.toList()[groupPosition]

            override fun getChild(groupPosition: Int, childPosition: Int): Any = categories.values.toList()[groupPosition][childPosition]

            override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

            override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

            override fun hasStableIds(): Boolean = false

            override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = getGroup(groupPosition) as String
                return view
            }

            override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
                val view = convertView ?: layoutInflater.inflate(android.R.layout.simple_expandable_list_item_2, parent, false)
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.text = getChild(groupPosition, childPosition) as String
                return view
            }

            override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
        }

        binding.expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val selectedCategory = adapter.getGroup(groupPosition) as String
            val selectedSubcategory = adapter.getChild(groupPosition, childPosition) as String
            binding.selectedCategory.text = "$selectedCategory: $selectedSubcategory"
            true
        }

        binding.expandableListView.setOnGroupClickListener { parent, v, groupPosition, id ->
            val selectedCategory = adapter.getGroup(groupPosition) as String
            binding.selectedCategory.text = selectedCategory
            false
        }
        binding.expandableListView.setAdapter(adapter)
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val place = Autocomplete.getPlaceFromIntent(data!!)
                binding.jobLocation.text = "${place.address}"
            }
        }
    }


}