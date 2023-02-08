package com.example.gestionmedical.ui.recherche

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestionmedical.adapter.AdapterRecycler
import com.example.gestionmedical.databinding.FragmentRechercheBinding
import com.example.gestionmedical.db.DataUser
import com.example.gestionmedical.model.ViewModelDao

class RechercheFragment : Fragment() {

    private lateinit var binding: FragmentRechercheBinding
    private lateinit var viewModel: ViewModelDao
    private lateinit var adapter: AdapterRecycler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View binding
        val fragmentRechercheBinding =  FragmentRechercheBinding.inflate(inflater, container, false)
        binding = fragmentRechercheBinding

        initRecycler()
        return fragmentRechercheBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding?.fragmentRecherche = this
    }

    fun initRecycler(){
        // Configuration du layout
        binding.recyclerViewRecherche.layoutManager = LinearLayoutManager(context)

        // Configuration de l'adapter
        adapter = AdapterRecycler { daouser: DataUser -> listItemClicked(viewModel, daouser)}
        binding.recyclerViewRecherche.adapter = adapter

        // Affichage des data
        displayUser()

    }
    fun listItemClicked(viewModel: ViewModelDao, daouser: DataUser){
        //viewModel.initUpdateAndDelete(daouser)
    }
    fun displayUser(){

    }
    override fun onDestroyView() {
        super.onDestroyView()
    }
}