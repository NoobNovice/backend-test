package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
		List<Movie> result = new ArrayList<Movie>();
		MoviesResponse movieList = movieDataService.fetchAll();
		for (int i=0;i<movieList.size();i++)
		{
			MovieData movie = movieList.get(i);
			Pattern pattern = Pattern.compile("\\b"+queryText.toLowerCase()+"\\b");
			if (pattern.matcher(movie.getTitle().toLowerCase()).find())
			{
				Movie tmp = new Movie(movie.getTitle());
				tmp.getActors().addAll(movie.getCast());
				result.add(tmp);
			}
		}
		return result;
	}
}
