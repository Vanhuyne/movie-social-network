package com.huy.backend.service;

import com.huy.backend.dto.MovieDTO;
import com.huy.backend.models.Genre;
import com.huy.backend.models.Movie;
import com.huy.backend.repository.MovieRepo;
import com.huy.backend.service.impl.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
//
//@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class TestMovieService {

    @MockBean
    private MovieRepo movieRepo;

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    private Movie movie1;
    private Movie movie2;
    private Pageable pageable ;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);
        // Mock Data
        Genre thriller = new Genre(53L, "Thriller");
        Genre action = new Genre(28L, "Action");
        Genre adventure = new Genre(12L, "Adventure");

        Set<Genre> genres = new HashSet<>();
        genres.add(thriller);
        genres.add(action);
        genres.add(adventure);

        movie1 = new Movie(
                6279L,
                "Kraven the Hunter",
                LocalDate.parse("2024-12-11"),
                250,
                "Kraven Kravinoff's complex relationship with his ruthless gangster father, Nikolai, starts him down a path of vengeance with brutal consequences, motivating him to become not only the greatest hunter in the world, but also one of its most feared.",
                "/1GvBhRxY6MELDfxFrete6BNhBB5.jpg",
                "/v9Du2HC3hlknAvGlWhquRbeifwW.jpg",
                539972,
                LocalDateTime.parse("2024-12-25T10:22:53"),
                genres,
                1133.345,
                5.8,
                196,
                "test"
        );
        movie2 = new Movie(
                6279L,
                "Kraven the Hunter",
                LocalDate.parse("2024-12-11"),
                250,
                "Kraven Kravinoff's complex relationship with his ruthless gangster father, Nikolai, starts him down a path of vengeance with brutal consequences, motivating him to become not only the greatest hunter in the world, but also one of its most feared.",
                "/1GvBhRxY6MELDfxFrete6BNhBB5.jpg",
                "/v9Du2HC3hlknAvGlWhquRbeifwW.jpg",
                539972,
                LocalDateTime.parse("2024-12-25T10:22:53"),
                genres,
                1133.345,
                5.8,
                196,
                "test"
        );
        pageable = PageRequest.of(0, 2);
    }

    @Test
    public void testGetAllMovies() {
        // Given
        List<Movie> movies = List.of(movie1, movie2);
        Page<Movie> moviePage = new PageImpl<>(movies, pageable, movies.size());

        // Mock the repository call
        when(movieRepo.findAll(pageable)).thenReturn(moviePage);

        // When
        Page<MovieDTO> result = movieServiceImpl.getAllMovies(pageable);

        // Then
        assertNotNull(result.getContent());
        assertEquals(2, result.getContent().size());

        // Verify repository interaction
//        verify(movieRepo, times(1)).findAll(pageable);
    }

    @Test
    public void getAllMoviesReturnEmptyPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> moviePage = new PageImpl<>(Collections.emptyList());

        when(movieRepo.findAll(pageable)).thenReturn(moviePage);

        Page<MovieDTO> result = movieServiceImpl.getAllMovies(pageable);

        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());
    }
}
