package com.mrcoder.sbjdbcmultidbatomikos.dao;

import com.mrcoder.sbjdbcmultidbatomikos.entity.Student;
import com.mrcoder.sbjdbcmultidbatomikos.entity.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeacherDao {

    @Resource(name = "masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @Resource(name = "slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;


    class TeacherMapper implements RowMapper<Teacher> {
        @Override
        public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setAge(resultSet.getInt("age"));
            teacher.setCourse(resultSet.getInt("course"));
            teacher.setName(resultSet.getString("name"));
            return teacher;
        }
    }


    public int save(Teacher teacher) {
        String sql = "INSERT INTO `test2`.`teacher` (`age`, `course`,`name`) VALUES (?, ?, ?)";
        int result = slaveJdbcTemplate.update(sql, new Object[]{teacher.getAge(), teacher.getCourse(), teacher.getName()});
        return result;
    }

    public List<Teacher> getList() {
        String sql = "select * from teacher t ";
        List<Teacher> list = slaveJdbcTemplate.query(sql, new TeacherMapper());
        return list;
    }
}
