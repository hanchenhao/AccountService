package com.hanchenhao.account.DAO.Mapper;

import com.hanchenhao.account.Model.Persistence.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserInfoMapper {
    @Select("select `id`,`username` ,`password` ,create_time, update_time from account_userinfo where id = #{id}")
     UserInfo getUserInfoById(@Param("id") long id) ;
}
