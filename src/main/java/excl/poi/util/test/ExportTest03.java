package excl.poi.util.test;


import excl.poi.util.ErrorVo;
import excl.poi.util.ExcelUtil;
import excl.poi.util.StudentVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by ZJL on 2015/5/25.
 */
public class ExportTest03 {
    public static void main(String[] args) {
        // 初始化数据
        List<StudentVO> list = new ArrayList<StudentVO>();
        for (int i = 1; i <= 1000; i++) {
            StudentVO studentVO = new StudentVO();
            studentVO.setId(i);
            studentVO.setName("张三" + i);
            studentVO.setAge(26);
            studentVO.setClazz("五期提高班");
            studentVO.setCompany("天融信");
            list.add(studentVO);
        }
        ExcelUtil<StudentVO> util = new ExcelUtil<StudentVO>(StudentVO.class);
        //导出返回的的一个工作簿
        HSSFWorkbook wb = util.exportExcel(list, "学生信息", setErrorVoList(list));// 导出
        try {
            FileOutputStream fout = new FileOutputStream("D:\\test.xls");
            wb.write(fout);
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("导出完成！！！");
    }


    public static List<ErrorVo> setErrorVoList(List<StudentVO> voList) {

        List<ErrorVo> vos = new ArrayList<ErrorVo>();
            vos.add(new ErrorVo(8, 0, "姓名不能少于5个字"));
            vos.add(new ErrorVo(16, 0, "姓名不能少于5个字"));
        return vos;
    }
}
