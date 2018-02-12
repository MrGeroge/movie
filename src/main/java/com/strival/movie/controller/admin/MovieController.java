package com.strival.movie.controller.admin;

import com.strival.movie.dao.MovieCategoryDao;
import com.strival.movie.dao.MovieDao;
import com.strival.movie.po.Movie;
import com.strival.movie.po.MovieCategory;
import com.strival.movie.vo.MovieCategoryVO;
import com.strival.movie.vo.MovieNameVO;
import com.strival.movie.vo.MovieVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
@RestController
public class MovieController {
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private MovieCategoryDao movieCategoryDao;

    @RequestMapping(value = "/movie/name/get", method = RequestMethod.GET)
    public Object movieNameSearch(
            @RequestParam(value = "name",required = true)String name
    ) {
        if (name == null || name.equals("")) {
        List<MovieNameVO> movieNameVOs=new LinkedList<MovieNameVO>();
            return movieNameVOs;
        } else {
            List<MovieNameVO> movieNameVOList = new LinkedList<MovieNameVO>();
            List<Movie> movieList = movieDao.findByName(name);
            for (Movie m : movieList) {
                if (m != null) {
                    MovieNameVO movieNameVO = new MovieNameVO();
                    movieNameVO.setName(m.getName());
                    movieNameVOList.add(movieNameVO);
                }
            }
            if (movieNameVOList.size() <= 10) {
                return movieNameVOList;
            } else {
                List<MovieNameVO> movieNameVOs = new LinkedList<MovieNameVO>();
                for (int index = 0; index < 10; index++) {
                    movieNameVOs.add(movieNameVOList.get(index));
                }
                return movieNameVOs;
            }
        }
    }
    }


