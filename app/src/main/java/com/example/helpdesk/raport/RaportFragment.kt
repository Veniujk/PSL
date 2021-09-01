package com.example.helpdesk.raport


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.example.helpdesk.BaseFragment
import com.example.helpdesk.R
import com.example.helpdesk.data.Raport
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_raport_zone1.*
import java.util.*

class RaportFragment : BaseFragment() {
    private val cloud = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val DEBUG = "REG_DEBUG"
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_raport_zone1, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.send_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_action -> {
                openReportZone1Click(arguments?.get("raport"))}
            //  requireActivity().finish()
        }

        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rooms = arrayOf(
            "TISO001",
            "TISO002",
            "TISO003",
            "TISO004",
            "TISO005",
            "TISO006",
            "TISO007",
            "TISO008",
            "TISO009",
            "TISO010",
            "TISO011",
            "TISO012",
            "TISO013",
            "TISO014",
            "TISO015",
            "TISO016"
        )

        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, rooms)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        zone1_name_spinner.adapter = arrayAdapter
        val arguementRaport = arguments?.get("raport")

        if (arguementRaport != null) {
            editReportZoneClick(arguementRaport as Raport)
        }

    }

    private fun getIndex(spinner: Spinner, myString: String): Int {
        for (i in 0 until spinner.count) {

            if (spinner.getItemAtPosition(i).toString().equals(myString, ignoreCase = true)) {

                return i
            }
        }
        return 0
    }

    private fun editReportZoneClick(raport: Raport) {
        raport.name?.let { getIndex(zone1_name_spinner, it) }?.let {
        zone1_name_spinner.setSelection(it)
        }
        raport.lr1?.let { zone1_report_lp1.isChecked = it }
        raport.lr2?.let { zone1_report_lp2.isChecked = it }
        raport.lr3?.let { zone1_report_lp3.isChecked = it }
        raport.lr4?.let { zone1_report_lp4.isChecked = it }
        raport.lr5?.let { zone1_report_lp5.isChecked = it }
        raport.lr6?.let { zone1_report_lp6.isChecked = it }
        raport.lr7?.let { zone1_report_lp7.isChecked = it }
        raport.lr8?.let { zone1_report_lp8.isChecked = it }
        raport.lr9?.let { zone1_report_lp9.isChecked = it }
        raport.lr10?.let { zone1_report_lp10.isChecked = it }
        raport.lr11?.let { zone1_report_lp11.isChecked = it }
        raport.lr12?.let { zone1_report_lp12.isChecked = it }
        raport.lr13?.let { zone1_report_lp13.isChecked = it }
        raport.lr14?.let { zone1_report_lp14.isChecked = it }
        raport.lr15?.let { zone1_report_lp15.isChecked = it }
        raport.lr16?.let { zone1_report_lp16.isChecked = it }
        raport.lr17?.let { zone1_report_lp17.isChecked = it }


     /*   raport.lr18?.let { zone1_report_lp18.isChecked = it }
        raport.lr21?.let { zone1_report_lp21.isChecked = it }
        raport.lr22?.let { zone1_report_lp22.isChecked = it }
        raport.lr23?.let { zone1_report_lp23.isChecked = it }*/

        if (raport.uid != auth.currentUser?.uid) {
            zone1_name_spinner.isEnabled = false
            zone1_report_lp1.isEnabled = false
            zone1_report_lp2.isEnabled = false
            zone1_report_lp3.isEnabled = false
            zone1_report_lp4.isEnabled = false
            zone1_report_lp5.isEnabled = false
            zone1_report_lp6.isEnabled = false
            zone1_report_lp7.isEnabled = false
            zone1_report_lp8.isEnabled = false
            zone1_report_lp9.isEnabled = false
            zone1_report_lp10.isEnabled = false
            zone1_report_lp11.isEnabled = false
            zone1_report_lp12.isEnabled = false
            zone1_report_lp13.isEnabled = false
            zone1_report_lp14.isEnabled = false
            zone1_report_lp15.isEnabled = false
            zone1_report_lp16.isEnabled = false
            zone1_report_lp17.isEnabled = false
           /* zone1_report_lp18.isEnabled = false
            zone1_report_lp21.isEnabled = false
            zone1_report_lp22.isEnabled = false
            zone1_report_lp23.isEnabled = false*/
        }
    }

    private fun openReportZone1Click(argumentRaport: Any?) {

        var raport : Raport? = null
        if (argumentRaport != null) {
            raport = (argumentRaport as Raport)
        }
        val data = hashMapOf<Any, Any>(
            "lr1" to zone1_report_lp1.isChecked,
            "lr2" to zone1_report_lp2.isChecked,
            "lr3" to zone1_report_lp3.isChecked,
            "lr4" to zone1_report_lp4.isChecked,
            "lr5" to zone1_report_lp5.isChecked,
            "lr6" to zone1_report_lp6.isChecked,
            "lr7" to zone1_report_lp7.isChecked,
            "lr8" to zone1_report_lp8.isChecked,
            "lr9" to zone1_report_lp9.isChecked,
            "lr10" to zone1_report_lp10.isChecked,
            "lr11" to zone1_report_lp11.isChecked,
            "lr12" to zone1_report_lp12.isChecked,
            "lr13" to zone1_report_lp13.isChecked,
            "lr14" to zone1_report_lp14.isChecked,
            "lr15" to zone1_report_lp15.isChecked,
            "lr16" to zone1_report_lp16.isChecked,
            "lr17" to zone1_report_lp17.isChecked,
           /* "lr18" to zone1_report_lp18.isChecked,
            "lr21" to zone1_report_lp21.isChecked, //okresowo dlatego start od 20
            "lr22" to zone1_report_lp22.isChecked,//okresowo dlatego start od 20
            "lr23" to zone1_report_lp23.isChecked,//okresowo dlatego start od 20*/

        )
        data.put("control", control(data).toString())
        auth.currentUser?.uid?.let { it1 -> data.put("uid", it1) }
        auth.currentUser?.email?.let { it1 -> data.put("uemail", it1) }
        data.put("name", zone1_name_spinner.selectedItem)
        data.put("date", Timestamp(Date()))
        data.put("cid", "Top-Info")
        data.put("uwagi",text_input_uwagi.text.toString())

        if (raport == null) {
            cloud.collection("raports")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(
                        ContentValues.TAG,
                        "DocumentSnapshot written with ID: ${documentReference.id}"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }

            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToHomeFragment().actionId)
            Snackbar.make(requireView(), "Raport został wysłany!", Snackbar.LENGTH_SHORT)
                .show()
        }
        else
        {
            if (raport.uid == auth.currentUser?.uid) {
                raport.documentId?.let {
                    cloud.collection("raports")
                        .document(it)
                        .set(data)
                        .addOnSuccessListener {
                            // Log.d(REPO_DEBUG, "Dodana do ulubionych")
                        }
                        .addOnFailureListener {
                            //  Log.d(REPO_DEBUG, it.message.toString())
                        }
                }
                findNavController()
                    .navigate(RaportFragmentDirections.actionRaportFragmentToHomeFragment().actionId)
                Snackbar.make(requireView(), "Zmiany zostały zapisane!", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                findNavController()
                    .navigate(RaportFragmentDirections.actionRaportFragmentToHomeFragment().actionId)
                Snackbar.make(requireView(), "Brak uprawnień do edycji! Powrócono do podglądu raportów...", Snackbar.LENGTH_SHORT)
                    .show()
            }


        }


    }


}
/*
class RaportFragment : BaseFragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_raport, container, false)
    }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
       openReportZone1()
       openReportZone2()
       openReportZone3()
       openReportZone4()
       openReportZone5()
       openReportZone6()
       openReportZone1Short()
       openReportZone2Short()
       openReportZone3Short()
       openReportZone4Short()
       openReportZone5Short()
       openReportZone6Short()

       }

    private fun openReportZone1() {
        GoTo_fragment_raport_zone1.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz1().actionId)
        }
    }

    private fun openReportZone2() {
        GoTo_fragment_raport_zone2.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz2().actionId)
        }

    }
    private fun openReportZone3() {
        GoTo_fragment_raport_zone3.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz3().actionId)
        }

    }
    private fun openReportZone4() {
        GoTo_fragment_raport_zone4.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz4().actionId)
        }

    }
    private fun openReportZone5() {
        GoTo_fragment_raport_zone5.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz5().actionId)
        }

    }
    private fun openReportZone6() {
        GoTo_fragment_raport_zone6.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportFragmentz6().actionId)
        }

    }
    private fun openReportZone1Short() {
        GoTo_fragment_raport_zone1short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone1ShortFragment().actionId)
        }
    }
    private fun openReportZone2Short() {
        GoTo_fragment_raport_zone2short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone2ShortFragment().actionId)
        }

    }
    private fun openReportZone3Short() {
        GoTo_fragment_raport_zone3short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone3ShortFragment().actionId)
        }

    }
    private fun openReportZone4Short() {
        GoTo_fragment_raport_zone4short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone4ShortFragment().actionId)
        }

    }
    private fun openReportZone5Short() {
        GoTo_fragment_raport_zone5short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone5ShortFragment().actionId)
        }

    }
    private fun openReportZone6Short() {
        GoTo_fragment_raport_zone6short.setOnClickListener {
            findNavController()
                .navigate(RaportFragmentDirections.actionRaportFragmentToRaportZone6ShortFragment().actionId)
        }

    }

        }
*/





