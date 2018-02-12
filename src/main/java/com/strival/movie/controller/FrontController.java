package com.strival.movie.controller;

import com.strival.movie.dao.*;
import com.strival.movie.po.*;
import com.strival.movie.vo.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author:zhangyu
 * create on 15/12/21.
 */
@Controller
public class FrontController {
    @Autowired
    private SponsorDao sponsorDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private BuoyDao buoyDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private EnterDao enterDao;
    @Autowired
    private InvestorDao investorDao;
    @Autowired
    private MapLightDao mapLightDao;
    @Autowired
    private MentorDao mentorDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MovieCategoryDao movieCategoryDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private FormDao formDao;
    @Autowired
    private AccountProfileDao accountProfileDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private AwardDao awardDao;
    @Autowired
    private PeriodDao periodDao;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private JudgerDao judgerDao;
    @Autowired
    private MailSubscribeDao mailSubscribeDao;
    private String front(String view){
        return "frontend/"+view;
    }

    @RequestMapping(value = "/media_center/news", method = RequestMethod.GET)
    public String articleList(
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "count", required = false, defaultValue = "9") int count
    ) {
        PageVO<ArticleVO> articleVOList;
        Page<Article> articleList = articleDao.findAll(new PageRequest(page - 1, count));
        Map<String, Object> map = new HashMap<String, Object>();
        int pageNum=articleList.getTotalPages();
        int[] total=new int[pageNum];
        for(int i=0;i<pageNum;i++){
            total[i]=i+1;
        }
        if (articleList.getContent().size() == 0) {
            articleVOList = new PageVO<ArticleVO>();
            map.put("articleVOList", articleVOList.getRows());
            map.put("total", total);
            map.put("currentPage",(long)page);
            map.put("hasNextPage",articleList.hasNext());
            map.put("hasPreviousPage",articleList.hasPrevious());
        } else {
            articleVOList = new PageVO<ArticleVO>();
            for (Article a : articleList) {
                if (a != null) {
                    ArticleVO articleVO = dozerBeanMapper.map(a, ArticleVO.class);
                    articleVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(a.getCreateAt()));
                    articleVOList.getRows().add(articleVO);
                }
            }
            map.put("articleVOList", articleVOList.getRows());
            map.put("total", total);
            map.put("currentPage",(long)page);
            map.put("hasNextPage",articleList.hasNext());
            map.put("hasPreviousPage",articleList.hasPrevious());
        }
        model.addAttribute("map", map);
        return front("media_center/news");
    }

    @RequestMapping(value = "/media_center/news_detail", method = RequestMethod.GET)
    public String articleDetail(
            Model model,
            @RequestParam(value = "article_id", required = true) long articleId
    ) {
        articleDao.updateBrowseNum(articleId);
        Article article = articleDao.findOne(articleId);
        ArticleDetailVO articleDetailVO = new ArticleDetailVO();
        articleDetailVO.setDetail(article.getContent());
        articleDetailVO.setTitle(article.getTitle());
        model.addAttribute("articleDetailVO", articleDetailVO);
        return front("media_center/news_detail");//由于新闻详情页面是动态生成的，所以不知道其对应的逻辑视图名
    }

   /* @RequestMapping(value = "/article/category/get", method = RequestMethod.GET)
    public String categoryList(
            Model model
    ) {
        List<ArticleCategoryVO> articleCategoryVOList = new LinkedList<ArticleCategoryVO>();
        List<ArticleCategory> articleCategoryList = articleCategoryDao.findAll();
        for (ArticleCategory c : articleCategoryList) {
            if (c != null) {
                articleCategoryVOList.add(dozerBeanMapper.map(c, ArticleCategoryVO.class));
            }
        }
        model.addAttribute("articleCategoryVOList", articleCategoryVOList);
        return "";  //暂无新闻分类视图
    }*/

    /*@RequestMapping(value = "/xxx", method = RequestMethod.GET)
    public String buoyList(
            Model model
    ) {
        List<BuoyVO> buoyVOList = new LinkedList<BuoyVO>();
        List<Buoy> buoyList = buoyDao.findAll();
        for (Buoy b : buoyList) {
            if (b != null) {
                buoyVOList.add(dozerBeanMapper.map(b, BuoyVO.class));
            }
        }
        model.addAttribute("buoyVOList", buoyVOList);
        return "frontend/xxx";//浮标页面
    }*/

    /*@RequestMapping(value = "/yyy", method = RequestMethod.GET)
    public String sponsorList(
            Model model
    ) {
        List<SponsorVO> sponsorVOList= new LinkedList<SponsorVO>();
        List<Sponsor> sponsorList=sponsorDao.findAll();
        for (Sponsor s : sponsorList) {
            if (s != null) {
               sponsorVOList.add(dozerBeanMapper.map(s, SponsorVO.class));
            }
        }
        model.addAttribute("sponsorVOList", sponsorVOList);
        return "frontend/yyy";//赞助商页面
    }*/


    @RequestMapping(value = "/training/course", method = RequestMethod.GET)
    public String courseList(
            Model model
    ) {
        List<CourseVO> courseVOList = new LinkedList<CourseVO>();
        List<Course> courseList = courseDao.findAll();
        for (Course c : courseList) {
            if (c != null) {
                courseVOList.add(dozerBeanMapper.map(c, CourseVO.class));
            }
        }
        model.addAttribute("courseVOList", courseVOList);
        return front("training/course");
    }

    @RequestMapping(value = "/venture_capital/selected_items", method = RequestMethod.GET)
    public String enterList(
            Model model
    ) {
        List<EnterVO> enterVOList = new LinkedList<EnterVO>();
        List<Enter> enterList = enterDao.findAll();
        for (Enter e : enterList) {
            if (e != null) {
                enterVOList.add(dozerBeanMapper.map(e, EnterVO.class));
            }
        }
        List<List<EnterVO>> enterVOs=new LinkedList<List<EnterVO>>();
        int row=enterVOList.size()/5;
        int rest=enterVOList.size()%5;
        List<EnterVO> enterVOTemp=new LinkedList<EnterVO>();
        for(int i=0;i<row*5;i++){
            if(i%5==0){
                enterVOs.add(enterVOTemp);
                enterVOTemp=new LinkedList<EnterVO>();
            }
            enterVOTemp.add(enterVOList.get(i));
        }
        enterVOTemp=new LinkedList<EnterVO>();
        for(int j=0;j<rest;j++){
            enterVOTemp.add(enterVOList.get(5*row+rest-1));
        }
        enterVOs.add(enterVOTemp);
        model.addAttribute("enterVOs",enterVOs);
        return front("venture_capital/selected_items");
    }

    @RequestMapping(value = "/venture_capital/investment_parties", method = RequestMethod.GET)
    public String investorList(
            Model model
    ) {
        List<InvestorVO> investorVOList = new LinkedList<InvestorVO>();
        List<Investor> investorList = investorDao.findAll();
        for (Investor i : investorList) {
            if (i != null) {
                investorVOList.add(dozerBeanMapper.map(i, InvestorVO.class));
            }
        }
        model.addAttribute("investorVOList", investorVOList);
        return front("venture_capital/investment_parties");
    }

    @RequestMapping(value = "/training/tutor", method = RequestMethod.GET)
    public String mentorList(
            Model model
    ) {
        List<MentorVO> mentorVOList = new LinkedList<MentorVO>();
        List<Mentor> mentorList = mentorDao.findAll();
        for (Mentor m : mentorList) {
            if (m != null) {
                mentorVOList.add(dozerBeanMapper.map(m, MentorVO.class));
            }
        }
        model.addAttribute("mentorVOList",mentorVOList);
        return front("training/tutor");
    }

    @RequestMapping(value = "/keyword/search_result", method = RequestMethod.GET)
    public String movieKeywordSearch(
            Model model,
            @RequestParam(value = "keyword",required = true)String keyword
    ){
        if(keyword==null||keyword.equals("")){
            List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
            model.addAttribute("movieSimpleVOList",movieSimpleVOList);
            return front("search_result");
        }
        else{
            List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
            List<Movie> movieList=movieDao.findByKeyword(keyword);
            for(Movie m:movieList){
                if(m!=null){
                    MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m, MovieSimpleVO.class);
                    movieSimpleVO.setKind(m.getMovieCategory().getName());
                    movieSimpleVO.setKindId(m.getMovieCategory().getId());
                    String[] groupStr=m.getGroup().split(";");
                    List<String> groupList=new LinkedList<String>();
                    for(String s:groupStr){
                        if(s!=null){
                            groupList.add(s);
                        }
                    }
                    List<List<String>> groups=new LinkedList<List<String>>();
                    int row=groupList.size()/2;
                    int rest=groupList.size()%2;
                    List<String> groupTemp=new LinkedList<String>();
                    for(int i=0;i<row*2;i++){
                        if(i%2==0){
                            groups.add(groupTemp);
                            groupTemp=new LinkedList<String>();
                        }
                        groupTemp.add(groupList.get(i));
                    }
                    groupTemp=new LinkedList<String>();
                    for(int j=0;j<rest;j++){
                        groupTemp.add(groupList.get(row*2+rest-1));
                    }
                    groups.add(groupTemp);
                    movieSimpleVO.setGroupList(groups);
                    movieSimpleVOList.add(movieSimpleVO);
                }
            }
            model.addAttribute("movieSimpleVOList",movieSimpleVOList);
            return front("search_result");
        }

        }

    /*@RequestMapping(value = "/movie/category/get",method = RequestMethod.GET)
    public String movieCategoryList(
            Model model
    ){
        List<MovieCategoryVO> movieCategoryVOList=new LinkedList<MovieCategoryVO>();
        List<MovieCategory> movieCategoryList=movieCategoryDao.findAll();
            for(MovieCategory m:movieCategoryList){
                if(m!=null){
                    movieCategoryVOList.add(dozerBeanMapper.map(m,MovieCategoryVO.class));
                }
            }
           model.addAttribute("movieCategoryVOList",movieCategoryVOList);
        return "frontend/showing/*";//电影分类 什么鬼
        }*/

    /*@RequestMapping(value = "/category/search_result",method = RequestMethod.GET)
    public String movieCategorySearch(
            Model model,
            @RequestParam(value = "category_id",required = true)long categoryId
    ) {
        MovieCategory movieCategory = movieCategoryDao.findOne(categoryId);
        List<MovieSimpleVO> movieSimpleVOList= new LinkedList<MovieSimpleVO>();
        List<Movie> movieList = movieDao.findByMovieCategory(movieCategory);
            for (Movie m : movieList) {
                if (m != null) {
                    MovieSimpleVO movieSimpleVO = dozerBeanMapper.map(m, MovieSimpleVO.class);
                    movieSimpleVO.setKind(m.getMovieCategory().getName());
                    movieSimpleVO.setKindId(m.getMovieCategory().getId());
                    String[] attributes=m.getAttribute().split(";");
                    List<String> attributeList=new LinkedList<String>();
                    for(String s:attributes){
                        attributeList.add(s);
                    }
                    movieSimpleVO.setAttributeList(attributeList);
                    movieSimpleVOList.add(movieSimpleVO);
                }
            }
            model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("search_result");  //搜索结果页面
        }*/

//    @RequestMapping(value ="/filmtest/reviewer",method = RequestMethod.GET)
//    public String filmtestReviewer(
//            Model model
//    ){
//     List<ReviewVO> reviewVOList=new LinkedList<>();
//        List<Review> reviewList=reviewDao.findByPosition("评委会主席");
//        for(Review r:reviewList){
//            if(r!=null){
//                reviewVOList.add(dozerBeanMapper.map(r,ReviewVO.class));
//            }
//        }
//        reviewList=reviewDao.findByPosition("评委会成员");
//        for(Review r:reviewList){
//            if(r!=null){
//                reviewVOList.add(dozerBeanMapper.map(r,ReviewVO.class));
//            }
//        }
//        model.addAttribute("reviewVOList",reviewVOList);
//        return front("filmtest/reviewer");
//    }

    @RequestMapping(value = "/filmtest/award",method = RequestMethod.GET)
    public String filmtestAward(
            Model model,
            @RequestParam(value = "period_num",required = false,defaultValue = "10")long periodNum
    ){
     List<AwardSimpleVO> awardVOList=new LinkedList<AwardSimpleVO>();
        List<Award> awardList=awardDao.findByPeriodNum(periodNum);
        for(Award a:awardList){
            if(a!=null){
                AwardSimpleVO awardSimpleVO=dozerBeanMapper.map(a, AwardSimpleVO.class);
                List<String> prizeList=new LinkedList<String>();
                String[] prizes=a.getPrize().split(";");
                for(String s:prizes){
                    if(s!=null){
                        prizeList.add(s);
                    }
                }
                awardSimpleVO.setPrizeList(prizeList);
                awardVOList.add(awardSimpleVO);
            }
        }
        List<PeriodVO> maxPeriod=new LinkedList<PeriodVO>();
        List<Period> periodList=periodDao.findAll();
        for(Period p:periodList){
            maxPeriod.add(dozerBeanMapper.map(p,PeriodVO.class));
        }
        model.addAttribute("awardVOList",awardVOList);
        model.addAttribute("maxPeriod",maxPeriod);
        model.addAttribute("currentPeriod", periodNum);
        return front("filmtest/award");
    }

    @RequestMapping(value = "/filmtest/volunteer_list",method = RequestMethod.GET)
    public String filmtestVolunteer(
            Model model,
            @RequestParam(value = "period_num",required = false,defaultValue = "10")long periodNum
    ){
        List<VolunteerVO> volunteerVOList=new LinkedList<VolunteerVO>();
        List<Volunteer> volunteerList=volunteerDao.findByPeriodNum(periodNum);
        for(Volunteer v:volunteerList){
            if(v!=null){
                volunteerVOList.add(dozerBeanMapper.map(v,VolunteerVO.class));
            }
        }
        List<PeriodVO> maxPeriod=new LinkedList<PeriodVO>();
        List<Period> periodList=periodDao.findAll();
        for(Period p:periodList){
            maxPeriod.add(dozerBeanMapper.map(p,PeriodVO.class));
        }
        model.addAttribute("volunteerVOList",volunteerVOList);
        model.addAttribute("maxPeriod",maxPeriod);
        model.addAttribute("currentPeriod",periodNum);
        return front("filmtest/volunteer_list");
    }

    @RequestMapping(value = "/filmtest/reviewer",method = RequestMethod.GET)
    public String filmtestJudger(
            Model model,
            @RequestParam(value = "period_num",required = false,defaultValue = "10")long periodNum)
    {
        List<JudgerVO> judgerVOList=new LinkedList<JudgerVO>();
        List<Judger> judgerList=judgerDao.findByPeriodNum(periodNum);
        for(Judger j:judgerList){
            if(j!=null){
                judgerVOList.add(dozerBeanMapper.map(j,JudgerVO.class));
            }
        }
        List<PeriodVO> maxPeriod=new LinkedList<PeriodVO>();
        List<Period> periodList=periodDao.findAll();
        for(Period p:periodList){
            maxPeriod.add(dozerBeanMapper.map(p,PeriodVO.class));
        }
        model.addAttribute("judgerVOList",judgerVOList);
        model.addAttribute("maxPeriod",maxPeriod);
        model.addAttribute("currentPeriod",periodNum);
        return front("filmtest/reviewer");
    }



    @RequestMapping(value = "venture_capital/script_review",method=RequestMethod.GET)
    public String ventureReview(
            Model model
    ){
        List<ReviewVO> reviewVOList=new LinkedList<>();
        List<Review> reviewList=reviewDao.findByPosition("剧本复评");
        for(Review r:reviewList){
            if(r!=null){
                reviewVOList.add(dozerBeanMapper.map(r,ReviewVO.class));
            }
        }
        model.addAttribute("reviewVOList",reviewVOList);
        return front("venture_capital/script_review");
    }

    @RequestMapping(value = "venture_capital/script_final",method=RequestMethod.GET)
    public String ventureFinal(
            Model model
    ){
        List<ReviewVO> reviewVOList=new LinkedList<>();
        List<Review> reviewList = reviewDao.findByPosition("剧本评审");
        for(Review r:reviewList){
            if(r!=null){
                reviewVOList.add(dozerBeanMapper.map(r,ReviewVO.class));
            }
        }
        model.addAttribute("reviewVOList",reviewVOList);
        return front("venture_capital/script_final");
    }

    @RequestMapping(value = "/showing/special_tribute",method = RequestMethod.GET)
    public String showingSpecial(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("特别致敬");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/special_tribute");
    }

    @RequestMapping(value = "/showing/competition_unit_short",method = RequestMethod.GET)
    public String showingShort(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("短片");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/competition_unit_short");
    }

    @RequestMapping(value = "/showing/competition_unit_documentary",method = RequestMethod.GET)
    public String showingDocumentary(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("纪录片");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/competition_unit_documentary");
    }

    @RequestMapping(value = "/showing/competition_unit_feature",method = RequestMethod.GET)
    public String showingFeature(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("剧情长片");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/competition_unit_feature");
    }

    @RequestMapping(value = "/showing/competition_unit_animation",method = RequestMethod.GET)
    public String showingAnimation(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("动画&实验");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/competition_unit_animation");
    }

    @RequestMapping(value = "/showing/takeshi_exhibition",method = RequestMethod.GET)
    public String showingTakeshi(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("全景北野武个展");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList", movieSimpleVOList);
        return front("showing/takeshi_exhibition");
    }

    @RequestMapping(value = "/showing/xining_image",method = RequestMethod.GET)
    public String showingImage(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("西宁镜像");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        System.out.print(movieSimpleVOList);
        return front("showing/xining_image");
    }

    @RequestMapping(value = "/showing/college_spirit",method = RequestMethod.GET)
    public String showingCollege(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("学院精神");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList", movieSimpleVOList);
        return front("showing/college_spirit");
    }

    @RequestMapping(value = "/showing/continental_quadrant",method = RequestMethod.GET)
    public String showingContinental(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("欧陆象限");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/continental_quadrant");
    }

    @RequestMapping(value = "/showing/striking_first",method = RequestMethod.GET)
    public String showingStriking(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("惊人首作");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/striking_first");
    }

    @RequestMapping(value = "/showing/open",method = RequestMethod.GET)
    public String showingOpen(
            Model model
    ){
        MovieCategory movieCategory=movieCategoryDao.findByName("开幕");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/open");
    }

    @RequestMapping(value = "/showing/close",method = RequestMethod.GET)
    public String showingClose(
            Model model
    ){
        MovieCategory movieCategory= movieCategoryDao.findByName("闭幕");
        List<MovieSimpleVO> movieSimpleVOList=new LinkedList<MovieSimpleVO>();
        List<Movie> movieList=movieDao.findByMovieCategory(movieCategory);
        for(Movie m:movieList){
            if(m!=null){
                MovieSimpleVO movieSimpleVO=dozerBeanMapper.map(m,MovieSimpleVO.class);
                movieSimpleVO.setKind(movieCategory.getName());
                movieSimpleVO.setKindId(movieCategory.getId());
                String[] groupStr=m.getGroup().split(";");
                List<String> groupList=new LinkedList<String>();
                for(String s:groupStr){
                    if(s!=null){
                        groupList.add(s);
                    }
                }
                List<List<String>> groups=new LinkedList<List<String>>();
                int row=groupList.size()/2;
                int rest=groupList.size()%2;
                List<String> groupTemp=new LinkedList<String>();
                for(int i=0;i<row*2;i++){
                    if(i%2==0){
                        groups.add(groupTemp);
                        groupTemp=new LinkedList<String>();
                    }
                    groupTemp.add(groupList.get(i));
                }
                groupTemp=new LinkedList<String>();
                for(int j=0;j<rest;j++){
                    groupTemp.add(groupList.get(row*2+rest-1
                    ));
                }
                groups.add(groupTemp);
                movieSimpleVO.setGroupList(groups);
                movieSimpleVOList.add(movieSimpleVO);
            }
        }
        model.addAttribute("movieSimpleVOList",movieSimpleVOList);
        return front("showing/close");
    }

    @RequestMapping(value = "/event/online_apply",method = RequestMethod.GET)
    public String eventOnlineApply(
            Model model
    ){
        Form form=formDao.findByFormName("主动放映报名");
        FormVO formVO=dozerBeanMapper.map(form,FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("event/online_apply");
    }

    @RequestMapping(value = "/filmtest/film_apply",method = RequestMethod.GET)
    public String filmApply(
            Model model
    ){
        Form form=formDao.findByFormName("影片报名");
        FormVO formVO=dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("filmtest/film_apply");
    }

    @RequestMapping(value = "/filmtest/volunteer_apply",method = RequestMethod.GET)
    public String volunteerApply(
            Model model
    ){
        Form form=formDao.findByFormName("志愿者报名");
        FormVO formVO=dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("filmtest/volunteer_apply");
    }

    @RequestMapping(value = "/media_center/apply",method = RequestMethod.GET)
    public String mediaApply(
            Model model
    ){
        Form form=formDao.findByFormName("媒体报名");
        FormVO formVO=dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("media_center/apply");
    }

    @RequestMapping(value = "/training/apply",method = RequestMethod.GET)
    public String trainingApply(
            Model model
    ){
        Form form=formDao.findByFormName("训练营报名");
        FormVO formVO=dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("training/apply");
    }

    @RequestMapping(value = "/venture_capital/investors_apply",method = RequestMethod.GET)
    public String investorApply(
            Model model
    ){
        Form form=formDao.findByFormName("创投资方报名");
        FormVO formVO=dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("venture_capital/investors_apply");
    }

    @RequestMapping(value = "/venture_capital/proposal_apply",method = RequestMethod.GET)
    public String proposalApply(
            Model model
    ){
        Form form = formDao.findByFormName("创投提案报名");
        FormVO formVO = dozerBeanMapper.map(form, FormVO.class);
        formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(form.getCreateAt()));
        model.addAttribute("form",formVO);
        return front("venture_capital/proposal_apply");
    }

    @RequestMapping(value = "/user_center/user_info",method = RequestMethod.GET)
    public String user1InfoPost(
            Model model,
            HttpServletRequest request
    ){
        long accountId =(long) request.getSession().getAttribute("accountId");
        Account account=accountDao.findOne(accountId);
        AccountProfile accountProfile=accountProfileDao.findByAccount(account);
        if(accountProfile==null){
            accountProfile=new AccountProfile();
            accountProfile.setAccount(account);
        }
        model.addAttribute("accountProfile",accountProfile);
        return front("user_center/user_info");
    }


    @RequestMapping(value = "/user_center/user_info",method = RequestMethod.POST)
    public String userInfoPost(
            Model model,
            HttpServletRequest request
    ){
		long accountId =(long) request.getSession().getAttribute("accountId");
       Account account=accountDao.findOne(accountId);
        AccountProfile accountProfile=accountProfileDao.findByAccount(account);
        if(accountProfile==null){
            accountProfile=new AccountProfile();
            accountProfile.setAccount(account);
        }
        model.addAttribute("accountProfile",accountProfile);
        return front("user_center/user_info");
    }

    @RequestMapping(value ="/user_center/user_password",method = RequestMethod.GET)
    public String userPassword(
            Model model,
            HttpServletRequest request
    ){
        return front("user_center/user_password");
    }

    @RequestMapping(value ="/user_center/user_password",method = RequestMethod.POST)
    public String userPassword(
            Model model,
            HttpServletRequest request,
           // @RequestParam("account_id")long accountId,
            @RequestParam("password")String password
    ){
        long accountId =(long) request.getSession().getAttribute("accountId");
        if(password==null||password.equals("")){
          //return new ResponseEntity<String>("password can not be null",null, HttpStatus.BAD_REQUEST);
        }
        accountDao.updatePassword(accountId, password);
       return front("user_center/user_password");
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(
            Model model
    ){
        List<ArticleVO> articleVOList=new LinkedList<ArticleVO>();
        List<Article> articleList=articleDao.findAll(new Sort(Sort.Direction.DESC,"createAt"));
        if(articleList==null||articleList.size()==0){

        }
        if(articleList.size()>0&&articleList.size()<=4){
            for(int j=0;j<articleList.size();j++){
                Article article=articleList.get(j);
                ArticleVO articleVO=dozerBeanMapper.map(article,ArticleVO.class);
                articleVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(article.getCreateAt()));
                articleVOList.add(articleVO);
            }
        }
        if(articleList.size()>4){
            for(int i=0;i<4;i++){
                Article article=articleList.get(i);
                ArticleVO articleVO=dozerBeanMapper.map(article,ArticleVO.class);
                articleVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(article.getCreateAt()));
                articleVOList.add(articleVO);
            }
        }
        model.addAttribute("articleVOList", articleVOList);
        return front("index");
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String gen(
            Model model
    ){
        return "redirect:/index";
    }

    @RequestMapping(value = "/subscrible/cancel", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> cancel(
            @RequestParam(value = "id", required = true) long id,
            @RequestParam(value = "token", required = true) String token
    ) {
        MailSubscribe mailSubscribe = mailSubscribeDao.findOne(id);
        if (mailSubscribe != null) {
            if (mailSubscribe.getToken().equals(token)) {
                mailSubscribeDao.delete(id);
                // model.addAttribute("status", "订阅已取消");
                //model.addAttribute("reason", "如果您对电影节有什么建议，欢迎来信 517334815@qq.com");
                return new ResponseEntity<String>("success", null, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("token error", null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>(" mail subscribe not existed", null, HttpStatus.BAD_REQUEST);
        }
    }

    }












