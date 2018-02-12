package com.strival.movie.interceptor;

import com.strival.movie.dao.BuoyDao;
import com.strival.movie.dao.SponsorDao;
import com.strival.movie.po.Buoy;
import com.strival.movie.po.Sponsor;
import com.strival.movie.vo.BuoyVO;
import com.strival.movie.vo.SponsorVO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by xinghai on 2015/12/23.
 */
//@ContextConfiguration("/daos.xml")
public class BuoyAndSponsorInterceptor extends HandlerInterceptorAdapter {
    private BuoyDao buoyDao;
    private SponsorDao sponsorDao;
    private DozerBeanMapper dozerBeanMapper;

    public BuoyDao getBuoyDao() {
        return buoyDao;
    }

    public void setBuoyDao(BuoyDao buoyDao) {
        this.buoyDao = buoyDao;
    }

    public SponsorDao getSponsorDao() {
        return sponsorDao;
    }

    public void setSponsorDao(SponsorDao sponsorDao) {
        this.sponsorDao = sponsorDao;
    }

    public DozerBeanMapper getDozerBeanMapper() {
        return dozerBeanMapper;
    }

    public void setDozerBeanMapper(DozerBeanMapper dozerBeanMapper) {
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        List<Buoy> buoyList=buoyDao.findAll();
        if(buoyList==null||buoyList.size()==0){
            buoyList=new LinkedList<Buoy>();
        }
        List<BuoyVO> buoyVOList=new LinkedList<BuoyVO>();
        List<Sponsor> sponsorList=sponsorDao.findAll();
        if(sponsorList==null||sponsorList.size()==0){
            sponsorList=new LinkedList<Sponsor>();
        }
        List<SponsorVO> sponsorVOList=new LinkedList<SponsorVO>();
        for(Buoy b:buoyList){
            if(b!=null){
                buoyVOList.add(dozerBeanMapper.map(b,BuoyVO.class));
            }
        }
        for(Sponsor s:sponsorList){
            if(s!=null){
                sponsorVOList.add(dozerBeanMapper.map(s,SponsorVO.class));
            }
        }
        modelAndView.addObject("buoyVOList",buoyVOList);
        modelAndView.addObject("sponsorVOList",sponsorVOList);
        super.postHandle(request, response, handler, modelAndView);
    }
}
