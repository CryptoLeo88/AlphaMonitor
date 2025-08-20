package com.block.serviceImpl;

import com.block.entity.Id;
import com.block.mapper.IdMapper;
import com.block.service.IdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrfc
 * @since 2023-06-08
 */
@Service
public class IdServiceImpl extends ServiceImpl<IdMapper, Id> implements IdService {

}
