package com.strival.movie.controller.admin;

import com.strival.movie.dao.MapLightDao;
import com.strival.movie.po.MapLight;
import com.strival.movie.vo.MapLightVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/19.
 */
@RestController
public class MapLightController {
    @Autowired
    private MapLightDao mapLightDao;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @RequestMapping(value = "/maplight/get", method = RequestMethod.GET)
    public Object mapLightList(
            Model model
    ) {
        List<MapLightVO> mapLightVOList = new LinkedList<MapLightVO>();
        List<MapLight> mapLightList = mapLightDao.findByStatus(true);
        for (MapLight m : mapLightList) {
            if (m != null) {
                mapLightVOList.add(dozerBeanMapper.map(m, MapLightVO.class));
            }
        }
        return mapLightVOList;
    }

}