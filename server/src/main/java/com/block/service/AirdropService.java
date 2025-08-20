package com.block.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.entity.Airdrop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.block.entity.FamilyMembers;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lrfc
 * @since 2025-07-14
 */

public interface AirdropService extends IService<Airdrop> {
    IPage<Airdrop> page(Integer page, Integer size, Map<String, String> search);
}
