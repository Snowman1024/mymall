package com.snowman.mymall.service.impl;

import com.snowman.mymall.common.vo.GoodsVO;
import com.snowman.mymall.entity.GoodsEntity;
import com.snowman.mymall.repository.GoodsRepository;
import com.snowman.mymall.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Snowman2014
 * @Date 2019/10/11 15:17
 * @Version 1.0
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsRepository goodsRepository;

    /**
     *
     * @return
     */
    @Override
    public List<GoodsVO> queryNewGoodsList() {
        logger.info("首页查询新商品信息service开始");

        List<GoodsVO> goodsVOList = new ArrayList<>();

        List<GoodsEntity> goodsEntityList =  goodsRepository.queryNewGoodsList();
        if(CollectionUtils.isEmpty(goodsEntityList)){
            return goodsVOList;
        }
        BeanCopier copier = BeanCopier.create(GoodsEntity.class,GoodsVO.class,false);
        for(GoodsEntity entity:goodsEntityList){
            GoodsVO goodsVO = new GoodsVO();
            copier.copy(entity,goodsVO,null);
            goodsVOList.add(goodsVO);
        }
        logger.info("首页查询新商品信息service结束");
        return goodsVOList;
    }
}
