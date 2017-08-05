package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import play.data.validation.Required;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		render();
	}

	// 为myName参数添加注解,用于验证
	public static void sayHello(@Required String myName) {
		/*
		 * 添加数据验证：验证器的hasErrors()方法用于检查error对象，
		 * 如果有error对象存在，则可以在Flash作用域中增加错误提示信息并重定向到index Action。
		 * Flash作用域通常用于Action重定向时保存信息（比如错误提示信息）。
		 */
		if (validation.hasErrors()) {
			flash.error("用户名不能为空！");
			index(); // 调用index方法
		}
		render(myName);
	}

	public static void reverseStr(String s) {
		String str = "a1b2c3d4e6f7g8";
		String s1 = new StringBuffer(str).reverse().toString();
		s = s1;
		render(s);
	}

	public static void login(@Required String username,
			@Required String password) {
		if (validation.hasErrors()) {
			flash.error("用户名不能为空！");
			index(); // 调用index方法
		}
		render(username, password);
	}

	/**
	 * 功能：Java读取txt文件的内容 步骤：
	 * 1：先获得文件句柄 
	 * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 
	 * 4：一行一行的输出。readline()。 
	 * 备注：需要考虑的是异常情况
	 * 
	 * @param filePath
	 */
	public static void readTxtFile(String lineTxt) {
		String filePath = "..\\helloworld\\app\\a.txt";

		try {
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
					render(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		
	}
}