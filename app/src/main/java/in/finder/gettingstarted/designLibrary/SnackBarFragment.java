package in.finder.gettingstarted.designlibrary;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.finder.gettingstarted.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnackBarFragment extends Fragment {


    public SnackBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_snack_bar, container, false);
        FloatingActionButton button = (FloatingActionButton)rootView.findViewById(R.id.fab1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "This is a snackbar", Snackbar.LENGTH_LONG).show();
            }
        });
        return rootView;
    }

}
