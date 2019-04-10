package com.mrcoder.sbjdbcmultidbatomikos.dao;

import com.mrcoder.sbjdbcmultidbatomikos.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDao {

    @Resource(name = "masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @Resource(name = "slaveJdbcTemplate")
    private JdbcTemplate slaveJdbcTemplate;
    
    class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setAge(resultSet.getInt("age"));
            student.setGrade(resultSet.getInt("grade"));
            student.setName(resultSet.getString("name"));
            return student;
        }
    }


    public int save(Student student) {
        String sql = "INSERT INTO `test`.`student` (`age`, `grade`,`name`) VALUES (?, ?, ?)";
        int result = masterJdbcTemplate.update(sql, new Object[]{student.getAge(), student.getGrade(), student.getName()});
        return result;
    }

    public List<Student> getList() {
        String sql = "select * from student s";
        List<Student> list = masterJdbcTemplate.query(sql, new StudentMapper());
        return list;
    }
}
