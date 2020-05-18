package com.trainingmanagesys.web.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trainingmanagesys.web.test.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trainingmanagesys.web.test.vo.TestVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luoying
 * @since 2020-05-18
 */
public interface ITestService extends IService<Test> {

    Long addTest(Test test);

    String updateTest(Test test);

    String deleteTest(Long testId);

    List<Test> listTest(TestVO testVO);

    IPage<Test> pagedListTest(TestVO testVO);

}
