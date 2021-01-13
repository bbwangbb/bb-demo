package cn.mb.poi.excelexport.service.impl;

import cn.mb.poi.excelexport.dao.PoiTestMapper;
import cn.mb.poi.excelexport.dao.entity.PoiTest;
import cn.mb.poi.excelexport.service.PoiTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PoiTestServiceImpl extends ServiceImpl<PoiTestMapper, PoiTest> implements PoiTestService {
}
