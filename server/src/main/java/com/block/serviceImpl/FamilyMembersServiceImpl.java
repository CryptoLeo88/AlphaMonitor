package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.EducationInfo;
import com.block.entity.FamilyMembers;
import com.block.entity.FamilyMembers;
import com.block.mapper.FamilyMembersMapper;
import com.block.mapper.FamilyMembersMapper;
import com.block.service.FamilyMembersService;
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
public class FamilyMembersServiceImpl extends ServiceImpl<FamilyMembersMapper, FamilyMembers> implements FamilyMembersService {

    private final FamilyMembersMapper familyMembersMapper;

    @Autowired
    public FamilyMembersServiceImpl(FamilyMembersMapper productMapper){
        this.familyMembersMapper = productMapper;
    }

    @Override
    public IPage<FamilyMembers> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<FamilyMembers> wrapper = new QueryWrapper<>();
        try {
            Integer personId = Integer.parseInt(search.get("personId"));
            if (personId.equals(1)){
                return familyMembersMapper.selectPage(new Page<>(page, size), wrapper);
            }
            wrapper.eq("person_id", personId);
        } catch (NumberFormatException e) {
            System.out.println("输入字符串无法转换为整数");
        }
        return familyMembersMapper.selectPage(new Page<>(page, size), wrapper);
    }

}
