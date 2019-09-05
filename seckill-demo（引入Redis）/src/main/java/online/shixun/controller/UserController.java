package online.shixun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.pojo.SeckillUser;
import online.shixun.result.Result;


@Controller
@RequestMapping("/user")
public class UserController {
	
//	@Autowired
//	RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/info")
    @ResponseBody
    public Result<SeckillUser> info(Model model,SeckillUser user) {
        return Result.success(user);
    }
    
    @RequestMapping(value="")
    public String redisTest() {
    	SeckillUser seckillUser = new SeckillUser();
    	seckillUser.setId(3L);
    	seckillUser.setNickname("adiss");
    	seckillUser.setPassword("123456");
//    	redisTemplate.opsForValue().set("user", seckillUser);
    	return "goods_list";
    }
    
}
