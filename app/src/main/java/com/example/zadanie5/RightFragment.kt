package com.example.zadanie5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RightFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RightFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dataRepo: MyRepository
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

            param2 = it.getString(ARG_PARAM2)
        }
        dataRepo = MyRepository.getinstance(requireContext())
        adapter = MyAdapter(dataRepo.getData()!!)
        parentFragmentManager.setFragmentResultListener("item_added", this) {
            requestKey, _ ->
            adapter.data = dataRepo.getData()!!
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_right, container, false)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RightFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RightFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    inner class MyAdapter(var data: MutableList<DBItem>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        inner class MyViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            val text1: TextView = itemView.findViewById(R.id.rowTextView1)
            val text2: TextView = itemView.findViewById(R.id.rowTextView2)
            val text3: TextView = itemView.findViewById(R.id.textView10)
            val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.single_list_item, parent, false)
            return MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.text1.text = data[position].playerName
            holder.text2.text = data[position].teamName
            changeImageOnImageView(holder.imageView, data[position].teamName)
            val grade = data[position].grade
            if(grade < 3f){
                holder.text3.text = "Bad"
                holder.text3.setTextColor(getResources().getColor(R.color.red))
            }
            else if(grade >= 3f && grade < 4f){
                holder.text3.text = "Average"
                holder.text3.setTextColor(getResources().getColor(R.color.black))
            }
            else if(grade >= 4f && grade < 5f){
                holder.text3.text = "Good"
                holder.text3.setTextColor(getResources().getColor(R.color.green))
            }
            else if(grade == 5f){
                holder.text3.text = "GOAT"
                holder.text3.setTextColor(getResources().getColor(R.color.yellow))
            }
            holder.itemView.setOnClickListener {
             parentFragmentManager.setFragmentResult(             "msgtochild", bundleOf(
                 "playerName" to data[position].playerName,
                 "teamName" to data[position].teamName,
                 "wins" to data[position].wins,
                 "grade" to data[position].grade,
                 "position" to position
                   )
               )
               findNavController().navigate(R.id.action_fragmentRight_to_displayFragment)
            }
            holder.itemView.setOnLongClickListener {
                if (dataRepo.deleteItem(data[position])){
                    data = dataRepo.getData()!!
                    notifyDataSetChanged()
                }
                true
            }


        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recycleView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        requireActivity().findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{ _ ->
            findNavController().navigate(R.id.action_fragmentRight_to_addFragment)
            parentFragmentManager.setFragmentResult("msgtochild", bundleOf())
        }
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