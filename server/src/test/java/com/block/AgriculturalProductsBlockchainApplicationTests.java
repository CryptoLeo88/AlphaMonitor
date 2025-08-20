package com.block;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.block.controller.AirdropController;
import com.block.controller.IdController;
import com.block.controller.UpBlockController;
import com.block.entity.Id;
import com.block.entity.UpBlock;
import com.block.serviceImpl.DexscreenerService;
import com.block.utils.PageUtil;
import com.block.utils.Result;
import com.block.vo.AirDropVo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AgriculturalProductsBlockchainApplicationTests {



    @Autowired
    private AirdropController airdropController;

    @Autowired
    private DexscreenerService dexscreenerService;



}
