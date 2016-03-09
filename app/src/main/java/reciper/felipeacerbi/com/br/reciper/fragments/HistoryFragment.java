package reciper.felipeacerbi.com.br.reciper.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import reciper.felipeacerbi.com.br.reciper.R;

/**
 * Created by Felipe on 2/29/2016.
 */
public class HistoryFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FloatingActionButton fab;

    public HistoryFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HistoryFragment newInstance(int sectionNumber) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        textView.setText("History section: " + getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }
}
