package com.example.zadanie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var position: Int = 0
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
        return inflater.inflate(R.layout.fragment_display, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DisplayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DisplayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener("msgtochild", viewLifecycleOwner){
                _, bundle ->
            run {

                requireActivity().findViewById<TextView>(R.id.textView5).text = bundle.getString("playerName")
                requireActivity().findViewById<TextView>(R.id.textView7).text = bundle.getString("teamName")
                requireActivity().findViewById<TextView>(R.id.textView8).text = bundle.getInt("wins").toString()
                requireActivity().findViewById<RatingBar>(R.id.ratingBar).rating = bundle.getFloat("grade") ?: 0f
                var position = bundle.getInt("position")
                changeImageOnImageView(requireActivity().findViewById<ImageView>(R.id.imageView), viewModel.getItemOnIndex(position)?.teamName ?: "")
                this.position = position
            }
        }

            requireActivity().findViewById<Button>(R.id.button).setOnClickListener{ parentFragmentManager.popBackStackImmediate() }
            requireActivity().findViewById<Button>(R.id.button6).setOnClickListener {
                findNavController().navigate(R.id.action_displayFragment_to_addFragment)
                this.setFragmentResult(
                    "msgtochild2", bundleOf(
                        "position" to this.position,
                        "typeOfTransaction" to "modifyOperation"
                    )

                )
                println("message sent?!?")
            }

            requireActivity().findViewById<TextView>(R.id.textView5).text = viewModel.getItemOnIndex(this.position!!)!!.playerName
            requireActivity().findViewById<TextView>(R.id.textView7).text = viewModel.getItemOnIndex(this.position!!)!!.teamName
            requireActivity().findViewById<TextView>(R.id.textView8).text = viewModel.getItemOnIndex(this.position!!)!!.wins.toString()
            requireActivity().findViewById<RatingBar>(R.id.ratingBar).rating = viewModel.getItemOnIndex(this.position!!)!!.grade
        }
    fun changeImageOnImageView(imageView: ImageView, team: String){
        when(team){
            "Fnatic" -> imageView.setImageResource(R.drawable.fnatic)
            "G2 Esports" -> imageView.setImageResource(R.drawable.g2)
            "Team Heretics" -> imageView.setImageResource(R.drawable.th)
            else -> imageView.setImageResource(R.drawable.globeicon)
        }
    }

}