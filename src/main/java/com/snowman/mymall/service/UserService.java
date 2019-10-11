package com.snowman.mymall.service;

import com.snowman.mymall.common.exception.ServiceException;
import com.snowman.mymall.common.validator.Assert;
import com.snowman.mymall.common.vo.UserVO;
import com.snowman.mymall.entity.UserEntity;
import com.snowman.mymall.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2019/10/8 15:53
 * @Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BeanCopier copier = BeanCopier.create(UserEntity.class,UserVO.class,false);

    /**
     * 通过id查用户
     * @param userId
     * @return
     */
    public UserVO queryByUserId(Long userId) {
        UserEntity entity = userRepository.queryByUserId(userId);
        if(entity == null){
            return null;
        }
        UserVO userVO = new UserVO();
        copier.copy(entity,userVO,null);
        return userVO;

    }

    /**
     * 通过手机号查用户
     * @param mobile
     * @return
     */
    public UserVO queryByMobile(String mobile) {
        UserEntity entity = userRepository.queryByMobile(mobile);
        if(entity == null){
            return null;
        }
        UserVO userVO = new UserVO();
        copier.copy(entity,userVO,null);
        return userVO;
    }

    /**
     * 登陆
     * @param mobile
     * @param password
     * @return
     */
    public long login(String mobile, String password) {
        UserVO user = queryByMobile(mobile);
        Assert.isNull(user, "手机号或密码错误");

        //密码错误
        if (!user.getPassWord().equals(DigestUtils.sha256Hex(password))) {
            throw new ServiceException("手机号或密码错误");
        }

        return user.getUserId();
    }

    /**
     * 通过openId查用户
     * @param openId
     * @return
     */
    public UserVO queryByOpenId(String openId) {
        UserEntity entity = userRepository.queryByOpenId(openId);
        if(entity == null){
            return null;
        }
        UserVO userVO = new UserVO();
        copier.copy(entity,userVO,null);
        return userVO;
    }

    /**
     * 保存
     * @param userVo
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(UserVO userVo) {
        UserEntity entity = new UserEntity();
        BeanCopier entityCopier = BeanCopier.create(UserVO.class,UserEntity.class,false);
        entityCopier.copy(userVo,entity,null);
        userRepository.save(entity);
    }
}
