package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.EducationInfo;
import com.block.entity.ProductType;
import com.block.mapper.EducationInfoMapper;
import com.block.mapper.ProductTypeMapper;
import com.block.service.EducationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
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
public class EducationInfoServiceImpl extends ServiceImpl<EducationInfoMapper, EducationInfo> implements EducationInfoService {

    private final EducationInfoMapper educationInfoMapper;

    @Autowired
    public EducationInfoServiceImpl(EducationInfoMapper educationInfoMapper){
        this.educationInfoMapper = educationInfoMapper;
    }

    @Override
    public IPage<EducationInfo> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<EducationInfo> wrapper = new QueryWrapper<>();
        try {
            Integer personId = Integer.parseInt(search.get("personId"));
            if (personId.equals(1)){
                return educationInfoMapper.selectPage(new Page<>(page, size), wrapper);
            }
            wrapper.eq("person_id", personId);
        } catch (NumberFormatException e) {
            System.out.println("输入字符串无法转换为整数");
        }
        return educationInfoMapper.selectPage(new Page<>(page, size), wrapper);
    }
}
