package com.example.fragment_prac

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_prac.databinding.FragmentSecondBinding
import java.lang.RuntimeException

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private var listener: FragmentDataListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentDataListener) listener = context
        else throw RuntimeException("$context must implement FragmentDataListener")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val param = arguments?.getString(ARG_PARAM)
        param?.let {
            binding.tv.text = param
        }

        binding.btnToActivity.setOnClickListener {
            val data = "Hello From SecondFrag!"
            listener?.onDataReceived(data)
        }
    }

    fun setMainActivityFrameLayout(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .addToBackStack(null)
            .commit()
    }
    companion object {
        private const val ARG_PARAM = "param"

        fun newInstance(param: String): SecondFragment {
            val frag = SecondFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PARAM, param)
            frag.arguments = bundle
            return frag
        }
    }
}