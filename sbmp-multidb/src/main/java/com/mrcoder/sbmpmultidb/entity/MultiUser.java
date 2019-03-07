package com.mrcoder.sbmpmultidb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mrcoder
 * @since 2019-02-26
 */
@Data
@TableName("multi_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MultiUser implements Serializable {
    private static final long serialVersionUID = -1923859222295750467L;
    //private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER)
    private Long id;

    private String name;

    private Integer age;

}