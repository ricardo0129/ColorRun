package com.example.colorrun.ui.main;

import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.colorrun.R;

public class Game extends Fragment {
    private MainViewModel mViewModel;
    private GameInstance runningGame;
    public Button restart;
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);
        restart = view.findViewById(R.id.restartButton);
        restart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                runningGame.resetGame();
            }
        });

        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        runningGame = view.findViewById(R.id.gameWindow);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    }

    @Override
    public void onPause(){
        super.onPause();
        runningGame.pause();
    }
    @Override
    public void onResume(){
        super.onResume();
        runningGame.resume();
    }

}
