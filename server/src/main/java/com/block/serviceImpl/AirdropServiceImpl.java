package com.block.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.block.entity.Airdrop;
import com.block.entity.Airdrop;
import com.block.mapper.AirdropMapper;
import com.block.mapper.AirdropMapper;
import com.block.service.AirdropService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2025-07-14
 */
@Service
public class AirdropServiceImpl extends ServiceImpl<AirdropMapper, Airdrop> implements AirdropService {

    private final AirdropMapper airdropMapper;

    @Autowired
    public AirdropServiceImpl(AirdropMapper productMapper){
        this.airdropMapper = productMapper;
    }

    @Override
    public IPage<Airdrop> page(Integer page, Integer size, Map<String, String> search) {
        QueryWrapper<Airdrop> wrapper = new QueryWrapper<>();
        LocalDate today = LocalDate.now();
        try {
            String type = search.get("type");
            if (type .equals("2") ){
                wrapper.lt("airdrop_time",today);
            }else if(type.equals("3")){
                wrapper.ge("airdrop_time",today);
            }
        }catch (Exception e) {
            System.out.println("获取type错误");
        }

        wrapper.orderByDesc("airdrop_time");

        return airdropMapper.selectPage(new Page<>(page, size), wrapper);
    }
}
