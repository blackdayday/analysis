package tgc.edu.ljc.stu.custom;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class FileUtils {
	/***
	 * 下载文件
	 * 
	 * @param request    请求对象
	 * @param response   响应对象
	 * @param sourceName 服务器上的源文件名
	 * @param targetName 下载的默认文件名
	 */
	@Value(value = "${web.file_url}")
	private String url;
	
	public void download(HttpServletRequest request, HttpServletResponse response, String sourceName,
			String targetName) {
		response.setContentType("application/force-download");
		targetName = getStr(request, targetName);
		response.setHeader("Content-Disposition", "attachment;filename=" + targetName);
		ServletOutputStream out;
		try {
			out = response.getOutputStream();
			FileInputStream fis = new FileInputStream(new File(url, sourceName));
			
			byte[] buf = new byte[2048];
			int n = 0;
			while ((n = fis.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			fis.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 将下载的文件名改成能够在网上传送的字符串
	 * 
	 * @param request  请求对象
	 * @param fileName 文件名
	 * @return
	 */
	public String getStr(HttpServletRequest request, String fileName) {
		String downloadFileName = null;
		String agent = request.getHeader("USER-AGENT");
		try {

			if (agent != null && agent.toLowerCase().indexOf("firefox") > 0) {

				// downloadFileName =
				// Base64Utils.encodeToUrlSafeString(fileName.getBytes("UTF-8"));
				downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
			} else {
				//downloadFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				downloadFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

}
