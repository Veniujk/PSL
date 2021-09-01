package com.example.helpdesk.home


import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.example.helpdesk.BaseFragment
import com.example.helpdesk.R
import com.example.helpdesk.data.Raport
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), OnRaportItemLongClick {

    private val auth = FirebaseAuth.getInstance()
    private val homeVm by viewModels<HomeViewModel>()
    private val adapter = RaportAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_action -> {
                auth.signOut()
                requireActivity().finish()
            }
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_home.layoutManager = LinearLayoutManager(requireContext())
        recyclerView_home.adapter = adapter
          }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeVm.raports.observe(viewLifecycleOwner, { list ->
            adapter.setRaports(list.sortedByDescending { it.date })
        })
    }


    override fun onRaportLongClick(raport: Raport, position: Int) {
        val bundle = bundleOf("raport" to raport)
                findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToRaportFragment().actionId,bundle)}

    }

