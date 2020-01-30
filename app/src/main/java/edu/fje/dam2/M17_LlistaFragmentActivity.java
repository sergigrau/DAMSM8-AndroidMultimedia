package edu.fje.dam2;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Classe que hereta de fragment
 *
 * @author sergi.grau@fje.edu
 * @version 5.0 27.01.2020
 * 
 */
public class M17_LlistaFragmentActivity extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.m17_fragment_llista,
				container, false);
		return view;
	}

}

