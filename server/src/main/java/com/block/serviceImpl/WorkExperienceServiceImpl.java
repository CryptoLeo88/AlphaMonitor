package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.WorkExperience;
import com.block.entity.WorkExperience;
import com.block.mapper.WorkExperienceMapper;
import com.block.mapper.WorkExperienceMapper;
import com.block.service.WorkExperienceService;
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
public class WorkExperienceServiceImpl extends ServiceImpl<WorkExperienceMapper, WorkExperience> implements WorkExperienceService {

    private final WorkExperienceMapper workExperienceMapper;

    @Autowired
    public WorkExperienceServiceImpl(WorkExperienceMapper workExperienceMapper){
        this.workExperienceMapper = workExperienceMapper;
    }

    @Override
    public IPage<WorkExperience> page(Integer page, Integer size, Map<String, String> search) {

        QueryWrapper<WorkExperience> wrapper = new QueryWrapper<>();
        try {
            Integer userId = Integer.parseInt(search.get("personId"));// userId和personID是一一对应的，而且admin默认是1
            if (userId.equals(1)){
                return workExperienceMapper.selectPage(new Page<>(page, size), wrapper);
            }
            wrapper.eq("person_id", userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("输入字符串无法转换为整数");
        }
        return workExperienceMapper.selectPage(new Page<>(page, size), wrapper);
    }
}
