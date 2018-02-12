package com.strival.movie.controller.admin;

import com.strival.movie.dao.*;
import com.strival.movie.po.*;
import com.strival.movie.vo.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xinghai on 2015/12/22.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;
    @Autowired
    private AccountTagRecordDao accountTagRecordDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountProfileDao accountProfileDao;
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
    private FormDao formDao;
    @Autowired
    private FormSaveDao formSaveDao;
    @Autowired
    private FormApplyDao formApplyDao;
    @Autowired
    private InvestorDao investorDao;
    @Autowired
    private MapLightDao mapLightDao;
    @Autowired
    private MentorDao mentorDao;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private FormUploadRecordDao formUploadRecordDao;
    @Autowired
    private MovieCategoryDao movieCategoryDao;
    @Autowired
    private AwardDao awardDao;
    @Autowired
    private JudgerDao judgerDao;
    @Autowired
    private VolunteerDao volunteerDao;
    @Autowired
    private MailSubscribeDao mailSubscribeDao;
    @Autowired
    private PeriodDao periodDao;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(
            HttpServletRequest request,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password
    ) {
            Admin admin = adminDao.findByUsername(username);
            if (admin == null) {
                return new ResponseEntity<>("admin not exist", null, HttpStatus.BAD_REQUEST);
            }
            if (!password.equals(admin.getPassword())) {
                return new ResponseEntity<>("username or password error", null, HttpStatus.BAD_REQUEST);
            }
            request.getSession().setAttribute("adminId", (Long)admin.getId());
            return new ResponseEntity<>("success" , null, HttpStatus.OK);
        }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<String> logout(
            HttpServletRequest request
    ) {
            request.getSession().setAttribute("adminId", null);
            return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/password/{admin_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePassword(
            HttpServletRequest request,
            @PathVariable("admin_id")long adminId,
            @RequestBody AdminVO adminVO
    ) {
        adminDao.updatePassword(adminId, adminVO.getPassword());
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public ResponseEntity<String> adminAddTag(
            @RequestParam(value = "name", required = true) String name
    ) {
        Tag tag = new Tag();
        tag.setName(name);
        tagDao.save(tag);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{tag_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminTagDelete(
            @PathVariable("tag_id")long tagId
    ) {
        tagDao.delete(tagId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/{tag_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminTagUpdate(
            @PathVariable("tag_id")long tagId,
           @RequestBody TagVO tagVO
    ) {
        tagDao.updateName(tagId, tagVO.getName());
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/tag/get", method = RequestMethod.GET)
    public Object adminTagList(){
        List<TagVO> tagVOList=new LinkedList<TagVO>();
        List<Tag> tagList=tagDao.findAll();
            for(Tag t:tagList){
                if(t!=null){
                    tagVOList.add(dozerBeanMapper.map(t,TagVO.class));
                }
            }
            return tagVOList;
        }

    @RequestMapping(value = "/tag/account/{user_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminAccountTagEdit(
            @PathVariable("user_id")long userId,
            @RequestBody long[] tagids
    ) {
        Account account=accountDao.findOne(userId);
        List<AccountTagRecord> accountTagRecordList=accountTagRecordDao.findByAccount(account);
        if(accountTagRecordList!=null&&accountTagRecordList.size()!=0){
            for(AccountTagRecord a:accountTagRecordList){
                if(a!=null){
                    accountTagRecordDao.delete(a);
                }
            }
        }
        for(Long i:tagids){
            Tag tag=tagDao.findOne(i);
            AccountTagRecord accountTagRecord=new AccountTagRecord();
            accountTagRecord.setAccount(account);
            accountTagRecord.setTag(tag);
            accountTagRecordDao.save(accountTagRecord);
        }
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

   /* @RequestMapping(value = "/account/get", method = RequestMethod.GET)
    public Object adminAccountList(
            @RequestParam(value = "page",required = false ,defaultValue = "1" )int page,
            @RequestParam(value = "count",required = false ,defaultValue = "10" )int count
    ){

    }*/

    @RequestMapping(value = "/account/get", method = RequestMethod.GET)
    public Object adminAccountTagSearch(
            @RequestParam(value = "tagId",required = false ,defaultValue = "0")long tagId,
            @RequestParam(value = "page",required = false ,defaultValue = "1" )int page,
            @RequestParam(value = "count",required = false ,defaultValue = "10" )int count
    ){
        if(tagId>0){
            List<Tag> tags;
            List<AccountVO> accountVOList;
            List<Account> accountList;
            Tag tag=tagDao.findOne(tagId);
            Page<AccountTagRecord> accountTagRecordPage=accountTagRecordDao.findByTag(tag,new PageRequest(page-1,count));
            if(accountTagRecordPage.getContent().size()==0){
                accountVOList=new LinkedList<AccountVO>();
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("accountVOList",accountVOList);
                map.put("total",accountTagRecordPage.getTotalElements());
                map.put("currentPage",(long)page);
                map.put("hasNextPage",accountTagRecordPage.hasNext());
                map.put("hasPreviousPage",accountTagRecordPage.hasPrevious());

                return map;
            }
            else{
                accountList=new LinkedList<Account>();
                for(AccountTagRecord a:accountTagRecordPage){
                    if(a!=null){
                        accountList.add(a.getAccount());
                    }
                }
            }
            accountVOList=new LinkedList<AccountVO>();
            for(Account a:accountList){
                if(a!=null) {
                    AccountProfile accountProfile=accountProfileDao.findByAccount(a);
                    if(accountProfile==null) {
                        accountProfile=new AccountProfile();
                    }
                    else {   //存在profile，不做处理

                    }
                    List<AccountTagRecord> accountTagRecordList=accountTagRecordDao.findByAccount(a);  //查看用户标签
                    if(accountTagRecordList==null||accountTagRecordList.size()==0){  //该用户不存在标签
                        tags=new LinkedList<Tag>();
                    }
                    else {  //存在标签
                        tags=new LinkedList<Tag>();
                        for(AccountTagRecord atr:accountTagRecordList){
                            if(atr!=null) {
                                tags.add(atr.getTag());
                            }
                        }
                    }
                    AccountVO accountVO=new AccountVO();
                    accountVO.setAccount(a);
                    accountVO.setAccountProfileVO(dozerBeanMapper.map(accountProfile, AccountProfileVO.class));
                    accountVO.setTags(tags);
                    accountVOList.add(accountVO);
                }
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("accountVOList",accountVOList);
            map.put("total",accountTagRecordPage.getTotalElements());
            map.put("currentPage",(long)page);
            map.put("hasNextPage",accountTagRecordPage.hasNext());
            map.put("hasPreviousPage",accountTagRecordPage.hasPrevious());
            return map;
        }
        else{

            List<Tag> tags;
            PageVO<AccountVO> accountVOList;
            Page<Account> accountList=accountDao.findAll(new PageRequest(page - 1, count));
            if(accountList.getContent().size()==0){
                accountVOList=new PageVO<AccountVO>();
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("accountVOList",accountVOList.getRows());
                map.put("total",accountList.getTotalElements());
                map.put("currentPage",(long)page);
                map.put("hasNextPage",accountVOList.getHasNextPage());
                map.put("hasPreviousPage",accountVOList.getHasPreviousPage());
                return map;
            }
            else{  //存在用户
                accountVOList=new PageVO<AccountVO>();
                for(Account a:accountList){
                    if(a!=null) {
                        AccountProfile accountProfile=accountProfileDao.findByAccount(a);
                        if(accountProfile==null) {
                            accountProfile=new AccountProfile();
                        }
                        else {   //存在profile，不做处理

                        }
                        List<AccountTagRecord> accountTagRecordList=accountTagRecordDao.findByAccount(a);  //查看用户标签
                        if(accountTagRecordList==null||accountTagRecordList.size()==0){  //该用户不存在标签
                            tags=new LinkedList<Tag>();
                        }
                        else {  //存在标签
                            tags=new LinkedList<Tag>();
                            for(AccountTagRecord atr:accountTagRecordList){
                                if(atr!=null) {
                                    tags.add(atr.getTag());
                                }
                            }
                        }
                        AccountVO accountVO=new AccountVO();
                        accountVO.setAccount(a);
                        accountVO.setAccountProfileVO(dozerBeanMapper.map(accountProfile, AccountProfileVO.class));
                        accountVO.setTags(tags);
                        accountVOList.getRows().add(accountVO);
                    }
                }
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("accountVOList",accountVOList.getRows());
                map.put("total",accountList.getTotalElements());
                map.put("currentPage",(long)page);
                map.put("hasNextPage",accountList.hasNext());
                map.put("hasPreviousPage",accountList.hasPrevious());
                return map;
            }
        }

    }

    @RequestMapping(value = "/account/keyword/get", method = RequestMethod.GET)
    public Object adminAccountLikeList(
            @RequestParam(value = "keyword",required = true)String keyword,
            @RequestParam(value = "page",required = false ,defaultValue = "1" )int page,
            @RequestParam(value = "count",required = false ,defaultValue = "10" )int count
    ){
        List<Tag> tags;
        PageVO<AccountVO> accountVOList;
        Page<Account> accountList=accountDao.findByKeyword(keyword, new PageRequest(page - 1, count));
        if(accountList.getContent().size()==0){
            accountVOList=new PageVO<AccountVO>();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("accountVOList",accountVOList.getRows());
            map.put("total",accountList.getTotalElements());
            map.put("currentPage",(long)page);
            map.put("hasNextPage",accountList.hasNext());
            map.put("hasPreviousPage",accountList.hasPrevious());
            return map;
        }
        else{  //存在用户
            accountVOList=new PageVO<AccountVO>();
            for(Account a:accountList){
                if(a!=null) {
                    AccountProfile accountProfile=accountProfileDao.findByAccount(a);
                    if(accountProfile==null) {
                        accountProfile=new AccountProfile();
                    }
                    else {   //存在profile，不做处理

                    }
                    List<AccountTagRecord> accountTagRecordList=accountTagRecordDao.findByAccount(a);  //查看用户标签
                    if(accountTagRecordList==null||accountTagRecordList.size()==0){  //该用户不存在标签
                        tags=new LinkedList<Tag>();
                    }
                    else {  //存在标签
                        tags=new LinkedList<Tag>();
                        for(AccountTagRecord atr:accountTagRecordList){
                            if(atr!=null) {
                                tags.add(atr.getTag());
                            }
                        }
                    }
                    AccountVO accountVO=new AccountVO();
                    accountVO.setAccount(a);
                    accountVO.setAccountProfileVO(dozerBeanMapper.map(accountProfile, AccountProfileVO.class));
                    accountVO.setTags(tags);
                    accountVOList.getRows().add(accountVO);
                }
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("accountVOList",accountVOList.getRows());
            map.put("total", accountList.getTotalElements());
            map.put("currentPage", (long) page);
            map.put("hasNextPage", accountList.hasNext());
            map.put("hasPreviousPage", accountList.hasPrevious());
            return map;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> adminAccountAdd(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password",required = true)String password
    ) {
        Account account = accountDao.findByUsername(username);  //判断是否存在此用户
        if (account != null) {   //说明该用户已存在
            return new ResponseEntity<>("username existed", null, HttpStatus.BAD_REQUEST);
        } else {
            account = new Account();
            account.setRegisterTime(new Date());
            account.setUsername(username);
            account.setPassword(password);
            accountDao.save(account);
            return new ResponseEntity<>(" success", null, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/sponsor", method = RequestMethod.POST)
    public ResponseEntity<String> adminSponsorAdd(
            @RequestParam(value = "logoUrl", required = true) String logoUrl,
            @RequestParam(value = "website", required = true) String website
    ){
        Sponsor sponsor=new Sponsor();
        sponsor.setLogoUrl(logoUrl);
        sponsor.setWebsite(website);
        sponsorDao.save(sponsor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/sponsor/{sponsor_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminSponsorUpdate(
            @PathVariable("sponsor_id")long sponsorId ,
            @RequestBody SponsorVO sponsorVO
    ){
        Sponsor sponsor=dozerBeanMapper.map(sponsorVO,Sponsor.class);
        sponsor.setId(sponsorId);
        sponsorDao.save(sponsor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/sponsor/{sponsor_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminSponsorDelete(
            @PathVariable("sponsor_id")long sponsorId
    ){
        sponsorDao.delete(sponsorId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/sponsor/get", method = RequestMethod.GET)
    public Object adminSponsorList(){
        List<Sponsor> sponsorList=sponsorDao.findAll();
        List<SponsorVO> sponsorVOList=new LinkedList<SponsorVO>();
            for(Sponsor s:sponsorList){
                if(s!=null){
                    sponsorVOList.add(dozerBeanMapper.map(s, SponsorVO.class));
                }
            }
            return sponsorVOList;
        }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public ResponseEntity<String> adminArticleAdd(
            @RequestParam(value = "coverUrl", required = true) String coverUrl,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content
    ) {
        Article article = new Article();
        article.setTitle(title);
        article.setCoverUrl(coverUrl);
        article.setContent(content);
        article.setCreateAt(new Date());
        article.setBrowseNum(0);
        articleDao.save(article);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/{article_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminArticleDelete(
            @PathVariable("article_id")long articleId
    ) {
        articleDao.delete(articleId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/{article_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminArticleUpdate(
            @PathVariable("article_id") long articleId,
            @RequestBody ArticleSimpleVO articleSimpleVO
    ) {
        Article article = articleDao.findOne(articleId);
        article.setTitle(articleSimpleVO.getTitle());
        article.setCoverUrl(articleSimpleVO.getCoverUrl());
        article.setContent(articleSimpleVO.getContent());
        articleDao.save(article);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/article/get", method = RequestMethod.GET)
    public Object adminArticleList(
            @RequestParam(value = "page",required = false ,defaultValue = "1" )int page,
            @RequestParam(value = "count",required = false ,defaultValue = "10" )int count
    ) {
        PageVO<ArticleVO> articleVOList;
        Page<Article> articleList = articleDao.findAll(new PageRequest(page - 1, count));
        if (articleList.getContent().size() == 0) {
            articleVOList = new PageVO<ArticleVO>();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("articleVOList",articleVOList.getRows());
            map.put("total",articleList.getTotalElements());
            map.put("currentPage",(long)page);
            map.put("hasNextPage",articleList.hasNext());
            map.put("hasPreviousPage",articleList.hasPrevious());
            return map;
        } else {
            articleVOList = new PageVO<ArticleVO>();
            for (Article a : articleList) {
                if (a != null) {
                    ArticleVO articleVO = dozerBeanMapper.map(a, ArticleVO.class);
                    articleVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(a.getCreateAt()));
                    articleVOList.getRows().add(articleVO);
                }
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("articleVOList",articleVOList.getRows());
            map.put("total",articleList.getTotalElements());
            map.put("currentPage",(long)page);
            map.put("hasNextPage",articleList.hasNext());
            map.put("hasPreviousPage",articleList.hasPrevious());
            return map;
        }
    }

    @RequestMapping(value = "/article/detail/get", method = RequestMethod.POST)
    public Object articleDetail(
            @RequestParam(value = "article_id",required = true) long articleId
    ){
        //articleDao.updateBrowseNum(articleid);
        Article article=articleDao.findOne(articleId);
        ArticleDetailVO articleDetailVO=new ArticleDetailVO();
        articleDetailVO.setDetail(article.getContent());
        articleDetailVO.setTitle(article.getTitle());
        return articleDetailVO;
    }


    @RequestMapping(value = "/buoy", method = RequestMethod.POST)
    public ResponseEntity<String> adminBuoyAdd(
            @RequestParam(value = "logoUrl",required = true)String logoUrl,
            @RequestParam(value= "website",required = true)String website
    ){
        Buoy buoy=new Buoy();
        buoy.setLogoUrl(logoUrl);
        buoy.setWebsite(website);
        buoyDao.save(buoy);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/buoy/{buoy_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminBuoyDelete(
            @PathVariable("buoy_id")long buoyId
    ){
        buoyDao.delete(buoyId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/buoy/{buoy_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminBuoyUpdate(
            @PathVariable("buoy_id")long buoyId,
            @RequestBody BuoyVO buoyVO
    ){
        Buoy buoy=dozerBeanMapper.map(buoyVO,Buoy.class);
        buoy.setId(buoyId);
        buoyDao.save(buoy);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/buoy/get", method = RequestMethod.GET)
    public Object buoyList(){
        List<BuoyVO> buoyVOList=buoyVOList=new LinkedList<BuoyVO>();
        List<Buoy> buoyList=buoyDao.findAll();
            for(Buoy b:buoyList){
                if(b!=null){
                    buoyVOList.add(dozerBeanMapper.map(b,BuoyVO.class));
                }
            }
            return buoyVOList;
        }

    @RequestMapping(value = "/course", method = RequestMethod.POST)
    public ResponseEntity<String> adminCourseAdd(
            @RequestParam(value = "start_time",required = true)String startTime,
            @RequestParam(value= "direction",required = true)String direction,
            @RequestParam(value= "pattern",required = true)String pattern,
            @RequestParam(value= "mentor_name",required = true)String mentorName
    ){
        Course course=new Course();
        course.setDirection(direction);
        course.setMentorName(mentorName);
        course.setPattern(pattern);
        course.setStartTime(startTime);
        courseDao.save(course);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{course_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminCourseDelete(
            @PathVariable("course_id")long courseId
    ){
        courseDao.delete(courseId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/course/{course_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminCourseUpdate(
            @PathVariable("course_id")long courseId,
           @RequestBody CourseVO courseVO
    ){
        Course course=dozerBeanMapper.map(courseVO,Course.class);
        course.setId(courseId);
        courseDao.save(course);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/course/get", method = RequestMethod.GET)
    public Object courseList(){
        List<CourseVO> courseVOList=new LinkedList<CourseVO>();
        List<Course> courseList=courseDao.findAll();
            for(Course c:courseList){
                if(c!=null){
                    courseVOList.add(dozerBeanMapper.map(c,CourseVO.class));
                }
            }
            return courseVOList;
        }

    @RequestMapping(value = "/enter", method = RequestMethod.POST)
    public ResponseEntity<String> adminEnterAdd(
            @RequestParam(value = "name",required = true)String name,
            @RequestParam(value= "author",required = true)String author
    ){
        Enter enter=new Enter();
        enter.setName(name);
        enter.setAuthor(author);
        enterDao.save(enter);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/enter/{enter_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminEnterDelete(
        @PathVariable("enter_id")long enterId
    ){
        enterDao.delete(enterId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/enter/{enter_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminEnterUpdate(
            @PathVariable("enter_id")long enterId,
            @RequestBody EnterVO enterVO
    ){
       Enter enter = dozerBeanMapper.map(enterVO,Enter.class);
        enter.setId(enterId);
        enterDao.save(enter);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/enter/get", method = RequestMethod.GET)
    public Object adminEnterList(){
        List<EnterVO> enterVOList=new LinkedList<EnterVO>();
        List<Enter> enterList=enterDao.findAll();
            for(Enter e:enterList){
                if(e!=null){
                    enterVOList.add(dozerBeanMapper.map(e,EnterVO.class));
                }
            }
            return enterVOList;
        }

    @RequestMapping(value = "/form", method = RequestMethod.POST)  //管理员添加报名表
    public ResponseEntity<String> adminFormAdd(
            @RequestParam(value = "form_name",required = true) String name,
            @RequestParam(value = "control_desc",required = true)String controlDesc
    ){
        Form form=new Form();
        form.setFormName(name);
        form.setCreateAt(new Date());
        form.setControlDesc(controlDesc);
        formDao.save(form);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/form/{form_id}", method = RequestMethod.PUT)  //管理员编辑报名表
    public ResponseEntity<String> adminFormEdit(
            @PathVariable("form_id") long formId,
            @RequestBody FormVO formVO
    ){
        Form form=formDao.findOne(formId);
        form.setControlDesc(formVO.getControlDesc());
        formDao.save(form);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/form/get", method = RequestMethod.GET)
    public Object adminFormList(){
        List<FormSimpleVO> formSimpleVOList=new LinkedList<FormSimpleVO>();
        List<Form> formList=formDao.findAll();
            for(Form f:formList){
                if(f!=null){
                    FormSimpleVO formSimpleVO=new FormSimpleVO();
                    formSimpleVO.setId(f.getId());
                    formSimpleVO.setControlDesc(f.getControlDesc());
                    formSimpleVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(f.getCreateAt()));
                    formSimpleVO.setFormName(f.getFormName());
                    formSimpleVOList.add(formSimpleVO);
                }
            }
            return formSimpleVOList;
        }

    @RequestMapping(value = "/form/apply/get", method = RequestMethod.GET)
    public Object adminFormApplyGet(
            @RequestParam(value = "page",required = false,defaultValue = "1")int page,
            @RequestParam(value = "count",required = false,defaultValue = "10")int count,
       @RequestParam(value ="formName",required = false)String formName
    ){
        if(formName==null||formName.equals("")){  //全局差
            Page<FormApply> formApplyPage=formApplyDao.findAll(new PageRequest(page-1,count));
            PageVO<FormVO> formVOPage=new PageVO<FormVO>();
            for(FormApply f:formApplyPage){
                FormVO formVO=dozerBeanMapper.map(f.getForm(), FormVO.class);
                formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(f.getApplyTime()));
                formVO.setStatus(f.getStatus());
                formVO.setContentDesc(f.getContentDesc());
                formVO.setUsername(f.getAccount().getUsername());
                formVOPage.getRows().add(formVO);
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("formVOList",formVOPage.getRows());
            map.put("total",formApplyPage.getTotalElements());
            return map;
        }
        else{
            Form form=formDao.findByFormName(formName);
            Page<FormApply> formApplyPage=formApplyDao.findByForm(form, new PageRequest(page - 1, count));
            PageVO<FormVO> formVOPage=new PageVO<FormVO>();
            for(FormApply f:formApplyPage){
                FormVO formVO=dozerBeanMapper.map(f.getForm(), FormVO.class);
                formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(f.getForm().getCreateAt()));
                formVO.setStatus(f.getStatus());
                formVO.setContentDesc(f.getContentDesc());
                formVO.setUsername(f.getAccount().getUsername());
                formVOPage.getRows().add(formVO);
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("formVOList",formVOPage.getRows());
            map.put("total",formApplyPage.getTotalElements());
            return map;
        }
    }



    /*@RequestMapping(value = "/my/form/get", method = RequestMethod.GET)  //表单提交
    public Object adminMyFormList(
            @RequestParam(value = "user_id", required = true) long userId
    ) {
        Account account = accountDao.findOne(userId);
        List<FormVO> formVOList = new LinkedList<FormVO>();
        List<FormSave> formSaveList = formSaveDao.findByAccount(account);
        List<FormApply> formApplyList = formApplyDao.findByAccount(account);
            for (FormSave fs : formSaveList) {
                if (fs != null) {
                    FormVO formVO = dozerBeanMapper.map(fs.getForm(), FormVO.class);
                    formVO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(fs.getForm().getCreateAt()));
                    formVO.setContentDesc(fs.getContentDesc());
                    formVO.setStatus(fs.getStatus());
                    formVOList.add(formVO);
                }
            }
            for (FormApply fa : formApplyList) {
                if (fa != null) {
                    FormVO formVO = dozerBeanMapper.map(fa.getForm(), FormVO.class);
                    formVO.set
                    formVO.setContentDesc(fa.getContentDesc());
                    formVO.setStatus(fa.getStatus());
                    formVOList.add(formVO);
                }
            }
        return formVOList;
    }*/

    @RequestMapping(value = "/investor", method = RequestMethod.POST)
    public ResponseEntity<String> adminInvestorAdd(
            @RequestParam(value = "name",required = true)String name,
            @RequestParam(value = "introduction",required = true)String introduction
    ){
        Investor investor=new Investor();
        investor.setName(name);
        investor.setIntroduction(introduction);
        investorDao.save(investor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/investor/{investor_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminInvestorDelete(
            @PathVariable("investor_id")long investorId
    ){
        investorDao.delete(investorId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/investor/{investor_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminInvestorUpdate(
            @PathVariable("investor_id")long investorId,
            @RequestBody InvestorVO investorVO
    ){
       Investor investor=dozerBeanMapper.map(investorVO,Investor.class);
        investor.setId(investorId);
        investorDao.save(investor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/investor/get", method = RequestMethod.GET)
    public Object adminInvestorList(){
        List<InvestorVO> investorVOList=new LinkedList<InvestorVO>();
        List<Investor> investorList=investorDao.findAll();
            for(Investor i:investorList){
                if(i!=null){
                    investorVOList.add(dozerBeanMapper.map(i,InvestorVO.class));
                }
            }
            return investorVOList;
        }

    @RequestMapping(value = "/maplight", method = RequestMethod.POST)
    public ResponseEntity<String> adminMapLightAdd(
            @RequestParam(value = "province",required = true)String province
    ){
        MapLight mapLight=new MapLight();
        mapLight.setProvince(province);
        mapLight.setStatus(false);
        mapLightDao.save(mapLight);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/maplight/{maplight_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminMapLightDelete(
           @PathVariable("maplight_id")long maplightId
    ){
        mapLightDao.delete(maplightId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/maplight/{maplight_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminMapLightUpdate(
            @PathVariable("maplight_id")long maplightId,
            @RequestBody MapLightVO maplightVO
    ){
        MapLight mapLight=dozerBeanMapper.map(maplightVO,MapLight.class);
        mapLight.setId(maplightId);
        mapLightDao.save(mapLight);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/maplight/get", method = RequestMethod.GET)
    public Object adminMapLightList(){
        List<MapLightVO> mapLightVOList=new LinkedList<MapLightVO>();
        List<MapLight> mapLightList=mapLightDao.findAll();
            for(MapLight m:mapLightList){
                if(m!=null){
                    mapLightVOList.add(dozerBeanMapper.map(m,MapLightVO.class));
                }
            }
            return mapLightVOList;
        }

    @RequestMapping(value = "/maplight/light",method = RequestMethod.PUT)
    public ResponseEntity<String> adminMapLightLight(
            @RequestBody long[] ids
    ){
        List<MapLight> mapLightList=mapLightDao.findByStatus(true);
        for(MapLight m:mapLightList){
            if(m!=null){
                mapLightDao.updateStatus(m.getId(),false);
            }
        }
        for(Long i:ids){
            mapLightDao.updateStatus(i,true);
        }
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/mentor", method = RequestMethod.POST)  //管理员添加评审
    public ResponseEntity<String> adminMentorAdd(
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "position",required = true)String position,
            @RequestParam(value = "summary",required = true)String summary,
            @RequestParam(value = "avatar_url",required = true)String avatarUrl
    ){
        Mentor mentor=new Mentor();
        mentor.setName(name);
        mentor.setPosition(position);
        mentor.setSummary(summary);
        mentor.setAvatarUrl(avatarUrl);
        mentorDao.save(mentor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/mentor/{mentor_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminMentorDelete(
            @PathVariable("mentor_id") long mentorId
    ){
        mentorDao.delete(mentorId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/mentor/{mentor_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminMentorUpdate(
           @PathVariable("mentor_id")long mentorId,
            @RequestBody MentorVO mentorVO
    ){
       Mentor mentor=dozerBeanMapper.map(mentorVO,Mentor.class);
        mentor.setId(mentorId);
        mentorDao.save(mentor);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/mentor/get", method = RequestMethod.GET)
    public Object adminMentorList(){
        List<MentorVO> mentorVOList=new LinkedList<MentorVO>();
        List<Mentor> mentorList=mentorDao.findAll();
            for(Mentor m:mentorList){
                if(m!=null){
                    mentorVOList.add(dozerBeanMapper.map(m,MentorVO.class));
                }
            }
            return mentorVOList;
        }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)  //管理员添加电影
    public ResponseEntity<String> adminMovieAdd(
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "kindId",required = true) long categoryid,
            @RequestParam(value = "firstType",required = true)String firstType,
            @RequestParam(value = "attribute",required = true)String attribute,
            @RequestParam(value = "year",required = true)String year,
            @RequestParam(value = "author",required = true)String author,
            @RequestParam(value = "genre",required = true)String genre,
            @RequestParam(value = "group",required = true)String group,
            @RequestParam(value = "description",required = true)String description,
            @RequestParam(value = "state",required = true)String state,
            @RequestParam(value = "imageUrl",required = true)String imageUrl
    ){
        Movie movie=new Movie();
        MovieCategory movieCategory=movieCategoryDao.findOne(categoryid);
        movie.setName(name);
        movie.setAttribute(attribute);
        movie.setAuthor(author);
        movie.setDescription(description);
        movie.setFirstType(firstType);
        movie.setGenre(genre);
        movie.setMovieCategory(movieCategory);
        movie.setState(state);
        movie.setYear(year);
        movie.setGroup(group);
        movie.setImageUrl(imageUrl);
        movieDao.save(movie);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movie_id}", method = RequestMethod.DELETE)  //管理员删除电影
    public ResponseEntity<String> adminMovieDelete(
            @PathVariable("movie_id")long movieId
    ){
        movieDao.delete(movieId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/{movie_id}", method = RequestMethod.PUT)  //管理员电影列表
    public ResponseEntity<String> adminMovieUpdate(
            @PathVariable("movie_id") long movieId,
            @RequestBody MovieVO movieVO
    ){
        Movie movie=dozerBeanMapper.map(movieVO,Movie.class);
        MovieCategory movieCategory= movieCategoryDao.findOne(movieVO.getKindId());
        movie.setMovieCategory(movieCategory);
        movie.setId(movieId);
        movieDao.save(movie);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/movie/get", method = RequestMethod.GET)
    public Object adminMovieList(){
        List<MovieVO> movieVOList=new LinkedList<MovieVO>();
        List<Movie> movieList=movieDao.findAll();
            for(Movie m:movieList){
                if(m!=null){
                    MovieVO movieVO=dozerBeanMapper.map(m,MovieVO.class);
                    movieVO.setKind(m.getMovieCategory().getName());
                    movieVO.setKindId(m.getMovieCategory().getId());
                    movieVOList.add(movieVO);
                }
            }
            return movieVOList;
        }

    @RequestMapping(value = "/movie/category/get",method = RequestMethod.GET)
    public Object adminMovieCategoryList(){
        List<MovieCategoryVO> movieCategoryVOList=new LinkedList<MovieCategoryVO>();
        List<MovieCategory> movieCategoryList=movieCategoryDao.findAll();
            for(MovieCategory m:movieCategoryList){
                if(m!=null){
                    movieCategoryVOList.add(dozerBeanMapper.map(m,MovieCategoryVO.class));
                }
            }
            return movieCategoryVOList;
        }

    @RequestMapping(value = "/review", method = RequestMethod.POST)  //管理员添加评审
    public ResponseEntity<String> adminReviewAdd(
            HttpServletRequest request,
            @RequestParam(value = "name",required = true) String name,
            @RequestParam(value = "position",required = true)String position,
            @RequestParam(value = "summary",required = true)String summary,
            @RequestParam(value = "avatar_url",required = true)String avatarUrl
    ){
        Review review=new Review();
        review.setName(name);
        review.setPosition(position);
        review.setSummary(summary);
        review.setAvatarUrl(avatarUrl);
        reviewDao.save(review);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/review/{review_id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> adminReviewDelete(
           @PathVariable("review_id")long reviewId
    ){
        reviewDao.delete(reviewId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/review/{review_id}", method = RequestMethod.PUT)
    public ResponseEntity<String> adminReviewUpdate(
            @PathVariable("review_id")long reviewId,
           @RequestBody ReviewVO reviewVO
    ){
       Review review=dozerBeanMapper.map(reviewVO,Review.class);
        review.setId(reviewId);
        reviewDao.save(review);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/review/get", method = RequestMethod.GET)
    public Object adminReviewList(){
        List<ReviewVO> reviewVOList=new LinkedList<ReviewVO>();
        List<Review> reviewList=reviewDao.findAll();
            for(Review r:reviewList){
                if(r!=null){
                    reviewVOList.add(dozerBeanMapper.map(r,ReviewVO.class));
                }
            }
            return reviewVOList;
        }

    @RequestMapping(value = "/form/upload/record/get",method = RequestMethod.GET)
    public Object adminFormUploadRecordList(
            @RequestParam(value = "page",required = false,defaultValue = "1")int page,
            @RequestParam(value = "count",required = false,defaultValue = "10")int count
    ){
        PageVO<FormUploadRecordVO> formUploadRecordVOPageVO=new PageVO<FormUploadRecordVO>();
        Page<FormUploadRecord> formUploadRecordPage=formUploadRecordDao.findAll(new PageRequest(page-1,count));
        for(FormUploadRecord f:formUploadRecordPage){
            if(f!=null){
                formUploadRecordVOPageVO.getRows().add(dozerBeanMapper.map(f, FormUploadRecordVO.class));
            }
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("formUploadRecordVOList",formUploadRecordVOPageVO.getRows());
        map.put("total",formUploadRecordPage.getTotalElements());
        return map;
    }

    @RequestMapping(value = "/award",method = RequestMethod.POST)
    public ResponseEntity<String> adminAwardAdd(
            @RequestParam(value = "category",required = true)String category,
            @RequestParam(value = "prize",required = true)String prize,
            @RequestParam(value = "number",required = true)int number,
            @RequestParam(value = "description",required = true)String description,
            @RequestParam(value = "periodNum",required = true)long periodNum
    ){
        Award award=new Award();
        award.setCategory(category);
        award.setDescription(description);
        award.setNumber(number);
        award.setPrize(prize);
        award.setPeriodNum(periodNum);
        awardDao.save(award);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/award/{award_id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> adminAwardDelete(
            @PathVariable("award_id")long awardId
    ){
      awardDao.delete(awardId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/award/{award_id}",method = RequestMethod.PUT)
    public ResponseEntity<String> adminAwardUpdate(
            @PathVariable("award_id")long awardId,
            @RequestBody AwardVO awardVO
    ){
      Award award=dozerBeanMapper.map(awardVO,Award.class);
        award.setId(awardId);
        awardDao.save(award);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/award/get",method = RequestMethod.GET)
    public Object adminAwardList(){
        List<AwardVO> awardVOList=new LinkedList<AwardVO>();
        List<Award> awardList=awardDao.findAll();
        for(Award a:awardList){
            if(a!=null){
                awardVOList.add(dozerBeanMapper.map(a,AwardVO.class));
            }
        }
        return awardVOList;
    }

    @RequestMapping(value = "/award/period",method = RequestMethod.GET)
    public Object adminAwardPeriodList(
            @RequestParam(value = "period_num",required = true)long periodNum
    ){
        List<AwardVO> awardVOList=new LinkedList<AwardVO>();
        List<Award> awardList=awardDao.findByPeriodNum(periodNum);
        for(Award a:awardList){
            if(a!=null){
                awardVOList.add(dozerBeanMapper.map(a,AwardVO.class));
            }
        }
        return awardVOList;
    }

    @RequestMapping(value = "/judger",method = RequestMethod.POST)
    public  ResponseEntity<String> adminJudgerAdd(
            @RequestParam(value = "summary",required = true)String summary,
            @RequestParam(value = "avatar_url",required = true)String avatarUrl,
            @RequestParam(value = "period_num",required = true)long periodNum
    ){
        Judger judger=new Judger();
        judger.setSummary(summary);
        judger.setAvatarUrl(avatarUrl);
        judger.setPeriodNum(periodNum);
        judgerDao.save(judger);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/judger/{judger_id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> adminJudgerDelete(
          @PathVariable("judger_id")long judgerId
    ){
        judgerDao.delete(judgerId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/judger/{judger_id}",method = RequestMethod.PUT)
    public ResponseEntity<String> adminJudgerUpdate(
            @PathVariable("judger_id")long judgerId,
            @RequestBody JudgerVO judgerVO
    ){
        Judger judger=dozerBeanMapper.map(judgerVO,Judger.class);
        judger.setId(judgerId);
        judgerDao.save(judger);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/judger/get",method = RequestMethod.GET)
    public Object adminJudgerList(){
      List<JudgerVO> judgerVOList=new LinkedList<JudgerVO>();
        List<Judger> judgerList=judgerDao.findAll();
        for(Judger j:judgerList){
            if(j!=null){
                judgerVOList.add(dozerBeanMapper.map(j,JudgerVO.class));
            }
        }
        return judgerVOList;
    }

    @RequestMapping(value = "/judger/period",method = RequestMethod.GET)
    public Object adminJudgerPeriodList(
        @RequestParam(value = "period_num",required = true)long periodNum
    ){
        List<JudgerVO> judgerVOList=new LinkedList<JudgerVO>();
        List<Judger> judgerList=judgerDao.findByPeriodNum(periodNum);
        for(Judger j:judgerList){
            if(j!=null){
                judgerVOList.add(dozerBeanMapper.map(j,JudgerVO.class));
            }
        }
        return judgerVOList;
    }

    @RequestMapping(value = "/volunteer",method = RequestMethod.POST)
    public ResponseEntity<String> adminVolunteerAdd(
            @RequestParam(value = "summary",required = true)String summary,
            @RequestParam(value = "avatar_url",required = true)String avatarUrl,
            @RequestParam(value = "period_num",required = true)long periodNum
    ){
      Volunteer volunteer=new Volunteer();
        volunteer.setSummary(summary);
        volunteer.setPeriodNum(periodNum);
        volunteer.setAvatarUrl(avatarUrl);
        volunteerDao.save(volunteer);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/volunteer/{volunteer_id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> adminVolunteerDelete(
          @PathVariable("volunteer_id")long volunteerId
    ){
        volunteerDao.delete(volunteerId);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/volunteer/{volunteer_id}",method = RequestMethod.PUT)
    public ResponseEntity<String> adminVolunteerUpdate(
            @PathVariable("volunteer_id")long volunteerId,
            @RequestBody VolunteerVO volunteerVO
    ){
        Volunteer volunteer=dozerBeanMapper.map(volunteerVO,Volunteer.class);
        volunteer.setId(volunteerId);
        volunteerDao.save(volunteer);
        return new ResponseEntity<>("success", null, HttpStatus.OK);
    }

    @RequestMapping(value = "/volunteer/get",method = RequestMethod.GET)
    public Object adminVolunteerList()
    {
        List<VolunteerVO> volunteerVOList=new LinkedList<VolunteerVO>();
        List<Volunteer> volunteerList=volunteerDao.findAll();
        for(Volunteer v:volunteerList){
            if(v!=null){
                volunteerVOList.add(dozerBeanMapper.map(v,VolunteerVO.class));
            }
        }
        return volunteerVOList;
    }

    @RequestMapping(value = "/volunteer/period",method = RequestMethod.GET)
    public Object adminVolunteerPeriodList(
            @RequestParam(value = "period_num",required = true)long periodNum
    )
    {
        List<VolunteerVO> volunteerVOList=new LinkedList<VolunteerVO>();
        List<Volunteer> volunteerList=volunteerDao.findByPeriodNum(periodNum);
        for(Volunteer v:volunteerList){
            if(v!=null){
                volunteerVOList.add(dozerBeanMapper.map(v,VolunteerVO.class));
            }
        }
        return volunteerVOList;
    }

    @RequestMapping(value = "/period/get",method = RequestMethod.GET)
    public Object adminPeriodList(){
    List<PeriodVO> periodVOList=new LinkedList<PeriodVO>();
        List<Period> periodList=periodDao.findAll();
        for(Period p:periodList){
            if(p!=null){
                periodVOList.add(dozerBeanMapper.map(p,PeriodVO.class));
            }
        }
        return periodVOList;
    }

    @RequestMapping(value = "/mail/subscribe/get",method = RequestMethod.GET)
    public Object adminMailSubscribeGet(
            @RequestParam(value = "page",required = false ,defaultValue = "1" )int page,
            @RequestParam(value = "count",required = false ,defaultValue = "10" )int count
    ){
     PageVO<MailSubscribeVO> mailSubscribeVOPageVO=new PageVO<MailSubscribeVO>();
        Page<MailSubscribe> mailSubscribePage=mailSubscribeDao.findAll(new PageRequest(page-1,count));
        for(MailSubscribe m:mailSubscribePage){
            if(m!=null){
                mailSubscribeVOPageVO.getRows().add(dozerBeanMapper.map(m, MailSubscribeVO.class));
            }
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("mailSubscribeVOList",mailSubscribeVOPageVO.getRows());
        map.put("total",mailSubscribePage.getTotalElements());
        return map;
    }

    @RequestMapping(value = "/mail/subscribe/{mail_subscribe_id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> mailSubscribeDelete(
            @PathVariable("mail_subscribe_id")long mailSubscribeId
    ){
        mailSubscribeDao.delete(mailSubscribeId);
        return new ResponseEntity<String>("success",null,HttpStatus.OK);
    }

    @RequestMapping(value = "/mail/subscribe/email",method = RequestMethod.GET)
    public Object mailSubscribeEmail(
            @RequestParam(value = "email",required = true)String email
    ){
        MailSubscribe mailSubscribe=mailSubscribeDao.findByEmail(email);
        MailSubscribeVO mailSubscribeVO=new MailSubscribeVO();
        if(mailSubscribe==null){

        }
        else{
            mailSubscribeVO=dozerBeanMapper.map(mailSubscribe,MailSubscribeVO.class);
        }
        return mailSubscribeVO;
    }
    
    @PostConstruct
    public void initData(){
        String[] formNames={"影片报名","创投提案报名","创投资方报名","训练营报名","媒体报名","志愿者报名","主动放映报名"};
        for(int i=1;i<=7;i++){
            Form form=formDao.findOne((long)i);
            if(form==null){
                form=new Form();
                form.setCreateAt(new Date());
                form.setControlDesc("");
                form.setFormName(formNames[i - 1]);
                form.setId((long) i);
                formDao.save(form);
            }
            else{
                continue;
            }
        }
        String[] movieCategorys={"特别致敬","短片","纪录片","剧情长片","动画&实验","全景北野武个展","西宁镜像","学院精神","欧陆象限","惊人首作","开幕","闭幕"};
        for(int j=1;j<=12;j++){
            MovieCategory movieCategory=movieCategoryDao.findOne((long)j);
            if(movieCategory==null){
                movieCategory=new MovieCategory();
                movieCategory.setId((long)j);
                movieCategory.setName(movieCategorys[j-1]);
                movieCategoryDao.save(movieCategory);
            }
            else{
                continue;
            }
            }
        List<MapLight> mapLightList=mapLightDao.findByStatus(true);
        String[] provinces={"北京","天津","重庆","上海","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海","台湾","内蒙古","广西","西藏","宁夏","新疆","香港","澳门"};
        for(int k=1;k<=34;k++){
            boolean tag=false;
            for(MapLight m:mapLightList){
                if(m!=null){
                    if(m.getId()==(long)k){
                       tag=true;  //点亮
                    }
                }
            }
            MapLight mapLight=new MapLight();
            mapLight.setId((long) k);
            mapLight.setProvince(provinces[k - 1]);
            if(tag==true){
                mapLight.setStatus(true);
            }
            else{
                mapLight.setStatus(false);
            }
            mapLightDao.save(mapLight);
        }
        long[] periods={1,2,3,4,5,6,7,8,9,10};
        String[] years={"2006年","2007年","2008年","2009年","2010年","2011年","2012年","2013年","2014年","2015年"};
        for(int m=1;m<=10;m++){
            Period period=periodDao.findOne((long) m);
            if(period==null){
                period=new Period();
                period.setPeriodNum(periods[m-1]);
                period.setYear(years[m-1]);
                periodDao.save(period);
            }
            else{
                continue;
            }
        }
        }
    }














