package com.trainingmanagesys.web.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trainingmanagesys.conf.exception.APIException;
import com.trainingmanagesys.web.finance.entity.Finance;
import com.trainingmanagesys.web.test.entity.Test;
import com.trainingmanagesys.web.test.dao.TestMapper;
import com.trainingmanagesys.web.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trainingmanagesys.web.test.vo.TestVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

    private void checkTestExistence(Long testId){
        if (getTest(testId) == null)
            throw new APIException("该考试不存在");
    }

    @Override
    public Long addTest(Test test) {
        baseMapper.insert(test);
        return test.getTestSerial();
    }

    @Override
    public String updateTest(Test test) {
        checkTestExistence(test.getTestSerial());
        String result = "更新考试失败";
        int code = baseMapper.updateById(test);
        if (code == 1)
            result = "更新考试成功";
        return result;
    }

    @Override
    public String deleteTest(Long testId) {
        checkTestExistence(testId);
        String result = "删除考试失败";
        int code = baseMapper.deleteById(testId);
        if (code == 1)
            result = "删除考试成功";
        return result;
    }

    @Override
    public Test getTest(Long testId) {
        return baseMapper.selectById(testId);
    }

    @Override
    public List<Test> listTest(TestVO testVO) {
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        if (testVO.getTesterId() != null) queryWrapper.eq("test_id", testVO.getTesterId());
        if (testVO.getClassCode() != null) queryWrapper.eq("class_code", testVO.getClassCode());
        if (testVO.getTestFile() != null) queryWrapper.eq("test_file", testVO.getTestFile());
        if (testVO.getScheduleSerial() != null) queryWrapper.eq("schedule_serial", testVO.getScheduleSerial());
        if (testVO.getLimit() != null) queryWrapper.last(" limit " + testVO.getLimit());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Test> pagedListTest(TestVO testVO) {
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        if (testVO.getTesterId() != null) queryWrapper.eq("test_id", testVO.getTesterId());
        if (testVO.getClassCode() != null) queryWrapper.eq("class_code", testVO.getClassCode());
        if (testVO.getTestFile() != null) queryWrapper.eq("test_file", testVO.getTestFile());
        if (testVO.getScheduleSerial() != null) queryWrapper.eq("schedule_serial", testVO.getScheduleSerial());
        if (testVO.getLimit() != null) queryWrapper.last(" limit " + testVO.getLimit());

        Page<Test> page = new Page<>();
        page.setCurrent(testVO.getCurrentPage());
        page.setSize(testVO.getPageSize());
        IPage<Test> pagedList = baseMapper.selectPage(page, queryWrapper);
        return pagedList;
    }
}
