package com.example.fragment_prac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragment_prac.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val param = arguments?.getString(ARG_PARAM)
        param?.let {
            binding.tv.text = it
        }

        binding.btnToSecond.setOnClickListener{
            val data = "Hello Frag2!\nFrom Frag1"
            val fragment = SecondFragment.newInstance(data)
            setMainActivityFrameLayout(fragment)
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
        fun newInstance(param: String): FirstFragment {
            val frag = FirstFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PARAM, param)
            frag.arguments = bundle
            return frag
        }
    }
}