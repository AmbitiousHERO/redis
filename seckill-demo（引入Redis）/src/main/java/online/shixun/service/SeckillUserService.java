package online.shixun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.dao.SeckillUserDao;
import online.shixun.exception.GlobalException;
import online.shixun.pojo.SeckillUser;
import online.shixun.pojo.vo.LoginVo;
import online.shixun.result.CodeMsg;


@Service
public class SeckillUserService {
	
	@Autowired
	SeckillUserDao seckillUserDao;
	
	public SeckillUser getById(long id) {
		return seckillUserDao.getById(id);
	}

	public SeckillUser login(LoginVo loginVo) {
		if(loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		//判断手机号是否存在
		SeckillUser user = getById(Long.parseLong(mobile));
		if(user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//验证密码
		if(!formPass.equals(user.getPassword())) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		return user;
	}

	public SeckillUser getByName(String userName){
		return seckillUserDao.getByName(userName);
	}
}
