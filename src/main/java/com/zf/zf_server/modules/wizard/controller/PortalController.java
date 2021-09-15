package com.zf.zf_server.modules.wizard.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.CommConstant;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.system.entity.SysUser;
import com.zf.zf_server.modules.system.service.SysUserService;
import com.zf.zf_server.modules.wizard.entity.co.WxProperties;
import com.zf.zf_server.modules.wizard.service.PortalService;
import com.zf.zf_server.utils.CheckUserAgent;
import com.zf.zf_server.utils.YaoSay;
import com.zf.zf_server.utils.weixin.SignUtil;
import com.zf.zf_server.utils.weixin.TemplateMsgUtil;
import com.zf.zf_server.utils.weixin.bo.TemplateBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * (Login)表控制层
 *
 * @author yaoxin
 * @since 2020-04-22 11:10:41
 */

@RestController
@EnableConfigurationProperties(WxProperties.class)
public class PortalController extends ApiController {


    @Autowired
    public PortalController(SignUtil signUtil, TemplateMsgUtil templateMsgUtil, PortalService portalService, SysUserService sysUserService, WxProperties wxMpProperties) {
        this.signUtil = signUtil;
        this.templateMsgUtil = templateMsgUtil;
        this.portalService = portalService;
        this.sysUserService = sysUserService;
        this.wxMpProperties = wxMpProperties;
    }


    private final SignUtil signUtil;
    private final TemplateMsgUtil templateMsgUtil;
    private final PortalService portalService;
    private final SysUserService sysUserService;
    private final WxProperties wxMpProperties;

    /**
     * 主页
     */
    @GetMapping("/")
    public void index(HttpServletResponse response, HttpServletRequest request) throws IOException {
        final String userAgent = request.getHeader("user-agent");
        Random random = new Random();
        int v = random.nextInt(9999999);
        if (!CheckUserAgent.checkAgentIsMobile(userAgent)) {
            response.sendRedirect("/p/admin.html?v=" + v);
            YaoSay.p("访问admin电脑端");
        } else {
            response.sendRedirect("/p/mobile.html?v=" + v);
            YaoSay.p("访问index手机端");
        }

    }


    @GetMapping("login.sysUser.tel")
    public R<?> loginDoSysUserByTel(@RequestParam("tel") String tel, @RequestParam("password") String password) {
        if (StrUtil.isEmpty(tel) || StrUtil.isEmpty(password)) {
            return failed(ResponseInfoConstant.ERROR_USERNAME_OR_PASSWORD_IS_EMPTY);
        }
        return portalService.loginDoBySysUserByTel(tel, password);
    }


    @GetMapping(value = "wx.auth/{token}")
    public void wxLogin(@PathVariable String token, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //请求获取code的回调地址
        String callBack = "http://" + request.getServerName() + "/wx.callBack/" + token;
        //请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + wxMpProperties.getAppId() +
                "&redirect_uri=" + URLEncoder.encode(callBack, StandardCharsets.UTF_8) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //	回调方法
    @RequestMapping("wx.callBack/{token}")
    public void wxCallBack(@PathVariable String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + wxMpProperties.getAppId() +
                "&secret=" + wxMpProperties.getSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";

        String result = HttpUtil.get(url);
        System.out.println("请求获取access_token:" + result);
        //返回结果的json对象
        JSONObject resultObject = JSON.parseObject(result);
        R<?> res = sysUserService.saveWxOpenId(token, resultObject.getString("openid"));
        if (res.ok()) {
            response.sendRedirect("http://" + request.getServerName());
        } else {
            response.sendRedirect("http://" + request.getServerName() + "/wxAuthFail");
        }


    }

    //获取用户信息
    // public void getUserInfo() {
    //请求获取userInfo
//        String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
//                "?access_token=" + resultObject.getString("access_token") +
//                "&openid=" + resultObject.getString("openid") +
//                "&lang=zh_CN";
//        String resultInfo = HttpUtil.get(infoUrl);
//
//        //此时已获取到userInfo，再根据业务进行处理
//        System.out.println("请求获取userInfo:" + resultInfo);
//        WxUserInfo resWxInfo = wxUserInfoService.insertOneByJSON(resultInfo);
//        if (resWxInfo.getId() != null) {
//            //结果成功转到主页
//            Cookie cookie = new Cookie(ComConstant.COOKIES_WX_INFO_ID, resWxInfo.getId());
//            cookie.setMaxAge(365 * 20 * 24 * 60 * 60);
//            response.addCookie(cookie);
//            response.sendRedirect(zfProperties.getDomainTvIndexHtml());
//        } else {
//            response.sendRedirect(zfProperties.getDomainMobileIndexHtml());
//        }
    // }

    //	回调方法
    @GetMapping("/test")
    public void wxTest(HttpServletRequest request) {
        String token = request.getHeader(CommConstant.REQUEST_TOKEN);
        R<SysUser> sysInfo = sysUserService.getUserInfo(token);

        TemplateBo templateBo = TemplateBo.New();
        String msgStr = templateBo.setTouser(sysInfo.getData().getWxOpenid())
                .setTemplate_id("P--dwdQg6uDwAnLZunBLpGkPu-Xer3hwvgdJrCFPlIA")
                .setUrl("http://n.zf-ap.com")
                .setTopcolor("#721212")
                .add("first", "你收到一个新的任务。", "#0000FF")
                .add("Apply_id", "21101121", "#0F0F0F")
                .add("Apply_Type", "维修-电气-PLC", "#0000FF")
                .add("Apply_State", "新任务", "#0000FF")
                .add("Apply_CreateTime", "2021-09-14 10:10:10", "#0000FF")
                .build();
        templateMsgUtil.send(msgStr);
    }


    //	回调方法
    @GetMapping("/wxGetAccessToken")
    public R<?> wxGetAccessToken(@RequestParam(value = "url") String url) {
        return signUtil.sign(url);
    }
}
