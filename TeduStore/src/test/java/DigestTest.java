import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * Digest：摘要
 * @author SsM479丶
 *
 */
public class DigestTest {

	@Test
	/**
	 * 计算一个文件的摘要(MD5)
	 */
	public void testFileDidest() throws Exception{
		// 文件保存在项目根目录下
		String file = "passwd";
		// 打开文件
		InputStream in = new FileInputStream(file);
		// 计算文件中数据的md5摘要
		String md5 = DigestUtils.md5Hex(in);
		in.close();
		// 输出
		System.out.println(md5);
	}
	
	@Test
	/**
	 * 计算一个字符串你的摘要
	 */
	public void testStringDigest() {
		String pwd = "SsM479";
		// "123" - UTF-8 -> bytes - md5 -> 摘要
		String md5 = DigestUtils.md5Hex(pwd);
		// 输出
		System.out.println(md5);
	}
}
