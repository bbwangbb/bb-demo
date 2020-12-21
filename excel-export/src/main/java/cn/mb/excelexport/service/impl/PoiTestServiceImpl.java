package cn.mb.excelexport.service.impl;

import cn.mb.excelexport.dao.PoiTestMapper;
import cn.mb.excelexport.dao.entity.PoiTest;
import cn.mb.excelexport.service.PoiTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PoiTestServiceImpl extends ServiceImpl<PoiTestMapper, PoiTest> implements PoiTestService {
}
