package cn.max.poi;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * 事件驱动(SAX)解析 Excel2007
 *
 * @author MaxStar
 * @date 2018/8/7
 */
public class ExcelReader2007Test {

    private ExcelReader2007 reader;
    private InputStream inputStream;

    @Before
    public void setUp() throws FileNotFoundException {
        reader = new ExcelReader2007();
        inputStream = new FileInputStream(new File(this.getClass().getResource("/data.xlsx").getFile()));
    }

    /**
     * 解析单个sheet
     *
     * @throws Exception
     */
    @Test
    public void testResolveOne() throws Exception {
        long start = System.currentTimeMillis();
        reader.processOne(inputStream, 2);
        List<List<String>> rowList = reader.getRowList();
        long end = System.currentTimeMillis() - start;

        // 遍历单元格内容
        rowList.forEach(rowValueList -> {
                    rowValueList.forEach(rowValue -> System.out.print(rowValue + " "));
                    System.out.println();
                }
        );

        System.out.println("当前sheet行数：" + rowList.size());
        System.out.println("耗时：" + end + "ms");
    }

    /**
     * 解析多个sheet
     *
     * @throws Exception
     */
    @Test
    public void testResolveAll() throws Exception {
        long start = System.currentTimeMillis();
        reader.processAll(inputStream);
        List<List<List<String>>> sheetList = reader.getSheetList();
        long end = System.currentTimeMillis() - start;

        // 遍历单元格内容
        for (int i = 0; i < sheetList.size(); i++) {
            List<List<String>> rowList = sheetList.get(i);
            System.out.println("遍历第" + (i + 1) + "个sheet的内容");
            rowList.forEach(rowValueList -> {
                        rowValueList.forEach(rowValue -> System.out.print(rowValue + " "));
                        System.out.println();
                    }
            );
            System.out.println("第" + (i + 1) + "个sheet遍历完毕，行数：" + rowList.size());
        }
        System.out.println("总耗时：" + end);
    }


    /**
     * 解析多个sheet
     *
     * @throws Exception
     */
    @Test
    public void testResolveName() throws Exception {
        long start = System.currentTimeMillis();
        reader.processByName(inputStream, "Sheet1");
        List<List<String>> rowList = reader.getRowList();
        long end = System.currentTimeMillis() - start;

        // 遍历单元格内容
        rowList.forEach(rowValueList -> {
                    rowValueList.forEach(rowValue -> System.out.print(rowValue + " "));
                    System.out.println();
                }
        );

        System.out.println("当前sheet行数：" + rowList.size());
        System.out.println("耗时：" + end + "ms");
    }
}