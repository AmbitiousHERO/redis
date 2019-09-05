package online.shixun.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.pojo.SeckillUser;
import online.shixun.pojo.vo.LoginVo;
import online.shixun.service.SeckillUserService;

@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	SeckillUserService userService;
	
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    
    @RequestMapping("/do_login")
    public String doLogin( LoginVo loginVo,HttpSession session) {
    	log.info(loginVo.toString());
    	//登录
    	SeckillUser user = userService.login(loginVo);
    	session.setAttribute("user", user);
    	return "forward:/goods/to_list";
    }

    @RequestMapping("/go_login")
    public String isLogin(@RequestParam String userName, HttpSession session){
        SeckillUser user = userService.getByName(userName);
        log.info(userName);
        if (user == null){
            session.setAttribute("error","您暂时没有权限加入秒杀");
            return "login";
        }
        session.setAttribute("user", user);
        return "forward:/goods/to_list";
    }
}
