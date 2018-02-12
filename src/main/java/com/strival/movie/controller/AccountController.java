package com.strival.movie.controller;

import com.strival.movie.config.WebConstants;
import com.strival.movie.dao.*;
import com.strival.movie.po.*;
import com.strival.movie.vo.*;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author:zhangyu
 * create on 15/11/8.
 */

@Controller
@RequestMapping("/account")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AccountProfileDao accountProfileDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private AccountTagRecordDao accountTagRecordDao;
    @Autowired
    private DozerBeanMapper dozerMapper;
    @Autowired
    private SponsorDao sponsorDao;
    @Autowired
    private FormDao formDao;
    @Autowired
    private FormSaveDao formSaveDao;
    @Autowired
    private FormApplyDao formApplyDao;
    @Autowired
    private MailSubscribeDao mailSubscribeDao;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private JavaMailSender mailSender;
    private static WebConstants c = new WebConstants();
  /* @RequestMapping(value = "/login",method = RequestMethod.GET)
   public String login(Model model) {
       return "account/login";
   }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "is_memory",required = false,defaultValue = "false")boolean isMemory
    ) {
        if (username != null && password != null) {
            Account account = accountDao.findByUsername(username);
            if (account == null) {
                return new ResponseEntity<String>("user not exist", null, HttpStatus.BAD_REQUEST);
                //  return "redirect:/account/login?msg=user not exist";
            }
            if (!password.equals(account.getPassword())) {
                // return "redirect:/account/login?msg=username or password error";
                return new ResponseEntity<String>("username or password error", null, HttpStatus.BAD_REQUEST);
            }
            // model.addAttribute("username",account.getUsername());
            if(isMemory){
                Cookie usernameCookie=new Cookie("username",username);
                Cookie passwordCookie=new Cookie("password",password);
                usernameCookie.setPath("/");
                passwordCookie.setPath("/");
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }
            request.getSession().setAttribute("username", account.getUsername());
            request.getSession().setAttribute("accountId", (Long) account.getId());
            return new ResponseEntity<String>("success", null, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("username or password is null", null, HttpStatus.BAD_REQUEST);
        }

        // return "redirect:/user_center/user_info?account_id="+account.getId();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(
            HttpServletRequest request
    ) {
            request.getSession().setAttribute("status",false);
            request.getSession().setAttribute("accountId",null);
            return "redirect:/index";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ResponseEntity<String> updatePassword(
            HttpServletRequest request,
            @RequestParam(value = "password", required = true) String password
    ) {
        if (password != null) {
            long accountId = (long) request.getSession().getAttribute("accountId");
            if (password == null || password.equals("")) {
                return new ResponseEntity<>("password can not be null", null, HttpStatus.BAD_REQUEST);
            } else {
                accountDao.updatePassword(accountId, password);
                return new ResponseEntity<>("success", null, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("password is null", null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String updateProfile(
            HttpServletRequest request,
            @RequestParam(value = "identity", required = false) String identity,
            @RequestParam(value = "sex", required = false) String sex,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "phone", required = false) String phone
    ) {
        long accountId = (long) request.getSession().getAttribute("accountId");
        Account account = accountDao.findOne(accountId);
        AccountProfile accountProfile = accountProfileDao.findByAccount(account);
        if (accountProfile == null) {
            accountProfile = new AccountProfile();
            if (identity == null || identity.equals("")) {
                accountProfile.setIdentity("");
            } else {
                accountProfile.setIdentity(identity);
            }
            if (birthday == null || birthday.equals("")) {
                accountProfile.setBirthday("");
            } else {
                accountProfile.setBirthday(birthday);
            }
            if (address == null || address.equals("")) {
                accountProfile.setAddress("");
            } else {
                accountProfile.setAddress(address);
            }
            if (phone == null || phone.equals("")) {
                accountProfile.setPhone("");
            } else {
                accountProfile.setPhone(phone);
            }
            if (sex == null || sex.equals("")) {
                accountProfile.setSex("");
            } else {
                accountProfile.setSex(sex);
            }
            accountProfile.setAccount(account);
            accountProfileDao.save(accountProfile);
        } else {
            if (identity == null || identity.equals("")) {
                accountProfile.setIdentity("");
            } else {
                accountProfile.setIdentity(identity);
            }
            if (birthday == null || birthday.equals("")) {
                accountProfile.setBirthday("");
            } else {
                accountProfile.setBirthday(birthday);
            }
            if (address == null || address.equals("")) {
                accountProfile.setAddress("");
            } else {
                accountProfile.setAddress(address);
            }
            if (phone == null || phone.equals("")) {
                accountProfile.setPhone("");
            } else {
                accountProfile.setPhone(phone);
            }
            if (sex == null || sex.equals("")) {
                accountProfile.setSex("");
            } else {
                accountProfile.setSex(sex);
            }
            accountProfileDao.save(accountProfile);
        }
        return "redirect:/user_center/user_info";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> adminAccountAdd(
            HttpServletRequest request,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "is_subscribe", required = false, defaultValue = "true") boolean isSubscribe
    ) {
        if (username != null && password != null) {
            Pattern pattern=Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
            Matcher matcher=pattern.matcher(username);
            boolean isEmail=matcher.matches();
            if(isEmail){
                Account account = accountDao.findByUsername(username);  //判断是否存在此用户
                if (account != null) {   //说明该用户已存在
                    return new ResponseEntity<>("user existed", null, HttpStatus.BAD_REQUEST);
                } else {
                    account = new Account();
                    account.setRegisterTime(new Date());
                    account.setUsername(username);
                    account.setPassword(password);
                    accountDao.save(account);
                    if(isSubscribe) {
                        String email = account.getUsername().trim();
                        if (mailSubscribeDao.countByEmail(email) == 0) {
                            MailSubscribe mailSubscribe = new MailSubscribe();
                            mailSubscribe.setEmail(email);
                            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                            String token = df.format(new Date()) + String.valueOf(new Random().nextInt(100));
                            mailSubscribe.setToken(token);
                            mailSubscribeDao.save(mailSubscribe);
                            asyncSendSimpleEmail(email, "注册用户订阅成功提醒", "你好：" + email + "\n 感谢你订阅西宁FIRST青年电影展，当有关于电影展最新动态时，会第一时间通过邮件通知您。\n 取消订阅请在浏览器中打开下面的链接:" + WebConstants.HOST_NAME + "subscrible/cancel?id=" + String.valueOf(mailSubscribe.getId()) + "&token=" + mailSubscribe.getToken());
                        }
                    }
                    else{

                    }
                    request.getSession().setAttribute("username", account.getUsername());
                    request.getSession().setAttribute("accountId", (Long) account.getId());
                    return new ResponseEntity<>("success", null, HttpStatus.OK);
            }
            }
            else{
                return new ResponseEntity<>("username is not email", null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<String>("username or password is null", null, HttpStatus.BAD_REQUEST);
        }
    }



        /*@RequestMapping(value = "/form/get", method = RequestMethod.GET)
        public String formList(
                Model model
        ){
            List<FormSimpleVO> formSimpleVOList=new LinkedList<FormSimpleVO>();
            List<Form> formList=formDao.findAll();
                for(Form f:formList){
                    if(f!=null){
                        FormSimpleVO formSimpleVO=new FormSimpleVO();
                        formSimpleVO.setId(f.getId());
                        formSimpleVO.setControlDesc(f.getControlDesc());
                        formSimpleVO.setCreateAt(f.getCreateAt());
                        formSimpleVO.setFormName(f.getFormName());
                        formSimpleVOList.add(formSimpleVO);
                    }
                }
               model.addAttribute("formSimpleVOList",formSimpleVOList);
            return "";
            }*/

    @RequestMapping(value = "/form/save", method = RequestMethod.POST)  //用户表单保存
    public ResponseEntity<String> accountFormSave(
            HttpServletRequest request,
            @RequestParam(value = "form_id", required = true) long formId,
            @RequestParam(value = "content_desc", required = true) String contentDesc
    ) {
        if (contentDesc != null) {
            long accountId = (long) request.getSession().getAttribute("accountId");
            Account account = accountDao.findOne(accountId);
            Form form = formDao.findOne(formId);
            FormApply formApply = formApplyDao.findByFormAndAccount(form, account);
            if (formApply != null) {
                return new ResponseEntity<>("user apply form more times", null, HttpStatus.BAD_REQUEST);
            }
            FormSave formSave = formSaveDao.findByFormAndAccount(form, account);
            if (formSave != null) {  //之前保存过，更新保存
                formSave.setSaveTime(new Date());
                formSave.setContentDesc(contentDesc);
                formSaveDao.save(formSave);
                return new ResponseEntity<>("success", null, HttpStatus.OK);
            } else {  //第一次保存
                formSave = new FormSave();
                formSave.setAccount(account);
                formSave.setForm(form);
                formSave.setSaveTime(new Date());
                formSave.setContentDesc(contentDesc);
                formSave.setStatus(0);
                formSaveDao.save(formSave);
                return new ResponseEntity<>("success", null, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<String>("contentDesc is null", null, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/form/apply", method = RequestMethod.POST)  //表单提交
    public ResponseEntity<String> accountFormApply(
            HttpServletRequest request,
            @RequestParam(value = "form_id", required = true) long formId,
            @RequestParam(value = "content_desc", required = true) String contentDesc
    ) {
        if (contentDesc != null) {
            long accountId = (long) request.getSession().getAttribute("accountId");
            Account account = accountDao.findOne(accountId);
            Form form = formDao.findOne(formId);
            FormApply formApply = formApplyDao.findByFormAndAccount(form, account);
            if (formApply != null) {  //之前提交过，不允许提交
                return new ResponseEntity<>("user apply form more times", null, HttpStatus.BAD_REQUEST);
            }
            FormSave formSave = formSaveDao.findByFormAndAccount(form, account);
            if (formSave != null) { //之前未提交，但保存过
                formSaveDao.deleteByAccountAndForm(accountId, formId);
                formApply = new FormApply();
                formApply.setAccount(account);
                formApply.setForm(form);
                formApply.setApplyTime(new Date());
                formApply.setContentDesc(contentDesc);
                formApply.setStatus(1);
                formApplyDao.save(formApply);
                return new ResponseEntity<>("success", null, HttpStatus.OK);
            } else { //未保存，但第一次提交
                formApply = new FormApply();
                formApply.setAccount(account);
                formApply.setForm(form);
                formApply.setApplyTime(new Date());
                formApply.setContentDesc(contentDesc);
                formApply.setStatus(1);
                formApplyDao.save(formApply);
                return new ResponseEntity<>("success", null, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<String>("contentDesc is null", null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/user_center/user_apply", method = RequestMethod.GET)  //表单提交
    public String accountFormList(
            Model model,
            HttpServletRequest request
    ) {
        long accountId = (long) request.getSession().getAttribute("accountId");
        Account account = accountDao.findOne(accountId);
        List<FormVO> formVOList = new LinkedList<FormVO>();
        List<FormSave> formSaveList = formSaveDao.findByAccount(account);
        List<FormApply> formApplyList = formApplyDao.findByAccount(account);
        for (FormSave fs : formSaveList) {
            if (fs != null) {
                FormVO formVO = dozerMapper.map(fs.getForm(), FormVO.class);
                formVO.setContentDesc(fs.getContentDesc());
                formVO.setStatus(fs.getStatus());
                formVO.setUsername(fs.getAccount().getUsername());
                formVOList.add(formVO);
            }
        }
        for (FormApply fa : formApplyList) {
            if (fa != null) {
                FormVO formVO = dozerMapper.map(fa.getForm(), FormVO.class);
                formVO.setContentDesc(fa.getContentDesc());
                formVO.setStatus(fa.getStatus());
                formVO.setUsername(fa.getAccount().getUsername());
                formVOList.add(formVO);
            }
        }
        model.addAttribute("formVOList", formVOList);
        return front("user_center/user_apply");
    }

    @RequestMapping(value = "/my/form/save", method = RequestMethod.POST)  //个人中心报名表保存
    public ResponseEntity<String> myFormSave(
            HttpServletRequest request,
            @RequestParam(value = "form_id", required = true) long formId,
            @RequestParam(value = "content_desc", required = true) String contentDesc

    ) {
        if (contentDesc != null) {
            long accountId = (long) request.getSession().getAttribute("accountId");
            formSaveDao.updateContentDesc(accountId, formId, contentDesc);
            return new ResponseEntity<>("success", null, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("contentDesc is null", null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/my/form/apply", method = RequestMethod.POST)  //个人中心报名表提交
    public ResponseEntity<String> myFormApply(
            HttpServletRequest request,
            @RequestParam(value = "form_id", required = true) long formId,
            @RequestParam(value = "content_desc", required = true) String contentDesc

    ) {
        if (contentDesc != null) {
            long accountId = (long) request.getSession().getAttribute("accountId");
            Account account = accountDao.findOne(accountId);
            Form form = formDao.findOne(formId);
            formSaveDao.deleteByAccountAndForm(accountId, formId);
            FormApply formApply = new FormApply();
            formApply.setForm(form);
            formApply.setAccount(account);
            formApply.setApplyTime(new Date());
            formApply.setContentDesc(contentDesc);
            formApply.setStatus(1);
            formApplyDao.save(formApply);
            return new ResponseEntity<>("success", null, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("contentDesc is null", null, HttpStatus.BAD_REQUEST);
        }
    }

    private String front(String view) {
        return "frontend/" + view;
    }

    public void asyncSendSimpleEmail(final String toEmailAddr, final String subject, final String content) {
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(toEmailAddr));
                mimeMessage.setFrom(new InternetAddress(WebConstants.Email.USERNAME));
                mimeMessage.setSubject(subject, "utf-8");
                mimeMessage.setText(content);
            }
        };

        taskExecutor.execute(new Runnable() {

            public void run() {
                try {
                    logger.debug("begin send email to:" + toEmailAddr);

                    mailSender.send(preparator);

                    logger.debug("the email to:" + toEmailAddr + "is sended");
                } catch (MailException ex) {
                    logger.error(ex.getMessage());
                }
            }
        });
    }
}

