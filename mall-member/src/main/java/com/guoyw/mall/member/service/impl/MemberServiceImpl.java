package com.guoyw.mall.member.service.impl;

import com.guoyw.mall.common.exception.BusinessException;
import com.guoyw.mall.mbg.mapper.UmsMemberMapper;
import com.guoyw.mall.mbg.model.UmsMember;
import com.guoyw.mall.mbg.model.UmsMemberExample;
import com.guoyw.mall.member.config.properties.RedisKeyPrefixConfig;
import com.guoyw.mall.member.domain.Register;
import com.guoyw.mall.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @program: mall-demo
 * @description:
 * @author: guoyw
 * @create: 2020-04-11 23:37
 **/
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

  @Autowired
  private UmsMemberMapper umsMemberMapper;
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private RedisKeyPrefixConfig redisKeyPrefixConfig;
  @Autowired
  private PasswordEncoder passwordEncoder;

  //region 获取手机验证码
  @Override
  public String getOtpCode(String telPhone) {

    // 1.查询当前用户有没有注册
    UmsMemberExample example = new UmsMemberExample();
    example.createCriteria().andPhoneEqualTo(telPhone);
    List<UmsMember> reulstes = umsMemberMapper.selectByExample(example);
    if(!CollectionUtils.isEmpty(reulstes))
      throw new BusinessException("用户已注册！不能重复注册");

    //2.校验60s后有没有再次发送
    if(redisTemplate.hasKey(redisKeyPrefixConfig.getPrefix().getOtpCode() + telPhone))
      throw new BusinessException("请60s后再试");

    //3.生成随机验证码
    Random random = new Random();
    StringBuffer stringBuffer = new StringBuffer();

    for (int i = 0 ;i<6;i++){
      stringBuffer.append(random.nextInt(10));
    }

    log.info("otpcode{}"+stringBuffer.toString());
    redisTemplate.opsForValue().set(redisKeyPrefixConfig.getPrefix().getOtpCode()+telPhone,
        stringBuffer.toString(),redisKeyPrefixConfig.getExpire().getOtpCode(), TimeUnit.SECONDS);

    return stringBuffer.toString();
  }
  //endregion

  //region 会员注册
  @Override
  public int register(Register register) {

    String otpPhone = (String) redisTemplate.opsForValue().get(redisKeyPrefixConfig.getPrefix().getOtpCode()+register.getPhone());
    if(StringUtils.isEmpty(otpPhone) || !otpPhone.equals(register.getOtpCode()))
      throw new BusinessException("动态验证码不正确");

    UmsMember umsMember = new UmsMember();
    BeanUtils.copyProperties(register,umsMember);

    umsMember.setStatus(1);
    umsMember.setMemberLevelId(4L);
    umsMember.setPassword(passwordEncoder.encode(register.getPassword()));

    return umsMemberMapper.insertSelective(umsMember);
  }
  //endregion
}
