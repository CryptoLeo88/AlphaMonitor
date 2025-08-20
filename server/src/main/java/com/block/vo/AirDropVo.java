package com.block.vo;

import com.block.entity.Airdrop;
import lombok.Data;

@Data
public class AirDropVo extends Airdrop {

    private String value;

    private String marketCap;
}
