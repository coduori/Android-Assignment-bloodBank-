package com.example.bloodbank.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.HomeActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.adapters.HospitalListViewAdapter;
import com.example.bloodbank.models.Hospital;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HospitalFragment} interface
 * to handle interaction events.
 * Use the {@link HospitalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HospitalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    HospitalListViewAdapter adapter;
    private List<Hospital> hospitalList;

    // private OnFragmentInteractionListener mListener;

    public HospitalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HospitalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HospitalFragment newInstance(String param1, String param2) {
        HospitalFragment fragment = new HospitalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View clientDashboardFragment = inflater.inflate(R.layout.fragment_hospital, container, false); super.onCreateView(inflater, container, savedInstanceState);
        /*RecyclerView recyclerView = clientDashboardFragment.findViewById(R.id.rvUsers);
        hospitalList = new ArrayList<>();
        adapter = new HospitalListViewAdapter(getActivity().getApplicationContext(),hospitalList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment */
        return clientDashboardFragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    // public void onButtonPressed(Uri uri) {
     //    if (mListener != null) {
    //         mListener.onFragmentInteraction(uri);
    //     }
    // }

    // @Override
    // public void onAttach(Context context) {
    //     super.onAttach(context);
    //     if (context instanceof OnFragmentInteractionListener) {
    //           mListener = (OnFragmentInteractionListener) context;
    //     } else {
    //         throw new RuntimeException(context.toString()
    //                 + " must implement OnFragmentInteractionListener");
    //     }
    // }

    // @Override
    // public void onDetach() {
    // super.onDetach();
        // mListener = null;
    // }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    // public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        // void onFragmentInteraction(Uri uri);
    //}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rvUsers);
        hospitalList = new ArrayList<>();
        adapter = new HospitalListViewAdapter(getActivity().getApplicationContext(),hospitalList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}
