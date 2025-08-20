package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.FamilyMembers;
import com.block.entity.PersonInfo;
import com.block.entity.PersonInfo;
import com.block.mapper.PersonInfoMapper;
import com.block.mapper.PersonInfoMapper;
import com.block.service.PersonInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2023-04-28
 */
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfo> implements PersonInfoService {

    private final PersonInfoMapper personInfoMapper;

    @Autowired
    public PersonInfoServiceImpl(PersonInfoMapper personInfoMapper){
        this.personInfoMapper = personInfoMapper;
    }

    @Override
    public IPage<PersonInfo> page(Integer page, Integer size, Map<String, String> search) {

        QueryWrapper<PersonInfo> wrapper = new QueryWrapper<>();
        try {
            Integer personId = Integer.parseInt(search.get("personId"));
            wrapper.eq("user_id", personId);
        } catch (NumberFormatException e) {
            System.out.println("输入字符串无法转换为整数");
        }

        return personInfoMapper.selectPage(new Page<>(page, size), wrapper);
    }
}
