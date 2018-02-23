package in.finder.gettingstarted.Views;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.finder.gettingstarted.Adapters.PaginationAdapter;
import in.finder.gettingstarted.Model.Movie;
import in.finder.gettingstarted.Model.MovieResponse;
import in.finder.gettingstarted.Network.MovieDBApiClient;
import in.finder.gettingstarted.Network.MovieDBApiInterface;
import in.finder.gettingstarted.R;
import in.finder.gettingstarted.Utils.PaginationAdapterCallback;
import in.finder.gettingstarted.Utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static in.finder.gettingstarted.Network.MovieDBApiClient.API_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements PaginationAdapterCallback {

    private static final int PAGE_START = 1;

    private boolean isLoading = false;
    private boolean isLastPage = false;
    // limiting to 5 for this tutorial, since total pages in actual API is very large. Feel free to modify.
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;

    @BindView(R.id.movie_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.movie_progress)
    ProgressBar mProgressBar;
    @BindView(R.id.error_layout)
    LinearLayout mErrorLayout;
    @BindView(R.id.error_btn_retry)
    Button mButtonRetry;
    @BindView(R.id.error_txt_cause)
    TextView mTextViewError;

    PaginationAdapter mAdapter;
    LinearLayoutManager mLinearLayoutManager;
    MovieDBApiInterface mMovieDBApiInterface;

    public MovieFragment() {
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
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new PaginationAdapter(getContext(), this);

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();

        return view;
    }

    @Override
    public void retryPageLoad() {
        loadNextPage();
    }

    @OnClick(R.id.error_btn_retry)
    public void retry(){
        loadFirstPage();
    }

    private void loadFirstPage() {

        // To ensure list is visible when retry button in error view is clicked
        hideErrorView();

        callMoviesApi().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                // Got data. Send it to mAdapter

                hideErrorView();

                List<Movie> movies = fetchMovies(response);
                mProgressBar.setVisibility(View.GONE);
                mAdapter.addAll(movies);

                if (currentPage <= TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                showErrorView(t);
            }
        });
    }

    /**
     * @param response extracts List<{@link Movie>} from response
     * @return
     */
    private List<Movie> fetchMovies(Response<MovieResponse> response) {
        MovieResponse movieResponse = response.body();
        return movieResponse.getMovies();
    }

    private void loadNextPage() {

        callMoviesApi().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mAdapter.removeLoadingFooter();
                isLoading = false;

                List<Movie> movies = fetchMovies(response);
                mAdapter.addAll(movies);

                if (currentPage != TOTAL_PAGES) mAdapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                mAdapter.showRetry(true, fetchErrorMessage(t));
            }
        });
    }


    /**
     * Performs a Retrofit call to the movies API.
     * Same API call for Pagination.
     * As {@link #currentPage} will be incremented automatically
     * by @{@link PaginationScrollListener} to load next page.
     */
    private Call<MovieResponse> callMoviesApi() {
        return mMovieDBApiInterface.getMovies(
                API_KEY,
                "en_US",
                currentPage
        );
    }

    /**
     * @param throwable required for {@link #fetchErrorMessage(Throwable)}
     * @return
     */
    private void showErrorView(Throwable throwable) {

        if (mErrorLayout.getVisibility() == View.GONE) {
            mErrorLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);

            mTextViewError.setText(fetchErrorMessage(throwable));
        }
    }

    /**
     * @param throwable to identify the type of error
     * @return appropriate error message
     */
    private String fetchErrorMessage(Throwable throwable) {
        String errorMsg = getResources().getString(R.string.error_msg_unknown);

        if (!isNetworkConnected()) {
            errorMsg = getResources().getString(R.string.error_msg_no_internet);
        } else if (throwable instanceof TimeoutException) {
            errorMsg = getResources().getString(R.string.error_msg_timeout);
        }

        return errorMsg;
    }

    // Helpers -------------------------------------------------------------------------------------


    private void hideErrorView() {
        if (mErrorLayout.getVisibility() == View.VISIBLE) {
            mErrorLayout.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Remember to add android.permission.ACCESS_NETWORK_STATE permission.
     *
     * @return
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

}
