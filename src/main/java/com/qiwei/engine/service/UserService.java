package com.qiwei.engine.service;

import com.qiwei.engine.domain.User;
import com.qiwei.engine.domain.UserExample;
import com.qiwei.engine.exception.BusinessException;
import com.qiwei.engine.exception.BusinessExceptionCode;
import com.qiwei.engine.mapper.UserMapper;
import com.qiwei.engine.req.UserLoginReq;
import com.qiwei.engine.req.UserSaveReq;
import com.qiwei.engine.resp.UserLoginResp;
import com.qiwei.engine.util.CopyUtil;
import com.qiwei.engine.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;


    /**
     * Save
     */
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            User userDB = selectByLoginName(req.getLoginName());
            if (ObjectUtils.isEmpty(userDB)){
                // add
                user.setId(snowFlake.nextId());
                userMapper.insert(user);
            } else {
                // Username already exists
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }
    }

    public User selectByLoginName(String LoginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }


    /**
     * Login
     */
    public UserLoginResp login(UserLoginReq req) {
        User userDb = selectByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            // Username does not exist
            LOG.info("Username does not exist, {}", req.getLoginName());
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        } else {
            if (userDb.getPassword().equals(req.getPassword())) {
                // Login successful
                UserLoginResp userLoginResp = CopyUtil.copy(userDb, UserLoginResp.class);
                return userLoginResp;
            } else {
                // Wrong password
                LOG.info("Wrong password, enter password：{}, Database password：{}", req.getPassword(), userDb.getPassword());
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }
    }
}
