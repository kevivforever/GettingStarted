package in.finder.gettingstarted.Views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.finder.gettingstarted.Adapters.MovieAdapter;
import in.finder.gettingstarted.Model.MovieResponse;
import in.finder.gettingstarted.Network.MovieDBApiClient;
import in.finder.gettingstarted.Network.MovieDBApiInterface;
import in.finder.gettingstarted.R;
import in.finder.gettingstarted.Utils.Divider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.finder.gettingstarted.Network.MovieDBApiClient.API_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingMoviesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.nowplaying_movies_rv)
    RecyclerView mRecyclerView;

    @BindView(R.id.nowplaying_movies_srl)
    SwipeRefreshLayout mSwipeRefreshLayout;

    MovieDBApiInterface mMovieDBApiInterface;

    public NowPlayingMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieDBApiInterface = MovieDBApiClient.getMovieDBApiClient().create(MovieDBApiInterface.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing_movies, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new Divider(getActivity(), LinearLayoutManager.VERTICAL));
        getNowPlayingMovies();
        return view;
    }

    private void getNowPlayingMovies() {
        Call<MovieResponse> call = mMovieDBApiInterface.getNowPlayingMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mRecyclerView.setAdapter(
                        new MovieAdapter(response.body().getMovies(), R.layout.list_item_movie, getActivity()));
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        getNowPlayingMovies();
    }

}
