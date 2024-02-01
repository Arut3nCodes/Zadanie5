package com.example.zadanie5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Tab1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Tab1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private fun getPrefsAndUpdateWidgets(view: View){
        val data : SharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = data.edit()
        val invitationText = data.getString("mainInvitationText", "invitation")
        val infoText = data.getString("mainInfoText", "info")
        (view.findViewById(R.id.editTextText) as EditText).setText(invitationText)
        (view.findViewById(R.id.editTextText2) as TextView).setText(infoText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_tab1, container, false)
        getPrefsAndUpdateWidgets(view)
        (view.findViewById(R.id.editTextText) as EditText).addTextChangedListener(object :
            TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val data: SharedPreferences =
                    requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val editor = data.edit()
                editor.putString("mainInvitationText", s.toString())
                editor.apply()
            }
        })

        (view.findViewById(R.id.editTextText2) as EditText).addTextChangedListener(object :
            TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val data: SharedPreferences =
                    requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
                val editor = data.edit()
                editor.putString("mainInfoText", s.toString())
                editor.apply()
            }
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Tab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Tab1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}