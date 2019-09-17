package com.web.twozqj.dao;


import com.web.twozqj.entrity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TypeDao
 * Author:   落叶尘纷
 * Date:     2019/9/14 21:14
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           1.0              描述
 */
public interface TypeDao extends JpaRepository<Type,Integer>, JpaSpecificationExecutor<Type> {
}
