package com.example.zadanie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    val viewModel : MyViewModel by activityViewModels { MyViewModel.Factory }

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
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener("msgtochild", viewLifecycleOwner) {
                _, bundle ->

            run{
                    println("dziala")
                    requireActivity().findViewById<Button>(R.id.button2).setOnClickListener { _ ->
                        val nick =
                            requireActivity().findViewById<EditText>(R.id.editTextText3).text.toString()
                        val team =
                            requireActivity().findViewById<EditText>(R.id.editTextText4).text.toString()
                        val wins =
                            requireActivity().findViewById<EditText>(R.id.editTextNumber).text.toString()
                                .toInt()
                        val grade = requireActivity().findViewById<RatingBar>(R.id.ratingBar2).rating
                        viewModel.insertItem(DBItem(nick, team, wins, grade))
                        parentFragmentManager.setFragmentResult("item_added", bundleOf())
                        parentFragmentManager.popBackStackImmediate()
                }
            }
        }
        parentFragmentManager.setFragmentResultListener("msgtochild2", viewLifecycleOwner) {
                _, bundle ->
            var position = bundle.getInt("position") ?: 0
            requireActivity().findViewById<EditText>(R.id.editTextText3)
                .setText(viewModel.getItemOnIndex(position)?.playerName ?: "Unknown")
            requireActivity().findViewById<EditText>(R.id.editTextText4)
                .setText(viewModel.getItemOnIndex(position)?.teamName ?: "Unknown")
            requireActivity().findViewById<EditText>(R.id.editTextNumber)
                .setText(viewModel.getItemOnIndex(position)?.wins.toString() ?: "0")
            requireActivity().findViewById<RatingBar>(R.id.ratingBar2).rating =
                viewModel.getItemOnIndex(position)?.grade ?: 0f
            requireActivity().findViewById<Button>(R.id.button2).setOnClickListener { _ ->
                val nick =
                    requireActivity().findViewById<EditText>(R.id.editTextText3).text.toString()
                val team =
                    requireActivity().findViewById<EditText>(R.id.editTextText4).text.toString()
                val wins =
                    requireActivity().findViewById<EditText>(R.id.editTextNumber).text.toString()
                        .toInt()
                val grade = requireActivity().findViewById<RatingBar>(R.id.ratingBar2).rating
                val itemek = viewModel.getItemOnIndex(position)
                itemek?.playerName = nick
                itemek?.teamName = team
                itemek?.grade = grade
                itemek?.wins = wins
                viewModel.updateItem(itemek)
                parentFragmentManager.setFragmentResult("item_added", bundleOf())
                parentFragmentManager.popBackStackImmediate()

            }
        }
        requireActivity().findViewById<Button>(R.id.button4).setOnClickListener{_ ->
            parentFragmentManager.popBackStackImmediate()
        }
    }
}