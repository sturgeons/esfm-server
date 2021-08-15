package com.zf.zf_server.modules.wizard.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zf.zf_server.extension.api.ApiController;
import com.zf.zf_server.extension.api.R;
import com.zf.zf_server.extension.constant.ResponseInfoConstant;
import com.zf.zf_server.modules.wizard.entity.co.WxMpProperties;
import com.zf.zf_server.modules.wizard.service.PortalService;
import com.zf.zf_server.utils.CheckUserAgent;
import com.zf.zf_server.utils.YaoSay;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

/**
 * (Login)表控制层
 * @author yaoxin
 * @since 2020-04-22 11:10:41
 */
@AllArgsConstructor
@RestController
@EnableConfigurationProperties(WxMpProperties.class)
public class PortalController extends ApiController {

    @Resource
    private final PortalService portalService;

    /**
     * 主页
     */
    @GetMapping("/")
    public void index(HttpServletResponse response,HttpServletRequest request) throws IOException {
        final String userAgent = request.getHeader("user-agent");
        Random random=new Random();
        int v=random.nextInt(9999999);
        if(!CheckUserAgent.checkAgentIsMobile(userAgent)){
            response.sendRedirect("/p/admin.html?v="+v);
            YaoSay.p("访问admin电脑端");
            return;
        }
        response.sendRedirect("/p/index.html?v="+v);
        YaoSay.p("访问index手机端");
    }


    @GetMapping("login.sysUser.tel")
    public R<?> loginDoSysUserByTel(@RequestParam("tel") String tel, @RequestParam("password") String password) {
        if (StrUtil.isEmpty(tel) || StrUtil.isEmpty(password)) {
            return failed(ResponseInfoConstant.ERROR_USERNAME_OR_PASSWORD_IS_EMPTY);
        }
        return portalService.loginDoBySysUserByTel(tel, password);
    }


    private final WxMpProperties properties;

    @RequestMapping(value = "login.wx", method = RequestMethod.GET)
    public void wxLogin(HttpServletResponse response,HttpServletRequest request) throws IOException {

        final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
        }

        //请求获取code的回调地址
        //用线上环境的域名或者用内网穿透，不能用ip
        String callBack = "http://"+request.getServerName() + "/wx.callBack";

        //请求地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize" +
                "?appid=" + configs.get(0).getAppId() +
                "&redirect_uri=" + URLEncoder.encode(callBack) +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        //重定向
        response.sendRedirect(url);
    }

    //	回调方法
    @RequestMapping("wx.callBack")
    public void wxCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {

        final List<WxMpProperties.MpConfig> configs = this.properties.getConfigs();
        if (configs == null) {
            throw new RuntimeException("大哥，拜托先看下项目首页的说明（readme文件），添加下相关配置，注意别配错了！");
        }
        String code = request.getParameter("code");

        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=" + configs.get(0).getAppId() +
                "&secret=" + configs.get(0).getSecret() +
                "&code=" + code +
                "&grant_type=authorization_code";

        String result = HttpUtil.get(url);

        System.out.println("请求获取access_token:" + result);
        //返回结果的json对象
        JSONObject resultObject = JSON.parseObject(result);

        //请求获取userInfo
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                "?access_token=" + resultObject.getString("access_token") +
                "&openid=" + resultObject.getString("openid") +
                "&lang=zh_CN";

        String resultInfo = HttpUtil.get(infoUrl);

        //此时已获取到userInfo，再根据业务进行处理
        System.out.println("请求获取userInfo:" + resultInfo);
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
    }

    //	回调方法
    @GetMapping("/test")
    public JSONObject wxTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject res=new JSONObject();
        res.put("getServerName", "http://"+request.getServerName());
        res.put("context", "修改了mapper");

        return res;
    }

}
