package com.example.pager.ui.notes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.ClearCredentialException
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pager.App
import com.example.pager.R
import com.example.pager.data.models.NotesModel
import com.example.pager.databinding.FragmentSecondPagerBinding
import com.example.pager.loadImg
import com.example.pager.ui.notes.adapter.NotesAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class SecondPagerFragment : Fragment() {
    private lateinit var binding: FragmentSecondPagerBinding
    private var auth: FirebaseAuth = Firebase.auth
    private var user = auth.currentUser
    private lateinit var credentialManager: CredentialManager
    private val notesAdapter = NotesAdapter(::onClick) { note ->
        App.db.dao().deleteNote(note)
    }
    private var boolForNotes = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        credentialManager = CredentialManager.create(requireContext())
        super.onViewCreated(view, savedInstanceState)
        val name = user?.displayName
        val emeil = user?.email
        val imUrl = user?.photoUrl

        loadImg(requireContext(), imUrl.toString(), binding.ivMenu)

        initView()
        getData()
        setUpLiester()

        boolForNotes = savedInstanceState?.getBoolean("keyNote") ?: false

        val recyclerView: RecyclerView = binding.rvNotesMain
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.adapter = notesAdapter
        val gridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = if (boolForNotes) gridLayoutManager else linearLayoutManager

        binding.ivMenu.setOnClickListener {
            val imageView = ImageView(requireContext())
            loadImg(requireContext(), imUrl.toString(), imageView)
            imageView.layoutParams = LinearLayout.LayoutParams(200, 200)

            val layout = LinearLayout(requireContext())
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            layout.orientation = LinearLayout.VERTICAL
            layout.addView(imageView)
            builder.setView(layout)
                .setMessage("Вы " + emeil + " хотите покинуть акаунт?")
                .setTitle("выход из " + name)
                .setPositiveButton("Да") { dialog, which ->
                    FirebaseAuth.getInstance().signOut()
                    signOut()
                    finish()
                }
                .setNegativeButton("Нет") { dialog, which ->

                }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        binding.ivChangeType.setOnClickListener {
            binding.rvNotesMain.post {
                if (boolForNotes == true) {
                    boolForNotes = false
                    recyclerView.layoutManager = linearLayoutManager
                } else {
                    boolForNotes = true
                    recyclerView.layoutManager = gridLayoutManager
                }
            }
        }

        binding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etSearch.animate().scaleX(1.05F).scaleY(1.05F).setDuration(100).start()
            } else {
                binding.etSearch.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100).start()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            if (!imeVisible) {
                binding.etSearch.clearFocus()
            }
            insets
        }
    }

    private fun initView() {
        binding.rvNotesMain.adapter = notesAdapter
    }

    private fun onClick(notesModel: NotesModel) {
        val action =
            SecondPagerFragmentDirections.actionSecondPagerFragmentToCreateNotes(notesModel)
        findNavController().navigate(action)
    }

    fun getData() {
        val list: List<NotesModel> = App.db.dao().getAllNotes()
        binding.rvNotesMain.post {
            notesAdapter.getAllNotes(list)
        }
    }

    fun setUpLiester() {
        binding.btnCreate.setOnClickListener {
            val action =
                SecondPagerFragmentDirections.actionSecondPagerFragmentToCreateNotes(task = null)
            findNavController().navigate(action)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("keyNote", boolForNotes)
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun loadimgDialog(imgUrl: String){

    }

    private fun signOut() {
        auth.signOut()

        lifecycleScope.launch {
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager.clearCredentialState(clearRequest)
                updateUI(null)
            } catch (e: ClearCredentialException) {
                Toast.makeText(requireContext(), "что то пошло не так", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {}

    private fun finish() {
        finish()
    }
}